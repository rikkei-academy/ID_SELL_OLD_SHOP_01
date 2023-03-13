package m.Controller;

import m.Model.Entity.*;
import m.Model.Service.CatalogService;
import m.Model.Service.CollectionService;
import m.Model.Service.GenderService;
import m.Model.Service.ProductService;
import m.PayLoad.Request.ProductResquest;
import m.PayLoad.Response.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/ProductController")
public class ProductController {
    @Autowired
    private ProductService<Product, Integer> productService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CollectionService<Collection, Integer> collectionService;

    @Autowired
    private GenderService<Gender, Integer> genderService;

    @GetMapping()
    public List<ProductDTO> getAllProductTrue() {
        List<Product> productList = productService.findAllProductTrue();
        List<ProductDTO> productDTOList = new ArrayList<>();
        String status="";
        for (Product p:productList ) {
            if (p.getProductStatus()==0){
                status="Mới";
            } else if (p.getProductStatus()==1) {
                status="Như mới";
            } else if (p.getProductStatus()==2) {
                status="Tốt";
            } else if (p.getProductStatus()==3) {
                status="Trung bình";
            }else {
                status="Hết hàng";
            }
            ProductDTO productDTO=new ProductDTO(
                    p.getProductName(),
                    p.getCatalog().getCatalogName(),
                    p.getGender().getGenderName(),
                    p.getCollection().getCollectionName(),
                    p.getProductImage(),
                    p.getProductTitle(), p.getProductDescription(),
                    (p.getProductPrice()-(p.getProductPrice()*p.getDiscount()/100)),
                    p.getDiscount()+"%",
                    status

            );
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @GetMapping("/getAll")
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productService.findAllProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();
        String status="";
        for (Product p:productList ) {
            if (p.getProductStatus()==0){
                status="Mới";
            } else if (p.getProductStatus()==1) {
                status="Như mới";
            } else if (p.getProductStatus()==2) {
                status="Tốt";
            } else if (p.getProductStatus()==3) {
                status="Trung bình";
            }else {
                status="Hết hàng";
            }
            ProductDTO productDTO=new ProductDTO(
                    p.getProductName(),
                    p.getCatalog().getCatalogName(),
                    p.getGender().getGenderName(),
                    p.getCollection().getCollectionName(),
                    p.getProductImage(),
                    p.getProductTitle(), p.getProductDescription(),
                    (p.getProductPrice()-(p.getProductPrice()*p.getDiscount()/100)),
                    p.getDiscount()+"%",
                    status

            );
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductResquest productResquest) {
        try {
            Product product = new Product();
            Collection collection = collectionService.getById(productResquest.getCollectionId());
            Gender gender = genderService.getById(productResquest.getGenderId());
            Catalog catalog = catalogService.getById(productResquest.getCatalogId());
            product.setProductName(productResquest.getProductName());
            if (catalog.isCatalogStatus()){
                product.setCatalog(catalog);
            }else {
                return ResponseEntity.ok("Danh mục này không tồn tại!");
            }
            if (gender.isGenderStatus()){
                product.setGender(gender);
            }else {
                return ResponseEntity.ok("Gender này không tồn tại!");
            }
            if (collection.isCollectionStatus()){
                product.setCollection(collection);
            }else {
                return ResponseEntity.ok("Bộ sưu tập này không tồn tại!");
            }
            product.setProductImage(productResquest.getProductImage());
            product.setProductTitle(productResquest.getProductTitle());
            product.setProductDescription(productResquest.getProductDescription());
            product.setProductPrice(productResquest.getProductPrice());
            product.setDiscount(productResquest.getDiscount());
            product.setProductStatus(1);
            product = productService.saveAndUpdate(product);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Product creation failed");
        }
    }

    @PutMapping("/{productId}")
    private ResponseEntity<?> updateProduct(@RequestBody ProductResquest productResquest,@PathVariable("productId") int productId){
        Product product=productService.getById(productId);
        try {
        Collection collection = collectionService.getById(productResquest.getCollectionId());
        Gender gender = genderService.getById(productResquest.getGenderId());
        Catalog catalog = catalogService.getById(productResquest.getCatalogId());
        product.setProductName(productResquest.getProductName());
        if (catalog.isCatalogStatus()){
            product.setCatalog(catalog);
        }else {
            return ResponseEntity.ok("Danh mục này không tồn tại!");
        }
        if (gender.isGenderStatus()){
            product.setGender(gender);
        }else {
            return ResponseEntity.ok("Gender này không tồn tại!");
        }
        if (collection.isCollectionStatus()){
            product.setCollection(collection);
        }else {
            return ResponseEntity.ok("Bộ sưu tập này không tồn tại!");
        }
        product.setProductImage(productResquest.getProductImage());
        product.setProductTitle(productResquest.getProductTitle());
        product.setProductDescription(productResquest.getProductDescription());
        product.setProductPrice(productResquest.getProductPrice());
        product.setDiscount(productResquest.getDiscount());
        product.setProductStatus(productResquest.getProductStatus());
        product = productService.saveAndUpdate(product);
        return ResponseEntity.ok(product);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.ok("Product update failed");
    }
    }

    @GetMapping("lockProduct/{productId}")
    private ResponseEntity<?> lockProduct(@PathVariable("productId") int productId) {
        Product product=productService.getById(productId);
        product.setProductStatus(4);
        productService.saveAndUpdate(product);
        return ResponseEntity.ok("Lock product successfully!");
    }

    @GetMapping("/searchProduct")
    private List<Product> searchProduct(@RequestParam("name") String name) {
        return productService.searchProductByProductName(name);
    }

    @GetMapping("/sortProductByName")
    private List<Product> sortProductByName(@RequestParam("direction") String direction,@RequestParam("sortBy") String sortBy) {
        return productService.sortProductByProductName(direction,sortBy);
    }

    @GetMapping("/pagging")
    private ResponseEntity<Map<String, Object>> pagination(@RequestParam(defaultValue = "0")
                                                           int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getPagging(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("Product", productPage.getContent());
        data.put("TotalElement in page", productPage.getTotalElements());
        data.put("Size", productPage.getSize());
        data.put("Total page", productPage.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
