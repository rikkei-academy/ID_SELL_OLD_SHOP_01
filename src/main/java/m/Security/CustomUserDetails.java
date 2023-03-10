package m.Security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import m.Model.Entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    private boolean userStatus;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    //Tu thong tin user chuyen sang thong tin CustomUserDetails
    public static CustomUserDetails mapUserToUserDetail(Users users) {
        //Lay cac quyen tu doi tuong user
        List<GrantedAuthority> listAuthorities = users.getListRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))
                .collect(Collectors.toList());
        return new CustomUserDetails(
             users.getUsersId(),
             users.getUsersName(),
             users.getUsersPassWord(),
             users.getUserEmail(),
             users.getUserPhone(), users.getUserCompany(),
             users.getBillingAddress(),
             users.getShippingAdress(),
             users.isUserStatus(),
             listAuthorities
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
