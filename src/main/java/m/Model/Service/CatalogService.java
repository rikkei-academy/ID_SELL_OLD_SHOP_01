package m.Model.Service;

import m.Model.Entity.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogService {
    List<Catalog> findAllCatalog();
    Catalog saveAndUpdate(Catalog catalog);
    void delete(int id);
    Catalog getById(int id);
    List<Catalog> searchCatalogByCatalogName(String name);
    List<Catalog> sortCatalogByCatalogName(String direction);
    Page<Catalog> getPagging(Pageable pageable);

}
