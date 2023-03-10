package m.Model.Responsitory;

import m.Model.Entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogResponsitory extends JpaRepository<Catalog,Integer> {
    List<Catalog> findByCatalogNameContaining(String name);
}
