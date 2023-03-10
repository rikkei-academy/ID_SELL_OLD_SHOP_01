package m.Model.ServiceImp;

import m.Model.Entity.Users;
import m.Model.Responsitory.UserResponsitory;
import m.Model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService<Users,Integer> {

    @Autowired
    private UserResponsitory userResponsitory;

    @Override
    public Users findByUsersName(String usersName) {
        return userResponsitory.findByUsersName(usersName);
    }

    @Override
    public Users findByEmail(String email) {
        return userResponsitory.findByUserEmail(email);
    }

    @Override
    public boolean existsByUsersName(String usersName) {
        return userResponsitory.existsByUsersName(usersName);
    }

    @Override
    public boolean existsByUserEmail(String userEmail) {
        return userResponsitory.existsByUserEmail(userEmail);
    }

    @Override
    public List<Users> getAll() {
        return userResponsitory.findAll();
    }

    @Override
    public Users saveAndUpdate(Users users) {
        return userResponsitory.save(users);
    }


    @Override
    public Users findById(Integer id) {
        return userResponsitory.findById(id).get();
    }

    @Override
    public List<Users> findByUsersNameContaining(String usersName) {
        return userResponsitory.findByUsersNameContaining(usersName);
    }

    @Override
    public List<Users> findByUserEmailContaining(String email) {
        return userResponsitory.findByUserEmailContaining(email);
    }

    @Override
    public List<Users> findByShippingAdressContaining(String shipping) {
        return userResponsitory.findByShippingAdressContaining(shipping);
    }

    @Override
    public List<Users> findByUserCompanyContaining(String company) {
        return userResponsitory.findByUserCompanyContaining(company);
    }

    @Override
    public List<Users> sortUserByUserName(String diraction) {
        if (diraction.equals("asc")) {
            return userResponsitory.findAll(Sort.by("usersName").ascending());
        } else {
            return userResponsitory.findAll(Sort.by("usersName").descending());
        }
    }

    @Override
    public Page<Users> paggingUser(Pageable pageable) {
        return userResponsitory.findAll(pageable);
    }

    @Override
        public List<Users> filter(String filter,String name) {
        List<Users> list = new ArrayList<>();
            if (filter.equalsIgnoreCase("company")){
                list = userResponsitory.findByUserCompany(name);
            } else if (filter.equalsIgnoreCase("billAddress")) {
                list = userResponsitory.findByBillingAddress(name);
            }else {
                list = userResponsitory.findByShippingAdress(name);
            }

        return list;
    }
}
