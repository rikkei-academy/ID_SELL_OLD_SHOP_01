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
    public List<Users> searchBy(String searchBy, String name) {
        if (searchBy.equalsIgnoreCase("name")){
            return userResponsitory.findByUsersNameContaining(name);
        } else if (searchBy.equalsIgnoreCase("email")) {
            return userResponsitory.findByUserEmailContaining(name);
        } else if (searchBy.equalsIgnoreCase("shipping")) {
            return userResponsitory.findByShippingAdressContaining(name);
        }else {
            return userResponsitory.findByUserCompanyContaining(name);
        }

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
            if (filter.equalsIgnoreCase("company")){
                return userResponsitory.findByUserCompany(name);
            } else if (filter.equalsIgnoreCase("billAddress")) {
                return userResponsitory.findByBillingAddress(name);
            }else {
                return userResponsitory.findByShippingAdress(name);
            }
    }
}
