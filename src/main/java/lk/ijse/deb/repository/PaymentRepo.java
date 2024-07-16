package lk.ijse.deb.repository;


import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.OrderDetail;
import lk.ijse.deb.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT payment_id   FROM  payments ORDER BY payment_id  DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments VALUES(?, ?, ?, ?,? ,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getPayment_id());
        pstm.setObject(2,payment.getOrder_id());
        pstm.setObject(3,payment.getCashTenderd());
        pstm.setObject(4,payment.getBalance());
        pstm.setObject(5,payment.getCashier());
   pstm.setObject(6,payment.getPaymentMethod());
      pstm.setObject(7,payment.getPaymentStatus());



        return pstm.executeUpdate() > 0;
    }

    public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payments";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> payList = new ArrayList<>();

        while (resultSet.next()) {


            String payment_id=resultSet.getString(1);
            String order_id=resultSet.getString(2);
            double cashTenderd=resultSet.getDouble(3);
            double balance=resultSet.getDouble(4);
            String cashier=resultSet.getString(5);
            String paymentMethod=resultSet.getString(6);
            String paymentStatus=resultSet.getString(7);



            Payment payment = new Payment(payment_id,order_id,cashTenderd,balance,cashier,paymentMethod,paymentStatus);
            payList.add(payment);
        }
        return payList;
    }

    public static String searchByOrderId(String orderId) throws SQLException {
        String sql = "SELECT paymentStatus FROM payments WHERE order_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, orderId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String paymentStatus = resultSet.getString("paymentStatus");
          //  System.out.println(paymentStatus); // You can remove this line if not needed for debugging
            return paymentStatus;
        }

        return null;
    }

}
