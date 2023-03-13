package m.Model.ServiceImp;

import m.Model.Entity.Product;
import m.Model.Responsitory.ProductResponsitory;
import m.Model.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService<Product,Integer> {
    @Autowired
    private ProductResponsitory productResponsitory;
    @Override
    public List<Product> findAllProduct() {
        return productResponsitory.findAll();
    }

    @Override
    public Product saveAndUpdate(Product product) {
        return productResponsitory.save(product);
    }

    @Override
    public void delete(Integer id) {
      productResponsitory.deleteById(id);
    }

    @Override
    public Product getById(Integer id) {
        return productResponsitory.findById(id).get();
    }

    @Override
    public List<Product> searchProductByProductName(String name) {
        return productResponsitory.findByProductNameContaining(name);
    }

    @Override
    public List<Product> sortProductByProductName(String direction,String sortBy) {
        if (direction.equalsIgnoreCase("asc")){
            if (sortBy.equalsIgnoreCase("name")){
                return productResponsitory.findAll(Sort.by("productName").ascending());
            } else if (sortBy.equalsIgnoreCase("price")) {
                return productResponsitory.findAll(Sort.by("productPrice").ascending());
            }else {
                return productResponsitory.findAll(Sort.by("discount").ascending());
            }
        }else {
            if (sortBy.equalsIgnoreCase("name")){
                return productResponsitory.findAll(Sort.by("productName").descending());
            } else if (sortBy.equalsIgnoreCase("price")) {
                return productResponsitory.findAll(Sort.by("productPrice").descending());
            }else {
                return productResponsitory.findAll(Sort.by("discount").descending());
            }
        }
    }

    @Override
    public Page<Product> getPagging(Pageable pageable) {
        return productResponsitory.findAll(pageable);
    }
}
