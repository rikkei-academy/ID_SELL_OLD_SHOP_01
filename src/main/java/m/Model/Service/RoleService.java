package m.Model.Service;



import m.Model.Entity.ERole;
import m.Model.Entity.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Roles> findByRoleName(ERole roleName);
}
