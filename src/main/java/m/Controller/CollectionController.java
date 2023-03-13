package m.Controller;

import m.Model.Entity.Collection;
import m.Model.Entity.Size;
import m.Model.Service.CollectionService;
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
@RequestMapping("/api/m/CollectionController")
public class CollectionController {
    @Autowired
    private CollectionService<Collection,Integer> collectionService;

    @GetMapping("/getAll")
    public List<Collection> getAllCollection() {
        return collectionService.findAllCollections();
    }

    @GetMapping()
    public List<Collection> getAllCollectionStatusTrue() {
        return collectionService.getAllCollectionsStatusTrue();
    }

    @PostMapping()
    public Collection createSize(@RequestBody Collection collection) {
        collection.setCollectionStatus(true);
        return collectionService.saveAndUpdate(collection);
    }

    @PutMapping("/{collectionId}")
    public Collection updateCollection(@RequestBody Collection collection, @PathVariable("collectionId") int collectionId) {
        Collection collectionOld=collectionService.getById(collectionId);
        collectionOld.setCollectionName(collection.getCollectionName());
        collectionOld.setImageCollection(collection.getImageCollection());
        collectionOld.setCollectionDescription(collection.getCollectionDescription());
        collectionOld.setCollectionStatus(collection.isCollectionStatus());
        return collectionService.saveAndUpdate(collectionOld);
    }

    @GetMapping("lockCollection/{collectionId}")
    private ResponseEntity<?> lockCollection(@PathVariable("collectionId") int collectionId) {
       Collection collection=collectionService.getById(collectionId);
       collection.setCollectionStatus(false);
       collectionService.saveAndUpdate(collection);
        return ResponseEntity.ok("Lock Collection successfully!");
    }

    @GetMapping("/searchCollection")
    private List<Collection> searchCollection(@RequestParam("name") String name,@RequestParam("searchBy") String searchBy) {
        return collectionService.searchCollectionsBy(searchBy,name);
    }

    @GetMapping("/sortCollectionByName")
    private List<Collection> sortCollectionByName(@RequestParam("direction") String direction) {
        return collectionService.sortCollectionsByCollectionsName(direction);
    }

    @GetMapping("/pagging")
    private ResponseEntity<Map<String, Object>> pagination(@RequestParam(defaultValue = "0")
                                                           int page,
                                                           @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Collection> collections = collectionService.getPagging(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("Collections", collections.getContent());
        data.put("TotalElement in page", collections.getTotalElements());
        data.put("Size", collections.getSize());
        data.put("Total page",collections.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
