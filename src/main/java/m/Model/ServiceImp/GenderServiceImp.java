package m.Model.ServiceImp;

import m.Model.Entity.Gender;
import m.Model.Responsitory.GenderResponsitory;
import m.Model.Service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImp implements GenderService<Gender,Integer> {
    @Autowired
    private GenderResponsitory genderResponsitory;
    @Override
    public List<Gender> findAllGender() {
        return genderResponsitory.findAll();
    }

    @Override
    public Gender saveAndUpdate(Gender gender) {
        return genderResponsitory.save(gender);
    }

    @Override
    public List<Gender> getAllGenderStatusTrue() {
        return genderResponsitory.getAllGenderStatusTrue();
    }

    @Override
    public void delete(Integer id) {
     genderResponsitory.deleteById(id);
    }

    @Override
    public Gender getById(Integer id) {
        return genderResponsitory.findById(id).get();
    }

    @Override
    public List<Gender> searchGenderByGenderName(String name) {
        return genderResponsitory.findByGenderNameContaining(name);
    }

    @Override
    public List<Gender> sortGenderByGenderName(String direction) {
        if (direction.equalsIgnoreCase("asc")){
            return genderResponsitory.findAll(Sort.by("genderName").ascending());
        }else {
            return genderResponsitory.findAll(Sort.by("genderName").ascending());
        }

    }

    @Override
    public Page<Gender> getPagging(Pageable pageable) {
        return genderResponsitory.findAll(pageable);
    }
}
