package lk.ijse.deb.model.tm;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class RowMatirialTm {
    private  String invoiceNumber;
    private  String location;
    private  String description;
    private  int qty;


}
