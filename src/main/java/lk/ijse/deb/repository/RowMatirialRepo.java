package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.tm.RowMatirialTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMatirialRepo {
    public static List<RowMatirialTm> getAll() throws SQLException {
        String sql = "SELECT * FROM RawMaterial";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<RowMatirialTm> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String invoiceNumber = resultSet.getString(1);
            String location = resultSet.getString(2);
            String  description = resultSet.getString(3);
            int qty= resultSet.getInt(4);




            RowMatirialTm rowMatirial = new RowMatirialTm(invoiceNumber,location,description,qty);
            cusList.add(rowMatirial);
        }
        return cusList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM RawMaterial  WHERE invoice_number  = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(RowMatirialTm rowMatirial) throws SQLException {
        String sql = "UPDATE RawMaterial SET location = ?, description  = ? ,qty = ? WHERE invoice_number  = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, rowMatirial.getLocation());
        pstm.setObject(2, rowMatirial.getDescription());
        pstm.setObject(3, rowMatirial.getQty());
        pstm.setObject(4, rowMatirial.getInvoiceNumber());

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(RowMatirialTm rowMatirial) throws SQLException {
        String sql = "INSERT INTO RawMaterial VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, rowMatirial.getInvoiceNumber());
        pstm.setObject(2, rowMatirial.getLocation());
        pstm.setObject(3, rowMatirial.getDescription());
        pstm.setObject(4, rowMatirial.getQty());

        return pstm.executeUpdate() > 0;
    }

    public static RowMatirialTm searchById(String id) throws SQLException {
        String sql = "SELECT * FROM RawMaterial WHERE  invoice_number = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String invoiceNumber = resultSet.getString(1);
            String location = resultSet.getString(2);
            String description = resultSet.getString(3);
            int qty = resultSet.getInt(4);

            RowMatirialTm rowMatirial= new RowMatirialTm(invoiceNumber,location,description,qty);

            return rowMatirial;
        }

        return null;
    }
}
