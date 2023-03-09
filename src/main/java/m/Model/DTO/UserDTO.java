package m.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
@Data
@AllArgsConstructor
public class UserDTO {

    private String usersName;

    private String userEmail;

    private String userPhone;

    private String userCompany;

    private String billingAddress;

    private String shippingAdress;

    private String permission;
    private String status;
}
