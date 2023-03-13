package m.Controller;

import m.Model.Entity.Color;
import m.Model.Service.ColorService;
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
@RequestMapping("/api/m/ColorController")
public class ColorController {
    @Autowired
    private ColorService<Color, Integer> colorService;

    @GetMapping("/getAll")
    private List<Color> getAllColor() {
        return colorService.findAllColor();
    }

    @GetMapping()
    private List<Color> getAllColorTrue() {
        return colorService.getAllColorStatusTrue();
    }

    @PostMapping()
    private Color createColor(@RequestBody Color color) {
        color.setColorStatus(true);
        return colorService.saveAndUpdate(color);
    }

    @PutMapping("/{colorId}")
    private Color updateColor(@RequestBody Color color, @PathVariable("colorId") int colorId) {
        Color colorUpdate = colorService.getById(colorId);
        colorUpdate.setColorName(color.getColorName());
        colorUpdate.setColorStatus(color.isColorStatus());
        return colorService.saveAndUpdate(colorUpdate);
    }

    @GetMapping("lockColor/{colorId}")
    private ResponseEntity<?> lockColor(@PathVariable("colorId") int colorId) {
        Color color = colorService.getById(colorId);
        color.setColorStatus(false);
        colorService.saveAndUpdate(color);
        return ResponseEntity.ok("Lock color successfully!");
    }

    @GetMapping("/searchColor")
    private List<Color> searchColor(@RequestParam("name") String name) {
        return colorService.searchColorByColorName(name);
    }

    @GetMapping("/sortColorByName")
    private List<Color> sortColor(@RequestParam("direction") String direction) {
        return colorService.sortColorByColorName(direction);
    }

    @GetMapping("/pagging")
    private ResponseEntity<Map<String, Object>> pagination(@RequestParam(defaultValue = "0")
                                                           int page,
                                                           @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Color> colorPage = colorService.getPagging(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("Color", colorPage.getContent());
        data.put("TotalElement in page", colorPage.getTotalElements());
        data.put("Size", colorPage.getSize());
        data.put("Total page", colorPage.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
