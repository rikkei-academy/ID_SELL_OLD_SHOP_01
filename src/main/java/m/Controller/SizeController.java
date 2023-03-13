package m.Controller;

import m.Model.Entity.Color;
import m.Model.Entity.Size;
import m.Model.Service.SizeService;
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
@RequestMapping("/api/m/SizeController")
public class SizeController {
    @Autowired
    private SizeService<Size, Integer> sizeService;

    @GetMapping("/getAll")
    public List<Size> getAllSize() {
        return sizeService.findAllSize();
    }

    @GetMapping()
    public List<Size> getAllSizeStatusTrue() {
        return sizeService.getAllSizeStatusTrue();
    }

    @PostMapping()
    public Size createSize(@RequestBody Size size) {
        size.setSizeStatus(true);
        return sizeService.saveAndUpdate(size);
    }

    @PutMapping("/{sizeId}")
    public Size updateSize(@RequestBody Size size, @PathVariable("sizeId") int sizeId) {
        Size sizeOld = sizeService.getById(sizeId);
        sizeOld.setSizeName(size.getSizeName());
        sizeOld.setSizeStatus(size.isSizeStatus());
        return sizeService.saveAndUpdate(sizeOld);
    }

    @GetMapping("lockSize/{sizeId}")
    private ResponseEntity<?> lockSize(@PathVariable("sizeId") int sizeId) {
        Size size=sizeService.getById(sizeId);
       size.setSizeStatus(false);
       sizeService.saveAndUpdate(size);
        return ResponseEntity.ok("Lock size successfully!");
    }

    @GetMapping("/searchSize")
    private List<Size> searchSize(@RequestParam("name") String name) {
        return sizeService.searchSizeBySizeName(name);
    }

    @GetMapping("/sortSizeByName")
    private List<Size> sortSizeByName(@RequestParam("direction") String direction) {
        return sizeService.sortSizeBySizeName(direction);
    }

    @GetMapping("/pagging")
    private ResponseEntity<Map<String, Object>> pagination(@RequestParam(defaultValue = "0")
                                                           int page,
                                                           @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Size> sizePage = sizeService.getPagging(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("Size", sizePage.getContent());
        data.put("TotalElement in page", sizePage.getTotalElements());
        data.put("Size", sizePage.getSize());
        data.put("Total page",sizePage.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
