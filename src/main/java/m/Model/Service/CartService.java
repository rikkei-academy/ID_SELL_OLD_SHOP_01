package m.Model.Service;

import m.Model.Entity.Carts;
import m.Model.Entity.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService<T,V> {
    List<T> findAllCart();
    T saveAndUpdate(T t);
    List<T> getCartInShoppingCart();
    void delete(int id);
    T getById(int id);

    List<Carts> findAllByUsers_UsersIdAndState(int userdId,int state);
    T findByUsers_UsersId(V v);
    List<Carts> searchStateBy(String name);

    T findByUsers_UsersIdAndState(int id,int state);
}
