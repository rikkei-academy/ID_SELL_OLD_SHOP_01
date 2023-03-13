package m.Model.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenderService<T,V> {
    List<T> findAllGender();
    T saveAndUpdate(T t);
    List<T> getAllGenderStatusTrue();
    void delete(V id);
    T getById(V id);
    List<T> searchGenderByGenderName(String name);
    List<T> sortGenderByGenderName(String direction);
    Page<T> getPagging(Pageable pageable);
}
