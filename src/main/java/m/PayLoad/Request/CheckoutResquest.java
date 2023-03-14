package m.PayLoad.Request;


import lombok.AllArgsConstructor;
import lombok.Data;


import m.Model.Entity.Users;



@Data
@AllArgsConstructor
public class CheckoutResquest {

    private Users users;
    private String email;

    private String fristName;

    private String lastName;

    private String phone;

    private String companyName;

    private String country;

    private String zipCode;

    private String houseNumber;
    private String apartment;
    private String streetName;
    private String city;

}
