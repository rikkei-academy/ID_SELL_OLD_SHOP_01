package m.Model.Responsitory;

import m.Model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResponsitory extends JpaRepository<Users,Integer> {
Users findByUsersName(String usersName);
boolean existsByUsersName(String usersName);
boolean existsByUserEmail(String userEmail);

}
