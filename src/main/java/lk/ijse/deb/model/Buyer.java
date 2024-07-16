package lk.ijse.deb.model;
import lk.ijse.deb.db.DbConnection;
import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Buyer {
    private  String id;
    private  String tel;
    private  String address;
    private  String email;
    private  String company;


}
