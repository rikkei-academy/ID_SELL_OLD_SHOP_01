package m.Model.Responsitory;

import m.Model.Entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsResponsitory extends JpaRepository<ProductDetails, Integer> {

    @Query(value = "from ProductDetails p where p.productDetailsStatus<4")
    List<ProductDetails> getAllProductDetailsTrue();
}
