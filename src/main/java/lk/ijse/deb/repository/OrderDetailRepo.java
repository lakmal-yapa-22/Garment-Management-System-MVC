package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.OrderDetail;
import lk.ijse.deb.model.tm.OrderDetailTm;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepo {
    {
       // System.out.println("hi");
    }
    public static boolean save(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {
            boolean isSaved = save(od);
          //  System.out.println(isSaved+"isSave");
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(OrderDetail od) throws SQLException {

        {
      //      System.out.println("hi2");
        }

        String sql = "INSERT INTO OrderDetail VALUES(?, ?, ?, ? ,? ,? ,? ,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

       pstm.setString(1, od.getOrderId());
pstm.setString(2,od.getItemCode());
   pstm.setInt(3,od.getQty());
      pstm.setDouble(4,od.getUnitPrice());
    pstm.setDouble(5,od.getSubTotal());
pstm.setString(6, String.valueOf(od.getDiscountRate()));
      pstm.setDouble(7,od.getDiscount());
    pstm.setDouble(8,od.getFinalAmount());
      //  System.out.println("hi3");

        return pstm.executeUpdate() > 0;    //false ->  |
    }

    public static List<OrderDetail> getAll() throws SQLException {
        String sql = "SELECT * FROM OrderDetail ";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<OrderDetail> orderDetailList = new ArrayList<>();

        while (resultSet.next()) {



            String orderId= resultSet.getString(1);
           String itemCode= resultSet.getString(2);
          int qty= resultSet.getInt(3);
         double unitPrice= resultSet.getDouble(4);
           double subTotal= resultSet.getDouble(5);
          int discountRate=resultSet.getInt(6);
          double discount=resultSet.getDouble(7);
            double finalAmount=resultSet.getDouble(8);



            OrderDetail orderDetail=new OrderDetail(orderId,itemCode,qty,unitPrice,subTotal,discountRate,discount,finalAmount);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }
/*
    public static List<String> getOrderID() throws SQLException {
        String sql = "SELECT order_id  FROM  OrderDetail ";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

 */

    public static OrderDetail searchById(String id) throws SQLException {
        String sql = "SELECT * FROM OrderDetail  WHERE order_id   = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String orderId= resultSet.getString(1);
            String itemCode= resultSet.getString(2);
            int qty= resultSet.getInt(3);
            double unitPrice= resultSet.getDouble(4);
            double subTotal= resultSet.getDouble(5);
            int discountRate=resultSet.getInt(6);
            double discount=resultSet.getDouble(7);
            double finalAmount=resultSet.getDouble(8);

            OrderDetail orderDetail = new OrderDetail(orderId,itemCode,qty,unitPrice,subTotal,discountRate,discount,finalAmount);

            return orderDetail;
        }

        return null;
    }


    public static List<String> getIds() throws SQLException {
        String sql = "SELECT  order_id   FROM  OrderDetail ";
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

    public static List<OrderDetailTm> getAllOrderDetail() throws SQLException {
        String sql = "SELECT \n" +
                "    od.order_id,\n" +
                "    b.company_name,\n" +
                "    p.product_type,\n" +
                "    od.qty,\n" +
                "    p.color,\n" +
                "    p.size,\n" +
                "    od.discount_rate,\n" +
                "    od.finalAmount,\n" +
                "    IFNULL(pm.paymentStatus, 'no pay') AS paymentStatus\n" +
                "FROM \n" +
                "    OrderDetail od\n" +
                "LEFT JOIN \n" +
                "    Orders o ON od.order_id = o.order_id\n" +
                "LEFT JOIN \n" +
                "    Buyer b ON o.buyer_id = b.buyer_id\n" +
                "LEFT JOIN \n" +
                "    Product p ON od.product_id = p.product_id\n" +
                "LEFT JOIN \n" +
                "    payments pm ON od.order_id = pm.order_id";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<OrderDetailTm> orderDetailTmsList = new ArrayList<>();

        while (resultSet.next()) {
            String orderID = resultSet.getString(1);
            String companyName = resultSet.getString(2);
            String productType = resultSet.getString(3);
            int qty=resultSet.getInt(4);
            String color = resultSet.getString(5);
            String size=resultSet.getString(6);
            String discountRate =resultSet.getString(7);
            double finalAmount= resultSet.getDouble(8) ;
            String paymentStatus=resultSet.getString(9);

            OrderDetailTm orderDetailTm = new OrderDetailTm(orderID,companyName,productType,qty,color,size,discountRate,finalAmount,paymentStatus);
            orderDetailTmsList.add(orderDetailTm);


        }
        return orderDetailTmsList;
    }

    public static OrderDetailTm searchByOrder(String id) throws SQLException {
        String sql =
                "SELECT \n" +
                        "    od.order_id,\n" +
                        "    b.company_name,\n" +
                        "    p.product_type,\n" +
                        "    od.qty,\n" +
                        "    p.color,\n" +
                        "    p.size,\n" +
                        "    od.discount_rate,\n" +
                        "    od.finalAmount,\n" +
                        "    IFNULL(pm.paymentStatus, 'no pay') AS paymentStatus\n" +
                        "FROM \n" +
                        "    OrderDetail od\n" +
                        "LEFT JOIN \n" +
                        "    Orders o ON od.order_id = o.order_id\n" +
                        "LEFT JOIN \n" +
                        "    Buyer b ON o.buyer_id = b.buyer_id\n" +
                        "LEFT JOIN \n" +
                        "    Product p ON od.product_id = p.product_id\n" +
                        "LEFT JOIN \n" +
                        "    payments pm ON od.order_id = pm.order_id\n" +
                        "WHERE \n" +
                        "    od.order_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String orderID = resultSet.getString(1);
            String companyName = resultSet.getString(2);
            String productType = resultSet.getString(3);
            int qty=resultSet.getInt(4);
            String color = resultSet.getString(5);
            String size=resultSet.getString(6);
            String discountRate =resultSet.getString(7);
            double finalAmount= resultSet.getDouble(8) ;
            String paymentStatus=resultSet.getString(9);


            OrderDetailTm orderDetailTm = new OrderDetailTm(orderID,companyName,productType,qty,color,size,discountRate,finalAmount,paymentStatus);

            return orderDetailTm;
        }

        return null;
    }

}
