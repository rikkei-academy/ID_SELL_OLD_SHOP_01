package m.Model.Responsitory;

import m.Model.Entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Carts,Integer> {
    Carts findByUsers_UsersId(int id);

    List<Carts> findAllByUsers_UsersIdAndState(int userdId,int state);
    @Query(value = " from Carts c where c.users.usersId=:id and c.state=:state")
    Carts getCartByUserandState(@Param("id") int id,@Param("state") int state);
    @Query(value = "from Carts c where c.state=1")
    List<Carts> findByState1();
    @Query(value = "from Carts c where c.state=0")

    List<Carts> findByState0();
    @Query(value = "from Carts c where c.state=2")

    List<Carts> findByState2();
    @Query(value = "from Carts c where c.state=3")

    List<Carts> findByState3();
    @Query(value = "from Carts c where c.state=4")

    List<Carts> findByState4();
    @Query(value = "from Carts c where c.state<5")
    List<Carts> findByState();



}
