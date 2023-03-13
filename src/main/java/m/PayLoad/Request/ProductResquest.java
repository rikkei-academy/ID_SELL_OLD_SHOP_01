package m.PayLoad.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import m.Model.Entity.Catalog;
import m.Model.Entity.Collection;
import m.Model.Entity.Gender;
import m.Model.Entity.ProductDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class ProductResquest {
    private String productName;
    private int catalogId;
    private int genderId;
    private int collectionId;
    private String productImage;
    private String productTitle;
    private String productDescription;
    private double productPrice;
    private double discount;
    private int productStatus;

}
