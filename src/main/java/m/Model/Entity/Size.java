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
@Table(name = "size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sizeId")
    private int sizeId;
    @Column(name = "sizeName")
    private String sizeName;
    @Column(name = "sizeStatus")
    private boolean sizeStatus;
    @OneToMany(mappedBy = "size",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductDetails> productDetailsList=new ArrayList<>();
}
