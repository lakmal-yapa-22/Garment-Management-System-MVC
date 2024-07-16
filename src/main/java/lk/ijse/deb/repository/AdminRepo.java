package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.tm.AdminTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRepo {
    public static boolean saveAdmin(AdminTm admin) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Admin VALUES(?,AES_ENCRYPT(?,'star01'),?,?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, admin.getEmail());
        pstm.setString(2, admin.getUsername());
        pstm.setString(3, admin.getPassword());
        pstm.setString(4, "ADMIN");

        if (pstm.executeUpdate() > 0) {
            return true;
        }else{
            return false;
        }
    }
}
