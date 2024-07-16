package lk.ijse.deb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetail {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;


   private double subTotal;
  private  int discountRate;
    private  double discount;
   private  double finalAmount;


    public OrderDetail(double finalAmount) {
    }
}
