package lk.ijse.deb.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Supplier {
    private String  id;
    private  String name;
    private  String contactNumber;
    private String email;
    private String address;


}
