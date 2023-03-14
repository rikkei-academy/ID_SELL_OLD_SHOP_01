package m.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private int cartId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usersId")
    private Users users;
    @OneToMany(mappedBy = "carts",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartDetails > cartDetailsList=new ArrayList<>();
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "email")
    private String email;
    @Column(name = "fristName")
    private String fristName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "country")
    private String country;
    @Column(name = "zipCode")
    private String zipCode;
    @Column(name = "houseNumber")
    private String houseNumber;
    @Column(name = "apartment")
    private String apartment;
    @Column(name = "streetName")
    private String streetName;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private int state;
}
