package m.Model.ServiceImp;

import m.Model.Entity.Collection;
import m.Model.Responsitory.CollectionResponsitory;
import m.Model.Service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImp implements CollectionService<Collection, Integer> {
    @Autowired
    private CollectionResponsitory collectionResponsitory;

    @Override
    public List<Collection> findAllCollections() {
        return collectionResponsitory.findAll();
    }

    @Override
    public Collection saveAndUpdate(Collection collection) {
        return collectionResponsitory.save(collection);
    }

    @Override
    public List<Collection> getAllCollectionsStatusTrue() {
        return collectionResponsitory.getAllCollectionTrue();
    }

    @Override
    public void delete(Integer id) {
        collectionResponsitory.deleteById(id);
    }

    @Override
    public Collection getById(Integer id) {
        return collectionResponsitory.findById(id).get();
    }

    @Override
    public List<Collection> searchCollectionsBy(String searchBy, String name) {
        if (searchBy.equalsIgnoreCase("name")) {
            return collectionResponsitory.findByCollectionNameContaining(name);
        } else {
            return collectionResponsitory.findByCollectionDescriptionContaining(name);
        }
    }

    @Override
    public List<Collection> sortCollectionsByCollectionsName(String direction) {
        if (direction.equalsIgnoreCase("asc")) {
            return collectionResponsitory.findAll(Sort.by("collectionName").ascending());
        } else {
            return collectionResponsitory.findAll(Sort.by("collectionName").descending());
        }
    }

    @Override
    public Page<Collection> getPagging(Pageable pageable) {
        return collectionResponsitory.findAll(pageable);
    }
}
