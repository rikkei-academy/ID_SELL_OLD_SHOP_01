package m.PayLoad.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailsDTO {
    private String productName;
    private String sizeName;
    private String colorName;
    private int quantity;
    private String productDetailsStatus;
}
