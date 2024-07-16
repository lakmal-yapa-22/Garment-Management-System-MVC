package lk.ijse.deb.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data   // getter setter and tostring
public class Product {
    private String id;
    private int qty;
    private double price;
    private  String type;
    private  String color;
    private String size;


}
