package m.Model.ServiceImp;

import m.Model.Entity.Carts;
import m.Model.Responsitory.CartRepository;
import m.Model.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService<Carts,Integer> {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Carts> findAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Carts saveAndUpdate(Carts carts) {
        return cartRepository.save(carts);
    }

    @Override
    public List<Carts> getCartInShoppingCart() {
        return cartRepository.findByState0();
    }


    @Override
    public void delete(int id) {
   cartRepository.deleteById(id);
    }

    @Override
    public Carts getById(int id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public List<Carts> findAllByUsers_UsersIdAndState(int userdId, int state) {
        return cartRepository.findAllByUsers_UsersIdAndState(userdId,state);
    }

    @Override
    public Carts findByUsers_UsersId(Integer integer) {
        return cartRepository.findByUsers_UsersId(integer);
    }

    @Override
    public List<Carts> searchStateBy(String name) {
        if (name.equalsIgnoreCase("đợi")){
            return cartRepository.findByState1();
        } else if (name.equalsIgnoreCase("chuẩn bị")) {
            return cartRepository.findByState2();
        }else if (name.equalsIgnoreCase("giao")) {
            return cartRepository.findByState3();
        }else if (name.equalsIgnoreCase("thành công")) {
            return cartRepository.findByState4();
        }else {
            return cartRepository.findByState();
        }
    }

    @Override
    public Carts findByUsers_UsersIdAndState(int id, int state) {
        return cartRepository.getCartByUserandState(id,state);
    }
}
