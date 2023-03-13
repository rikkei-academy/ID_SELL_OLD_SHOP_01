package m.PayLoad.Request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailsResquest {
    private int productId;
    private int sizeId;
    private int colorId;
    private int quantity;
    private int productDetailsStatus;
}
