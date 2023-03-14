package m.PayLoad.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartQuantityResquest {
    private int cartDetailsId;
    private int quantity;
}
