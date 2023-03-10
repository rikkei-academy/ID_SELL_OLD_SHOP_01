package m.Model.ServiceImp;

import m.Model.Entity.ERole;
import m.Model.Entity.Roles;
import m.Model.Responsitory.RoleRepository;
import m.Model.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class RoleServiceIpm implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {

        return roleRepository.findByRoleName(roleName);
    }

}
