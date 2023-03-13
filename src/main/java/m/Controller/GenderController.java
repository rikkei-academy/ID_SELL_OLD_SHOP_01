package m.Controller;

import m.Model.Entity.Color;
import m.Model.Entity.Gender;
import m.Model.Service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/GenderController")
public class GenderController {
    @Autowired
    private GenderService<Gender, Integer> genderService;

    @GetMapping("/getAll")
    private List<Gender> getAllColor() {
        return genderService.findAllGender();
    }
//
//    @GetMapping()
//    private List<Gender> getAllColorTrue() {
//        return colorService.getAllColorStatusTrue();
//    }
//
//    @PostMapping()
//    private Gender createGender(@RequestBody Gender gender) {
//        color.setColorStatus(true);
//        return colorService.saveAndUpdate(color);
//    }
//
//    @PutMapping("/{genderId}")
//    private Gender updateGender(@RequestBody Gender gender, @PathVariable("genderId") int genderId) {
//       Gender
//        return colorService.saveAndUpdate(colorUpdate);
//    }

    @GetMapping("lockGender/{genderId}")
    private ResponseEntity<?> lockGender(@PathVariable("genderId") int genderId) {
        Gender gender=genderService.getById(genderId);
        gender.setGenderStatus(false);
        genderService.saveAndUpdate(gender);
        return ResponseEntity.ok("Lock Gender successfully!");
    }

    @GetMapping("/searchGender")
    private List<Gender> searchGender(@RequestParam("name") String name) {
        return genderService.searchGenderByGenderName(name);
    }

    @GetMapping("/sortGenderByName")
    private List<Gender> sortGender(@RequestParam("direction") String direction) {
        return genderService.sortGenderByGenderName(direction);
    }

    @GetMapping("/pagging")
    private ResponseEntity<Map<String, Object>> pagination(@RequestParam(defaultValue = "0")
                                                           int page,
                                                           @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Gender> genderPage = genderService.getPagging(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("Gender", genderPage.getContent());
        data.put("TotalElement in page", genderPage.getTotalElements());
        data.put("Size", genderPage.getSize());
        data.put("Total page", genderPage.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
