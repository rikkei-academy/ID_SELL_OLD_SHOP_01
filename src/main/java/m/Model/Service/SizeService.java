package m.Model.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SizeService<T, V> {

    List<T> findAllSize();

    T saveAndUpdate(T t);

    List<T> getAllSizeStatusTrue();

    void delete(V id);

    T getById(V id);

    List<T> searchSizeBySizeName(String name);

    List<T> sortSizeBySizeName(String direction);

    Page<T> getPagging(Pageable pageable);
}
