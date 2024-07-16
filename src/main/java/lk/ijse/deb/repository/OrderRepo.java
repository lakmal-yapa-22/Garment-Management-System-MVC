package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.Order;
import lk.ijse.deb.model.Order1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepo {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public static boolean save(Order order) throws SQLException {

        String sql = "INSERT INTO  Orders VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, order.getOrderId());
        pstm.setString(2, order.getBuyerId());
        pstm.setDate(3, order.getDate());
        pstm.setTime(4,order.getTime());


        return pstm.executeUpdate() > 0;
    }

    public static List<Order1> getAll() throws SQLException {
        String sql = "SELECT * FROM Orders";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Order1> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String b_id = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            Time time = resultSet.getTime(4);


            Order1 order = new Order1(id,b_id, (java.sql.Date) date,time);
            cusList.add(order);
        }
        return cusList;
    }



}