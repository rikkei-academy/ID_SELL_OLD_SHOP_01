package m.Model.Responsitory;

import m.Model.Entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CollectionResponsitory extends JpaRepository<Collection,Integer> {
    List<Collection> findByCollectionNameContaining(String name);
    List<Collection> findByCollectionDescriptionContaining(String name);
    @Query(value = "from Collection c where c.collectionStatus=true ")
    List<Collection> getAllCollectionTrue();
}
