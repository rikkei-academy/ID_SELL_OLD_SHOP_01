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
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;
    @Column(name = "productName")
    private String productName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogId")
    private Catalog catalog;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genderId")
    private Gender gender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collectionId")
    private Collection collection;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductDetails> productDetailsList=new ArrayList<>();
    @Column(name = "productImage")
    private String productImage;
    @Column(name = "productTitle")
    private String productTitle;
    @Column(name = "productDescription")
    private  String productDescription;
    @Column(name = "productPrice")
    private double productPrice;
    @Column(name = "discount")
    private double discount;
    @Column(name = "productStatus")
    private int productStatus;

}
