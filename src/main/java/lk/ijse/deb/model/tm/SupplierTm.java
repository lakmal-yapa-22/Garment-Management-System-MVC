package lk.ijse.deb.model.tm;
import lombok.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class SupplierTm {
    private String  id;
    private  String name;
    private  String contactNumber;
    private String email;
    private String address;




}
