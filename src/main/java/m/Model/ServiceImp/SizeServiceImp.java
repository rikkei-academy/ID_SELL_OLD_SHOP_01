package m.Model.ServiceImp;

import m.Model.Entity.Size;
import m.Model.Responsitory.SizeResponsitory;
import m.Model.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImp implements SizeService<Size, Integer> {
    @Autowired
    private SizeResponsitory sizeResponsitory;

    @Override
    public List<Size> findAllSize() {
        return sizeResponsitory.findAll();
    }

    @Override
    public Size saveAndUpdate(Size size) {
        return sizeResponsitory.save(size);
    }

    @Override
    public List<Size> getAllSizeStatusTrue() {
        return sizeResponsitory.getAllSizeStatusTrue();
    }

    @Override
    public void delete(Integer id) {
    sizeResponsitory.deleteById(id);
    }

    @Override
    public Size getById(Integer id) {
        return sizeResponsitory.findById(id).get();
    }

    @Override
    public List<Size> searchSizeBySizeName(String name) {
        return sizeResponsitory.findBySizeNameContaining(name);
    }

    @Override
    public List<Size> sortSizeBySizeName(String direction) {
        if (direction.equalsIgnoreCase("asc")){
            return sizeResponsitory.findAll(Sort.by("sizeName").ascending());
        }else {
            return sizeResponsitory.findAll(Sort.by("sizeName").ascending());
        }

    }

    @Override
    public Page<Size> getPagging(Pageable pageable) {
        return sizeResponsitory.findAll(pageable);
    }
}
