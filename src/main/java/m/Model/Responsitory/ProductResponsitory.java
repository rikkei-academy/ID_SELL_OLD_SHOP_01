package m.Model.Responsitory;

import m.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductResponsitory extends JpaRepository<Product,Integer> {
    List<Product> findByProductNameContaining(String name);
    @Query(value = "from Product p where p.productStatus<4")
    List<Product> getAllByProductTrue();


}
