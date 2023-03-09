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
@Table(name = "productDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productDetailsId")
    private int productDetailsId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sizeId")
    private Size size;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colorId")
    private Color color;
    @Column(name = "quantity")
    private int quantity;
    @OneToMany(mappedBy = "productDetails",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SubImage> subImageList=new ArrayList<>();
    @Column(name = "productDetailsStatus")
    private int productDetailsStatus;
}
