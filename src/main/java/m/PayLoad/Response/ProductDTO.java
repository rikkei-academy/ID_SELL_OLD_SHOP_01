package m.PayLoad.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private String catalogName;
    private String genderName;
    private String collectionName;
    private String productImage;
    private String productTitle;
    private String productDescription;
    private double productPrice;
    private String discount;
    private String productStatus;
}
