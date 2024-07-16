package lk.ijse.deb.model.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderTm1 {
    private String orderId;
    private String BuyerId;
    private Date date;
    private Time time;

}
