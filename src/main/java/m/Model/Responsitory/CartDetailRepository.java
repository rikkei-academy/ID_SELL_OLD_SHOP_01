package m.Model.Responsitory;

import m.Model.Entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetails,Integer> {
}
