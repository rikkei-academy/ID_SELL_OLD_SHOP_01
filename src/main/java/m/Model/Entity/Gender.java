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
@Table(name = "gender")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genderId")
    private int genderId;
    @Column(name = "genderName")
    private String genderName;
    @Column(name = "genderStatus")
    private boolean genderStatus;
    @OneToMany(mappedBy = "gender",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList=new ArrayList<>();
}
