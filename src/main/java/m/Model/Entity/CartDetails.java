package m.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cartDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartDetailsId")
    private int cartDetailsId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private Carts carts;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailsId")
    private ProductDetails productDetails;
    @Column(name = "cartDetailsPrice")
    private double cartDetailsPrice;
    @Column(name = "cartDetailsQuantity")
    private int cartDetailsQuantity;
}
