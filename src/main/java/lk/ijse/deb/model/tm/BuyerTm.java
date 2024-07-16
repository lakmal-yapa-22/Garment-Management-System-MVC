package lk.ijse.deb.model.tm;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class BuyerTm {
    private  String id;
    private  String tel;
    private  String address;
    private  String email;
    private  String company;
}
