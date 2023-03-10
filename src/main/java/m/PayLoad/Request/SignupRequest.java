package m.PayLoad.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Set;
@Data
@AllArgsConstructor
public class SignupRequest {
    private String usersName;

    private String usersPassWord;

    private String userEmail;

    private String userPhone;

    private String userCompany;

    private String billingAddress;

    private String shippingAdress;

    private String permission;

    private boolean userStatus;
    private Set<String> listRoles;

}
