package m.PayLoad.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartRequest {
    private int productDetailId;
    private int quantity;
}
