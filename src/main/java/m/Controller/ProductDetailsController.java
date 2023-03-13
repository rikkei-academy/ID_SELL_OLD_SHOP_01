package m.Controller;

import m.Model.Entity.*;
import m.Model.Service.*;
import m.PayLoad.Request.ProductDetailsResquest;
import m.PayLoad.Request.ProductResquest;
import m.PayLoad.Response.ProductDTO;
import m.PayLoad.Response.ProductDetailsDTO;
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
@RequestMapping("/api/m/ProductDetailsController")
public class ProductDetailsController {
    @Autowired
    private ProductService<Product, Integer> productService;

   @Autowired
   private ColorService<Color,Integer> colorService;

   @Autowired
   private SizeService<Size,Integer> sizeService;

   @Autowired
   private ProductDetailsService<ProductDetails,Integer> productDetailsService;

    @GetMapping()
    public List<ProductDetailsDTO> getAllProductTrue() {
        List<ProductDetails> productDetailsList = productDetailsService.findAllProductDetailsTrue();
        List<ProductDetailsDTO> productDetailsDTOList = new ArrayList<>();
        String status="";
        for (ProductDetails p:productDetailsList ) {
            if (p.getProductDetailsStatus() == 0) {
                status = "Mới";
            } else if (p.getProductDetailsStatus() == 1) {
                status = "Như mới";
            } else if (p.getProductDetailsStatus() == 2) {
                status = "Tốt";
            } else  {
                status = "Trung bình";

            }
            ProductDetailsDTO productDetailsDTO=new ProductDetailsDTO(
                    p.getProduct().getProductName(),
                    p.getSize().getSizeName(),
                    p.getColor().getColorName(),
                    p.getQuantity(),
                    status
            );
            productDetailsDTOList.add(productDetailsDTO);
        }
        return productDetailsDTOList;
    }

    @GetMapping("/getAll")
    public List<ProductDetailsDTO> getAllProduct() {
        List<ProductDetails> productDetailsList = productDetailsService.findAllProductDetails();
        List<ProductDetailsDTO> productDetailsDTOList = new ArrayList<>();
        String status="";
        for (ProductDetails p:productDetailsList ) {
            if (p.getProductDetailsStatus() == 0) {
                status = "Mới";
            } else if (p.getProductDetailsStatus() == 1) {
                status = "Như mới";
            } else if (p.getProductDetailsStatus() == 2) {
                status = "Tốt";
            } else if (p.getProductDetailsStatus() == 3) {
                status = "Trung bình";
            } else {
                status = "Hết hàng";
            }
            ProductDetailsDTO productDetailsDTO=new ProductDetailsDTO(
                    p.getProduct().getProductName(),
                    p.getSize().getSizeName(),
                    p.getColor().getColorName(),
                    p.getQuantity(),
                    status
            );
            productDetailsDTOList.add(productDetailsDTO);
        }
        return productDetailsDTOList;
    }

    @PostMapping
    public ResponseEntity<?> createProductDetails(@RequestBody ProductDetailsResquest productDetailsResquest) {
        ProductDetails productDetails = new ProductDetails();
        try {

            Product product=productService.getById(productDetailsResquest.getProductId());
            Color color=colorService.getById(productDetailsResquest.getColorId());
            Size size=sizeService.getById(productDetailsResquest.getSizeId());

            if (product.getProductStatus()<4){
                productDetails.setProduct(product);
            }else {
                return ResponseEntity.ok("Sản phẩm này không tồn tại!");
            }
            if (color.isColorStatus()){
                productDetails.setColor(color);
            }else {
                return ResponseEntity.ok("Color này không tồn tại!");
            }
            if (size.isSizeStatus()){
                productDetails.setSize(size);
            }else {
                return ResponseEntity.ok("Size này không tồn tại!");
            }
            productDetails.setQuantity(productDetailsResquest.getQuantity());
            productDetails.setProductDetailsStatus(1);
            productDetails=productDetailsService.saveAndUpdate(productDetails);
            return ResponseEntity.ok(productDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("ProductDetails creation failed");
        }
    }

    @PutMapping("/{productDetailsId}")
    private ResponseEntity<?> updateProductDetails(@RequestBody ProductDetailsResquest productDetailsResquest,@PathVariable("productDetailsId") int productDetailsId){
        ProductDetails productDetails=productDetailsService.getById(productDetailsId);
        try {
            Product product=productService.getById(productDetailsResquest.getProductId());
            Color color=colorService.getById(productDetailsResquest.getColorId());
            Size size=sizeService.getById(productDetailsResquest.getSizeId());

            if (product.getProductStatus()<4){
                productDetails.setProduct(product);
            }else {
                return ResponseEntity.ok("Sản phẩm này không tồn tại!");
            }
            if (color.isColorStatus()){
                productDetails.setColor(color);
            }else {
                return ResponseEntity.ok("Color này không tồn tại!");
            }
            if (size.isSizeStatus()){
                productDetails.setSize(size);
            }else {
                return ResponseEntity.ok("Size này không tồn tại!");
            }
            productDetails.setQuantity(productDetailsResquest.getQuantity());
            productDetails.setProductDetailsStatus(productDetailsResquest.getProductDetailsStatus());
            productDetails=productDetailsService.saveAndUpdate(productDetails);
            return ResponseEntity.ok(productDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("ProductDetails creation failed");
        }
    }

    @GetMapping("lockProductDetails/{productDetailsId}")
    private ResponseEntity<?> lockProductDetails(@PathVariable("productDetailsId") int productDetailsId) {
        ProductDetails productDetails=productDetailsService.getById(productDetailsId);
        productDetails.setProductDetailsStatus(4);
        productDetailsService.saveAndUpdate(productDetails);
        return ResponseEntity.ok("Lock product details successfully!");
    }

//    @GetMapping("/searchProductDetails")
//    private List<ProductDetails> searchProductDetails(@RequestParam("name") String name,@RequestParam("searchBy") String searchBy) {
//        return productDetailsService.searchProductDetailsBy(searchBy,name);
//    }

    @GetMapping("/sortProductDetailsBy")
    private List<ProductDetails> sortProductDetailsByName(@RequestParam("direction") String direction,@RequestParam("sortBy") String sortBy) {
        return productDetailsService.sortProductDetailsBy(direction,sortBy);
    }

    @GetMapping("/pagging")
    private ResponseEntity<Map<String, Object>> pagination(@RequestParam(defaultValue = "0")
                                                           int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDetails> productDetails = productDetailsService.getPagging(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("ProductDetails", productDetails.getContent());
        data.put("TotalElement in page", productDetails.getTotalElements());
        data.put("Size", productDetails.getSize());
        data.put("Total page", productDetails.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
