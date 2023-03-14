package m.Model.Service;

import m.Model.Entity.CartDetails;
import m.Model.Entity.Carts;

import java.util.List;

public interface CartDetailService<T,V> {
    List<T> findAllCartDetail();
    T saveAndUpdate(T t);
    void delete(int id);
    T getById(int id);
}
