package m.Controller;

import m.Model.Entity.Catalog;
import m.Model.Service.CatalogService;
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
@RequestMapping("/api/m/CatalogController")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;
    @GetMapping("/getAll")
    public List<Catalog> getAllCatalog(){
        return catalogService.findAllCatalog();
    }

    @GetMapping
    public List<Catalog> getAllCatalogTrue(){
        return catalogService.getAllCatalogStatusTrue();
    }

    @GetMapping("/{catalogId}")
    public Catalog finById(@PathVariable("catalogId") int catalogId){
        return catalogService.getById(catalogId);
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

    @GetMapping("/lockCatalog/{catalogId}")
    public ResponseEntity<?> deleteCatalog(@PathVariable("catalogId") int catalogId){
       Catalog catalog=catalogService.getById(catalogId);
       catalog.setCatalogStatus(false);
        catalogService.saveAndUpdate(catalog);
        return ResponseEntity.ok("Lock catagory successfully!");
    }

    @GetMapping("/searchCatalogByName")
    public List<Catalog> searchCatalogByName(@RequestParam("name") String name){
        return catalogService.searchCatalogByCatalogName(name);
    }
    @GetMapping("/sortCatalogByName")
    public List<Catalog> sortCatalogByName(@RequestParam("diraction") String diraction){
        return catalogService.sortCatalogByCatalogName(diraction);
    }

    @GetMapping("/paginationCatalog")
    public ResponseEntity<Map<String,Object>> paginationCatalog(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Catalog> catalogPage=catalogService.getPagging(pageable);
        Map<String,Object> data=new HashMap<>();
        data.put("Catagory in page",catalogPage.getContent());
        data.put("Total elements in Catalog",catalogPage.getTotalElements());
        data.put("Total page in Catalog",catalogPage.getTotalPages());
        data.put("Size",catalogPage.getSize());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
