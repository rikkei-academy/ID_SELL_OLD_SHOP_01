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
@Table(name = "catalog")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalogId")
    private int catalogId;
    @Column(name = "catalogName")
    private String catalogName;
    @Column(name = "catalogStatus")
    private boolean catalogStatus;
    @OneToMany(mappedBy = "catalog",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList=new ArrayList<>();
}
