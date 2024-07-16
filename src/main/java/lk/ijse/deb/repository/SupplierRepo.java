package lk.ijse.deb.repository;


import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.Supplier;
import lk.ijse.deb.model.tm.SupplierTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNumber= resultSet.getString(3);
            String email= resultSet.getString(4);
            String address = resultSet.getString(5);


            Supplier supplier = new Supplier(id, name, contactNumber,email,address);
            supList.add(supplier);
        }
        return supList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE supplier_id   = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static SupplierTm searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE supplier_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNumber = resultSet.getString(3);
            String email = resultSet.getString(4);
            String address = resultSet.getString(5);


            SupplierTm supplier = new SupplierTm(supp_id,name,contactNumber,email,address);

            return supplier;
        }

        return null;
    }

    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO  Supplier  VALUES(?, ?, ?, ?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getContactNumber());
        pstm.setObject(4, supplier.getEmail());
        pstm.setObject(5,supplier.getAddress());

        return pstm.executeUpdate() > 0;
    }


    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE  Supplier  SET  supplier_name   = ?, contact_number = ?,  email  = ?, address = ?  WHERE supplier_id  = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getContactNumber());
        pstm.setObject(3, supplier.getEmail());
        pstm.setObject(4, supplier.getAddress());

        pstm.setObject(5,supplier.getId());

        return pstm.executeUpdate() > 0;
    }
}
