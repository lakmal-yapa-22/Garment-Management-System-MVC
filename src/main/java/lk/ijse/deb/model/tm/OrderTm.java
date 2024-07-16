package lk.ijse.deb.model.tm;


import lk.ijse.deb.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderTm {
    private String orderId;
    private String BuyerId;
    private Date date;
    private Time time;

    private double subTotal;
    private  int discountRate;
    private  double discount;
    private  double finalAmount;



}