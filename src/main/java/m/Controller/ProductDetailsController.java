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


}
