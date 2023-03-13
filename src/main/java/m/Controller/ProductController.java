package m.Controller;

import m.Model.Entity.Catalog;
import m.Model.Entity.Product;
import m.Model.Service.CatalogService;
import m.Model.Service.ProductService;
import m.PayLoad.Request.ProductResquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/ProductController")
public class ProductController {
    @Autowired
    private ProductService<Product, Integer> productService;

    @Autowired
    private CatalogService catalogService;


    @GetMapping
    public List<Product> getAllProduct() {
        return productService.findAllProduct();
    }
//    @PostMapping
//    public Product createProduct(@RequestBody ProductResquest productResquest){
//        Product product=new Product();
//        product.setProductName(productResquest.getProductName());
//        Catalog catalog=
//    }
}
