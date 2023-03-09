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
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "colorId")
    private int colorId;
    @Column(name = "colorName")
    private String colorName;
    @Column(name = "colorStatus")
    private boolean colorStatus;
    @OneToMany(mappedBy = "color",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductDetails> productDetailsList=new ArrayList<>();

}
