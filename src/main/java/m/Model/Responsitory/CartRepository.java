package m.Model.Responsitory;

import m.Model.Entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Carts,Integer> {
    Carts findByUsers_UsersId(int id);
    List<Carts> findAllByUsers_UsersIdAndState(int userdId,int state);
    Carts findByUsers_UsersIdAndState(int id,int state);
}
