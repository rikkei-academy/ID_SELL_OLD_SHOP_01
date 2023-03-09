package m.Model.Service;

import m.Model.Entity.Users;

import java.util.List;

public interface UserService<T,V> {
    Users findByUsersName(String usersName);
    boolean existsByUsersName(String usersName);
    boolean existsByUserEmail(String userEmail);

    List<T> getAll();
    T saveAndUpdate(T t);
    T findById(V id);

}
