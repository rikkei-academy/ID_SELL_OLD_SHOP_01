package m.Controller;

import m.Model.Entity.CartDetails;
import m.Model.Service.CartDetailService;
import m.PayLoad.Request.CartQuantityResquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/CartDetailsController")
public class CartDetailsController {

    @Autowired
    private CartDetailService<CartDetails,Integer> cartDetailService;

    @PatchMapping("/updateCartQuantity")
    private ResponseEntity<?> updateQuantity(@RequestBody CartQuantityResquest cartQuantityResquest){
        CartDetails cartDetails=cartDetailService.getById(cartQuantityResquest.getCartDetailsId());
        if (cartDetails.getCarts().getState()==0){
            cartDetails.setCartDetailsQuantity(cartQuantityResquest.getQuantity());
            cartDetailService.saveAndUpdate(cartDetails);
            return ResponseEntity.ok("Update quantity successfully!");
        }else {
            return ResponseEntity.ok("Unable to update quantity!");
        }

    }

    @DeleteMapping("/deleteCartDetail/{cartDetailsId}")
    private ResponseEntity<?> deleteCartDetail(@PathVariable("cartDetailsId") int cartDetailsId){
        CartDetails cartDetails=cartDetailService.getById(cartDetailsId);
        if (cartDetails.getCarts().getState()==1){
            cartDetailService.delete(cartDetails.getCartDetailsId());
            return ResponseEntity.ok("Delete order successfully!");
        }else {
            return ResponseEntity.ok("Unable to delete order!");
        }

    }

}
