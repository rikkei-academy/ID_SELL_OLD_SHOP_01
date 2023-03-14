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
    public List<Carts> getAllCartStatusTrue() {
        return null;
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
    public Carts findByUsers_UsersIdAndState(int id, int state) {
        return cartRepository.findByUsers_UsersIdAndState(id,state);
    }
}
