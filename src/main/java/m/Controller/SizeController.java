package m.Controller;

import m.Model.Entity.Size;
import m.Model.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
