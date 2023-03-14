package m.Model.ServiceImp;

import m.Model.Entity.CartDetails;
import m.Model.Responsitory.CartDetailRepository;
import m.Model.Service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImp implements CartDetailService<CartDetails, Integer> {
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Override
    public List<CartDetails> findAllCartDetail() {
        return cartDetailRepository.findAll();
    }

    @Override
    public CartDetails saveAndUpdate(CartDetails cartDetails) {
        return cartDetailRepository.save(cartDetails);
    }

    @Override
    public void delete(int id) {
   cartDetailRepository.deleteById(id);
    }

    @Override
    public CartDetails getById(int id) {
        return cartDetailRepository.findById(id).get();
    }
}
