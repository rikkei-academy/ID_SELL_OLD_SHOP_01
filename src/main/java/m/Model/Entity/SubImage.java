package m.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subImage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subImageId")
    private  int subImageId;
    @Column(name = "subImageLink")
    private String subImageLink;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailsId")
    private ProductDetails productDetails;
}
