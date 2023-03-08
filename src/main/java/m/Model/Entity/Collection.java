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
@Table(name = "collection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collectionId")
    private int collectionId;
    @Column(name = "collectionName")
    private String collectionName;
    @Column(name = "imageCollection")
    private String imageCollection;
    @Column(name = "collectionDescription")
    private String collectionDescription;
    @Column(name = "collectionStatus")
    private boolean collectionStatus;
    @OneToMany(mappedBy = "collection",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList=new ArrayList<>();
}
