package m.Model.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailsService<T,V>{
    List<T> findAllProductDetails();
    List<T> findAllProductDetailsTrue();
    T saveAndUpdate(T t);

    void delete(V id);

    T getById(V id);

    List<T> searchProductDetailsBy(String searchBy,String name);

    List<T> sortProductDetailsBy(String direction,String sortBy);

    Page<T> getPagging(Pageable pageable);
}
