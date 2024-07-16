package lk.ijse.deb.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
    private double SubTotal;
    private  int discountRate;
    private double disscount;
    private double totalAmount;

    private JFXButton btnRemove;







}