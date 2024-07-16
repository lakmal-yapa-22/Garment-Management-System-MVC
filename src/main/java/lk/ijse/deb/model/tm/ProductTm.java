package lk.ijse.deb.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ProductTm {
    private String id;
    private int qty;
    private double price;
    private  String type;
    private  String color;
    private  String size;


}
