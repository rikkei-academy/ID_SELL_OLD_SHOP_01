package m.Model.Responsitory;

import m.Model.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SizeResponsitory extends JpaRepository<Size,Integer> {
    List<Size> findBySizeNameContaining(String name);
    @Query(value = "from Size s where s.sizeStatus=true ")
    List<Size> getAllSizeStatusTrue();
}
