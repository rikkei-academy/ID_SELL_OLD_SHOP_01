package m.Model.Responsitory;

import m.Model.Entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderResponsitory extends JpaRepository<Gender,Integer> {
    List<Gender> findByGenderNameContaining(String name);
    @Query(value = "from Gender g where g.genderStatus=true ")
    List<Gender> getAllGenderStatusTrue();
}
