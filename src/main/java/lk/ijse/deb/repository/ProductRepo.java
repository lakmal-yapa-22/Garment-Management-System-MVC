package lk.ijse.deb.repository;


import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.OrderDetail;
import lk.ijse.deb.model.Product;
import lk.ijse.deb.model.tm.ProductTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {


    public static List<ProductTm> getAll() throws SQLException {
        String sql = "SELECT * FROM Product";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ProductTm> productList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            int qty = resultSet.getInt(2);
            double price = resultSet.getDouble(3);
            String type = resultSet.getString(4);
            String color=resultSet.getString(5);
            String size =resultSet.getString(6);

            ProductTm product = new ProductTm(id, qty, price, type,color,size);
            productList.add(product);
        }
        return productList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Product WHERE product_id  = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(ProductTm product) throws SQLException {
        String sql = "INSERT INTO Product VALUES(?, ?, ?, ?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, product.getId());
        pstm.setObject(2, product.getQty());
        pstm.setObject(3, product.getPrice());
        pstm.setObject(4, product.getType());
        pstm.setObject(5,product.getColor());
        pstm.setObject(6,product.getSize());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(ProductTm product) throws SQLException {
        String sql = "UPDATE Product SET qty  = ?, price   = ?,product_type  = ?, color =?,size=? WHERE  product_id   = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, product.getQty());
        pstm.setObject(2, product.getPrice());
        pstm.setObject(3, product.getType());

        pstm.setObject(4,product.getColor());
        pstm.setObject(5,product.getSize());
        pstm.setObject(6, product.getId());

        return pstm.executeUpdate() > 0;
    }

    public static ProductTm searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String prd_id = resultSet.getString(1);
            int qty = resultSet.getInt(2);
            double salary = resultSet.getDouble(3);
            String type = resultSet.getString(4);
            String color= resultSet.getString(5);
            String size =resultSet.getString(6);

            ProductTm product = new ProductTm(prd_id, qty, salary, type,color,size);

            return product;
        }

        return null;
    }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT product_id   FROM Product ";
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




    public static Product searchId(Object  product_id) throws SQLException {
        String sql = "SELECT * FROM  Product WHERE  product_id  = ?";

        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, product_id);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            String id = resultSet.getString(1);
            int qty = resultSet.getInt(2);
            double price = resultSet.getDouble(3);
            String product_type = resultSet.getString(4);
            String color= resultSet.getString(5);
            String size = resultSet.getString(6);

            Product product = new Product(id, qty,price, product_type,color,size);

            return product;
        }
        else {
            return null;
        }
}

    public static boolean update(List<OrderDetail> odList) throws SQLException {

        for (OrderDetail od : odList) {

            boolean isUpdateQty = updateQty(od.getItemCode(), od.getQty());

            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String itemCode, int qty) throws SQLException {
        String sql = "UPDATE Product SET qty = qty - ? WHERE product_id  = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, itemCode);

        return pstm.executeUpdate() > 0;
    }

    public static List<Product> getAllProductTshirtLongTshirt() throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_type = 't-shirt' OR product_type = 'long_sleve_t_shirt'";



        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Product> productList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);

           int qty=resultSet.getInt(2);
           double price =resultSet.getDouble(3);
           String type=resultSet.getString(4);
           String color=resultSet.getString(5);
          String size =resultSet.getString(6);
         //   System.out.println(id+price+color+size);
          Product product = new Product(id,qty,price,type,color,size);
        //    System.out.println(product);
            productList.add(product);

        }
        return productList;
    }
    public static List<Product> getAllProdctShirtLongShirt() throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_type = 'shirt' OR product_type = 'long_sleve_shirt'";



        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Product> productList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);

            int qty=resultSet.getInt(2);
            double price =resultSet.getDouble(3);
            String type=resultSet.getString(4);
            String color=resultSet.getString(5);
            String size =resultSet.getString(6);
            //   System.out.println(id+price+color+size);
            Product product = new Product(id,qty,price,type,color,size);
         //   System.out.println(product);
            productList.add(product);

        }
        return productList;
    }




    public static List<Product> getAllTrouser()throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_type = 'pant' OR product_type = 'trouser'";



        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Product> productList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);

            int qty=resultSet.getInt(2);
            double price =resultSet.getDouble(3);
            String type=resultSet.getString(4);
            String color=resultSet.getString(5);
            String size =resultSet.getString(6);
            //   System.out.println(id+price+color+size);
            Product product = new Product(id,qty,price,type,color,size);
            //   System.out.println(product);
            productList.add(product);

        }
        return productList;
    }


    public static List<Product> getAllFrock() throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_type = 'frock'";



        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Product> productList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);

            int qty=resultSet.getInt(2);
            double price =resultSet.getDouble(3);
            String type=resultSet.getString(4);
            String color=resultSet.getString(5);
            String size =resultSet.getString(6);
            //   System.out.println(id+price+color+size);
            Product product = new Product(id,qty,price,type,color,size);
            //   System.out.println(product);
            productList.add(product);

        }
        return productList;
    }

}
    /*
    public static boolean update(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getItemCode(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    private static boolean updateQty(String productCode, int qty) throws SQLException {
        String sql = "UPDATE  Product SET qty = qty - ? WHERE product_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, productCode);

        return pstm.executeUpdate() > 0;
    }

   */


