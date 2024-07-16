package lk.ijse.deb.model;

import lk.ijse.deb.model.tm.OrderTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrder {
    private Order order;
    private List<OrderDetail> odList;

    public PlaceOrder(Object order, List<OrderDetail> odList) {
    }
}
