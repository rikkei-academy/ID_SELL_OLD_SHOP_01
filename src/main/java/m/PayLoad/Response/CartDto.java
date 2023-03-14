package m.PayLoad.Response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDto {
    private String usersName;
    private double subtotal;
    private String state;
}
