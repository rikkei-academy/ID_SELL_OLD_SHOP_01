package m.Security;

import m.Model.Entity.Users;
import m.Model.Responsitory.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
   @Autowired
   private UserResponsitory userResponsitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userResponsitory.findByUsersName(username);
        if(user==null){
            throw  new UsernameNotFoundException("User not found");
        }
        return CustomUserDetails.mapUserToUserDetail(user);
    }
}
