package lk.ijse.deb.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    private String payment_id;

    private String order_id;

    private  double cashTenderd;
    private  double balance;
    private  String cashier;
    private  String paymentMethod;
    private String paymentStatus;


    public Payment(String paymentId, String orderId, double cashTenderd, String cashier, String paymentMethod, String paymentStatus) {
    }
}
