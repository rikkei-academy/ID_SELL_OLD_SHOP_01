package m.Controller;

import m.Model.Entity.Catalog;
import m.Model.Service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/CatalogController")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;
    @GetMapping
    public List<Catalog> getAllCatalog(){
        return catalogService.findAllCatalog();
    }
    @PostMapping
    public Catalog createCatalog(@RequestBody Catalog catalog){
        catalog.setCatalogStatus(true);
        return catalogService.saveAndUpdate(catalog);
    }
    @PutMapping("/{catalogId}")
    public Catalog updateCatalog(@PathVariable("catalogId") int catalogId,@RequestBody Catalog catalog){
        Catalog catalogold=catalogService.getById(catalogId);
        catalogold.setCatalogName(catalog.getCatalogName());
        catalogold.setCatalogStatus(catalog.isCatalogStatus());
        return catalogService.saveAndUpdate(catalogold);
    }

    @DeleteMapping("/{catalogId}")
    public ResponseEntity<?> deleteCatalog(@PathVariable("catalogId") int catalogId){
        catalogService.delete(catalogId);
        return ResponseEntity.ok("Xoá danh mục thành công!");
    }

    @GetMapping("/searchCatalogByName")
    public List<Catalog> searchCatalogByName(@RequestParam("name") String name){
        return catalogService.searchCatalogByCatalogName(name);
    }
    @GetMapping("/sortCatalogByName")
    public List<Catalog> sortCatalogByName(@RequestParam("diraction") String diraction){
        return catalogService.sortCatalogByCatalogName(diraction);
    }
}
