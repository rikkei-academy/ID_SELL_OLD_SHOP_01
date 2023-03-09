package m.Security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import m.Model.Entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Collection;
@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private int usersId;

    private String usersName;
@JsonIgnore
    private String usersPassWord;

    private String userEmail;

    private String userPhone;

    private String userCompany;

    private String billingAddress;

    private String shippingAdress;

    private int permission;

    private boolean userStatus;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public  static CustomUserDetails mapUserToUserDetails(Users users){
     return new CustomUserDetails(
             users.getUsersId(),
             users.getUsersName(),
             users.getUsersPassWord(),
             users.getUserEmail(),
             users.getUserPhone(), users.getUserCompany(),
             users.getBillingAddress(),
             users.getShippingAdress(),
             users.getPermission(),
             users.isUserStatus()

     );
    }

    @Override
    public String getPassword() {
        return this.usersPassWord;
    }

    @Override
    public String getUsername() {
        return this.usersName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
