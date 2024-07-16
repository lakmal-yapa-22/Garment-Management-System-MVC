package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrder());

            if (isOrderSaved) {
                boolean isQtyUpdated = ProductRepo.update(po.getOdList());

                if (isQtyUpdated) {
                  //  System.out.println(isQtyUpdated);
                    boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());
                 //   System.out.println(isOrderDetailSaved+"4");
                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

