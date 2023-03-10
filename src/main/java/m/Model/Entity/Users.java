package m.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usersId")
    private int usersId;
    @Column(name = "usersName")
    private String usersName;
    @Column(name = "usersPassWord")
    private String usersPassWord;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userPhone")
    private String userPhone;
    @Column(name = "userCompany")
    private String userCompany;
    @Column(name = "billingAddress")
    private String billingAddress;
    @Column(name = "shippingAdress")
    private String shippingAdress;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId"))
    private Set<Roles> listRoles = new HashSet<>();
    @Column(name = "userStatus")
    private boolean userStatus;


}
