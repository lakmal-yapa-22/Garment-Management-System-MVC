package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.Buyer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerRepo {

    public static List<Buyer> getAll() throws SQLException {
        String sql = "SELECT * FROM Buyer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Buyer> buyerList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String tel = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String company = resultSet.getString(5);

            Buyer buyer = new Buyer(id, tel, address, email, company);
            buyerList.add(buyer);
        }
        return buyerList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Buyer WHERE buyer_id    = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static Buyer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Buyer WHERE buyer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String buyer_id = resultSet.getString(1);
            String tel = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String company = resultSet.getString(5);

            Buyer buyer = new Buyer(buyer_id, tel, address, email, company);

            return buyer;
        }

        return null;
    }

    public static boolean update(Buyer buyer) throws SQLException {
        String sql = "UPDATE Buyer SET contact_number = ?, address = ?, email = ?, company_name = ? WHERE buyer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, buyer.getTel());
        pstm.setObject(2, buyer.getAddress());
        pstm.setObject(3, buyer.getEmail());
        pstm.setObject(4, buyer.getCompany());
        pstm.setObject(5, buyer.getId());

        return pstm.executeUpdate() > 0;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT buyer_id FROM  Buyer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }



    public static boolean save(Buyer buyer) throws SQLException {
        String sql = "INSERT INTO Buyer VALUES(?, ?, ?, ?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, buyer.getId());
        pstm.setObject(2, buyer.getTel());
        pstm.setObject(3, buyer.getAddress());
        pstm.setObject(4, buyer.getEmail());
        pstm.setObject(5,buyer.getCompany());

        return pstm.executeUpdate() > 0;
    }
}
