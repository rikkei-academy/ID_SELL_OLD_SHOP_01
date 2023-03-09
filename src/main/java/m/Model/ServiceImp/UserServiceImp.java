package m.Model.ServiceImp;

import m.Model.Entity.Users;
import m.Model.Responsitory.UserResponsitory;
import m.Model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
