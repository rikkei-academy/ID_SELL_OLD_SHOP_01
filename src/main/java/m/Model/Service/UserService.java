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
    List<Users> searchBy(String searchBy,String name);
    List<Users> sortUserByUserName(String diraction);
    Page<Users> paggingUser(Pageable pageable);

    List<Users> filter(String filter,String name);

}
