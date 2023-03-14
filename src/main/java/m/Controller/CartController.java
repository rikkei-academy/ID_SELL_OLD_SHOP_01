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
import m.PayLoad.Response.CartDto;
import m.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping()
    private List<CartDto> getAllCart() {
        List<Carts> cartsList = cartService.findAllCart();
        List<CartDto> cartDtosList = new ArrayList<>();
        String status = "";
        for (Carts c : cartsList) {
            if (c.getState() == 0) {
                status = "Trong giỏ hàng";
            } else if (c.getState() == 1) {
                status = "Đang chờ duyệt";
            } else if (c.getState() == 2) {
                status = "Đang chuẩn bị hàng";
            } else if (c.getState() == 3) {
                status = "Đang giao hàng";
            } else if (c.getState() == 4) {
                status = "Giao hàng thành công";
            } else {
                status = "Đã huỷ đơn hàng";
            }
            CartDto cartDto = new CartDto(c.getUsers().getUsersName(),
                    c.getSubtotal(), status);
            cartDtosList.add(cartDto);
        }
        return cartDtosList;
    }


    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest) {
        CustomUserDetails users = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (users != null) {
            Carts carts = cartService.findByUsers_UsersIdAndState(users.getUsersId(), 0);
            if (carts == null) {
                Carts newCart = new Carts();
                newCart.setUsers(userService.findById(users.getUsersId()));
                newCart.setState(0);
                carts = cartService.saveAndUpdate(newCart);
            }
            try {
                CartDetails cartDetails = new CartDetails();
                cartDetails.setCarts(carts);
                ProductDetails productDetails = productDetailsService.getById(cartRequest.getProductDetailId());
                cartDetails.setProductDetails(productDetails);
                cartDetails.setCartDetailsPrice(productDetails.getProduct().getProductPrice() * cartRequest.getQuantity());
                cartDetails.setCartDetailsQuantity(cartRequest.getQuantity());
                for (CartDetails c : carts.getCartDetailsList()) {
                    if (c.getProductDetails().getProductDetailsId() == productDetails.getProductDetailsId()) {
                        cartDetails.setCartDetailsQuantity(cartRequest.getQuantity() + c.getCartDetailsQuantity());
                        cartDetails.setCartDetailsPrice(productDetails.getProduct().getProductPrice() * cartDetails.getCartDetailsQuantity());
                        cartDetails.setCartDetailsId(c.getCartDetailsId());
                    }
                }
                cartDetails = cartDetailService.saveAndUpdate(cartDetails);
                return ResponseEntity.ok(cartDetails);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.ok("CartDetail create fail");
            }
        } else {
            return ResponseEntity.ok("Bạn cần đăng nhập trước khi thực hiện thao tác này");
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
        Carts carts = cartService.findByUsers_UsersIdAndState(users.getUsersId(), 0);
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
