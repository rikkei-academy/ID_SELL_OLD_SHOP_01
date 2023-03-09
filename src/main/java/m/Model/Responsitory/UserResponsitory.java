package m.Model.Responsitory;

import m.Model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResponsitory extends JpaRepository<Users,Integer> {
Users findByUsersName(String usersName);
    Users findByUserEmail(String userEmail);
boolean existsByUsersName(String usersName);
boolean existsByUserEmail(String userEmail);
List<Users> findByUsersNameContaining(String usersName);
    List<Users> findByUserEmailContaining(String email);
    List<Users> findByShippingAdressContaining(String shipping);
    List<Users> findByUserCompanyContaining(String company);



}
