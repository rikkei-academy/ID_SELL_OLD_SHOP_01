package m.Model.Responsitory;

import m.Model.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ColorResponsitory extends JpaRepository<Color,Integer>
{
    List<Color> findByColorNameContaining(String name);
    @Query(value = "from Color c where c.colorStatus=true ")
    List<Color> getAllColorStatusTrue();
}
