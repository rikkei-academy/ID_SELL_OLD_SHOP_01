package m.Model.ServiceImp;

import m.Model.Entity.Catalog;
import m.Model.Responsitory.CatalogResponsitory;
import m.Model.Service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatalogServiceImp implements CatalogService {
    @Autowired
    private CatalogResponsitory catalogResponsitory;
    @Override
    public List<Catalog> findAllCatalog() {
        return catalogResponsitory.findAll();
    }

    @Override
    public Catalog saveAndUpdate(Catalog catalog) {
        return catalogResponsitory.save(catalog);
    }

    @Override
    public List<Catalog> getAllCatalogStatusTrue() {
        return catalogResponsitory.getAllCatalogStatusTrue();
    }

    @Override
    public void delete(int id) {
        catalogResponsitory.deleteById(id);
    }

    @Override
    public Catalog getById(int id) {
        return catalogResponsitory.findById(id).get();
    }



    @Override
    public List<Catalog> searchCatalogByCatalogName(String name) {
        return catalogResponsitory.findByCatalogNameContaining(name);
    }

    @Override
    public List<Catalog> sortCatalogByCatalogName(String direction) {
        if (direction.equalsIgnoreCase("asc")){
            return catalogResponsitory.findAll(Sort.by("catalogName").ascending());
        }else {
            return catalogResponsitory.findAll(Sort.by("catalogName").descending());
        }

    }

    @Override
    public Page<Catalog> getPagging(Pageable pageable) {
        return catalogResponsitory.findAll(pageable);
    }
}
