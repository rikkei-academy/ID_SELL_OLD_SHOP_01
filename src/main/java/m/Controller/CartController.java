package m.Controller;

import m.Model.Entity.CartDetails;
import m.Model.Entity.Carts;
import m.Model.Entity.ProductDetails;
import m.Model.Entity.Users;
import m.Model.Service.CartDetailService;
import m.Model.Service.CartService;
import m.Model.Service.ProductDetailsService;
import m.Model.Service.UserService;
import m.PayLoad.Request.CartRequest;
import m.PayLoad.Request.CheckoutResquest;
import m.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/CartController")
public class CartController {
    @Autowired
    private CartService<Carts, Integer> cartService;
    @Autowired
    private CartDetailService<CartDetails, Integer> cartDetailService;

    @Autowired
    private UserService<Users, Integer> userService;
    @Autowired
    private ProductDetailsService<ProductDetails, Integer> productDetailsService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest) {
        CustomUserDetails users = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Carts carts = cartService.findByUsers_UsersIdAndState(users.getUsersId(), 0);
        if (carts == null) {
            carts.setUsers(userService.findById(users.getUsersId()));
            carts.setState(0);
            carts = cartService.saveAndUpdate(carts);
        }
        try {
            CartDetails cartDetails = new CartDetails();
            cartDetails.setCarts(carts);
            ProductDetails productDetails = productDetailsService.getById(cartRequest.getProductDetailId());
            cartDetails.setProductDetails(productDetails);
            for (CartDetails c : carts.getCartDetailsList()) {
                if (c.getProductDetails() == productDetails) {
                    cartDetails.setCartDetailsQuantity(cartRequest.getQuantity() + c.getCartDetailsQuantity());
                } else {
                    cartDetails.setCartDetailsQuantity(cartRequest.getQuantity());
                }
            }
            cartDetails.setCartDetailsPrice(productDetails.getProduct().getProductPrice() * cartRequest.getQuantity());
            cartDetails = cartDetailService.saveAndUpdate(cartDetails);
            return ResponseEntity.ok(cartDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("CartDetail create fail");
        }
    }

    public double totalAmount(List<CartDetails> cartList) {
        double totalAmount = 0;
        for (CartDetails cart : cartList) {
            totalAmount += cart.getCartDetailsPrice();
        }
        return totalAmount;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutResquest checkoutResquest) {
        CustomUserDetails users = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Carts carts = cartService.findByUsers_UsersId(users.getUsersId());
        carts.setUsers(userService.findById(users.getUsersId()));
        carts.setSubtotal(totalAmount(carts.getCartDetailsList()));
        carts.setEmail(checkoutResquest.getEmail());
        carts.setFristName(checkoutResquest.getFristName());
        carts.setLastName(checkoutResquest.getLastName());
        carts.setPhone(checkoutResquest.getPhone());
        carts.setCompanyName(checkoutResquest.getCompanyName());
        carts.setCountry(checkoutResquest.getCountry());
        carts.setZipCode(checkoutResquest.getZipCode());
        carts.setHouseNumber(checkoutResquest.getHouseNumber());
        carts.setStreetName(checkoutResquest.getStreetName());
        carts.setApartment(checkoutResquest.getApartment());
        carts.setCity(checkoutResquest.getCity());
        carts.setState(1);
        cartService.saveAndUpdate(carts);
        return ResponseEntity.ok("Check out successfully!");
    }




}
