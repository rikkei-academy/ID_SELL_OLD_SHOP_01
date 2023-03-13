package m.Model.Service;

import m.Model.Entity.Catalog;
import m.Model.Entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorService<T,V>{
    List<T> findAllColor();
    T saveAndUpdate(T t);
    List<T> getAllColorStatusTrue();
    void delete(V id);
    T getById(V id);
    List<T> searchColorByColorName(String name);
    List<T> sortColorByColorName(String direction);
    Page<T> getPagging(Pageable pageable);


}
