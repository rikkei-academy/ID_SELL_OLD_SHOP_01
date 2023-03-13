package m.Model.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService<T, V> {
    List<T> findAllProduct();

    T saveAndUpdate(T t);

    void delete(V id);

    T getById(V id);

    List<T> searchProductByProductName(String name);

    List<T> sortProductByProductName(String direction,String sortBy);

    Page<T> getPagging(Pageable pageable);
}
