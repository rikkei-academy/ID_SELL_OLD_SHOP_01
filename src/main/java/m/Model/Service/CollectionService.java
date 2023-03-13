package m.Model.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectionService<T,V> {
    List<T> findAllCollections();
    T saveAndUpdate(T t);
    List<T> getAllCollectionsStatusTrue();
    void delete(V id);
    T getById(V id);
    List<T> searchCollectionsBy(String searchBy,String name);
    List<T> sortCollectionsByCollectionsName(String direction);
    Page<T> getPagging(Pageable pageable);
}
