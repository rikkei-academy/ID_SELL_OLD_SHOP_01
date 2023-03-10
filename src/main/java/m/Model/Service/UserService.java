package m.Model.Service;

import m.Model.Entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService<T,V> {
    Users findByUsersName(String usersName);
    Users findByEmail(String email);
    boolean existsByUsersName(String usersName);
    boolean existsByUserEmail(String userEmail);

    List<T> getAll();
    T saveAndUpdate(T t);
    T findById(V id);

    List<Users> findByUsersNameContaining(String usersName);
    List<Users> findByUserEmailContaining(String email);
    List<Users> findByShippingAdressContaining(String shipping);
    List<Users> findByUserCompanyContaining(String company);
    List<Users> sortUserByUserName(String diraction);
    Page<Users> paggingUser(Pageable pageable);

    List<Users> filter(String filter,String name);

}
