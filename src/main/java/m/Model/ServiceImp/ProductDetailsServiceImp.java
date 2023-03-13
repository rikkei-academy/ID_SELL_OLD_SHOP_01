package m.Model.ServiceImp;

import m.Model.Entity.ProductDetails;
import m.Model.Responsitory.ProductDetailsResponsitory;
import m.Model.Service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsServiceImp implements ProductDetailsService<ProductDetails, Integer> {
    @Autowired
    private ProductDetailsResponsitory productDetailsResponsitory;

    @Override
    public List<ProductDetails> findAllProductDetails() {
        return productDetailsResponsitory.findAll();
    }

    @Override
    public List<ProductDetails> findAllProductDetailsTrue() {
        return productDetailsResponsitory.getAllProductDetailsTrue();
    }

    @Override
    public ProductDetails saveAndUpdate(ProductDetails productDetails) {
        return productDetailsResponsitory.save(productDetails);
    }

    @Override
    public void delete(Integer id) {
        productDetailsResponsitory.deleteById(id);
    }

    @Override
    public ProductDetails getById(Integer id) {
        return productDetailsResponsitory.findById(id).get();
    }

    @Override
    public List<ProductDetails> searchProductDetailsBy(String searchBy, String name) {
        if (searchBy.equalsIgnoreCase("productname")) {
            return productDetailsResponsitory.getProductDetailsByProductName(name);
        } else if (searchBy.equalsIgnoreCase("colorname")) {
            return productDetailsResponsitory.getProductDetailsByProductName(name);
        } else {
            return null;
        }

    }

    @Override
    public List<ProductDetails> sortProductDetailsBy(String direction, String sortBy) {
        if (direction.equalsIgnoreCase("asc")) {
            if (sortBy.equalsIgnoreCase("quantity")) {
                return productDetailsResponsitory.findAll(Sort.by("quantity").ascending());
            } else {
                return productDetailsResponsitory.findAll(Sort.by("productDetailsStatus").ascending());
            }
        } else {
            if (sortBy.equalsIgnoreCase("quantity")) {
                return productDetailsResponsitory.findAll(Sort.by("quantity").descending());
            } else {
                return productDetailsResponsitory.findAll(Sort.by("productDetailsStatus").descending());
            }
        }
    }

    @Override
    public Page<ProductDetails> getPagging(Pageable pageable) {
        return productDetailsResponsitory.findAll(pageable);
    }
}
