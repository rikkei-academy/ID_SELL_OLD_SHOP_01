package m.Model.ServiceImp;

import m.Model.Entity.Color;
import m.Model.Responsitory.ColorResponsitory;
import m.Model.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColorServiceImp implements ColorService<Color,Integer> {
    @Autowired
    private ColorResponsitory colorResponsitory;
    @Override
    public List<Color> findAllColor() {
        return colorResponsitory.findAll();
    }

    @Override
    public Color saveAndUpdate(Color color) {
        return colorResponsitory.save(color);
    }

    @Override
    public List<Color> getAllColorStatusTrue() {
        return colorResponsitory.getAllColorStatusTrue();
    }

    @Override
    public void delete(Integer id) {
      colorResponsitory.deleteById(id);
    }

    @Override
    public Color getById(Integer id) {
        return colorResponsitory.findById(id).get();
    }

    @Override
    public List<Color> searchColorByColorName(String name) {
        return colorResponsitory.findByColorNameContaining(name);
    }

    @Override
    public List<Color> sortColorByColorName(String direction) {
        if (direction.equalsIgnoreCase("asc")){
            return colorResponsitory.findAll(Sort.by("colorName").ascending());
        }else {
            return colorResponsitory.findAll(Sort.by("colorName").descending());
        }

    }

    @Override
    public Page<Color> getPagging(Pageable pageable) {
        return colorResponsitory.findAll(pageable);
    }
}
