package model;

import tm.OrderDetailTM;
import to.Orders;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public static String getNewId() throws SQLException, ClassNotFoundException {
        String lastLectureId = getLastId();
        if (lastLectureId == null) {
            return "O-0000000000001";
        } else {
            String[] split = lastLectureId.split("[O][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newLectureId = String.format("O-%013d", lastDigits);
            return newLectureId;
        }
    }


    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id from orders order by id DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static List<Orders> getAll() throws SQLException, ClassNotFoundException {
        List<Orders>orders=new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM orders");
        while (rst.next()){
            orders.add(new Orders(
                    rst.getString(1),
                    rst.getDate(2).toString(),
                    rst.getString(3)
            ));
        }
        return orders;
    }

    public static double getTotalById(String id) throws SQLException, ClassNotFoundException {
        double total = 0;
        ResultSet rst = CrudUtil.execute("SELECT * FROM order_details WHERE order_id=?",id);
        while (rst.next()){
            String itemId = rst.getString(2);
            double unitPrice = ItemModel.getUnitPriceById(itemId);
            total+=unitPrice * rst.getInt(3);
        }
        return total;
    }

    public static List<OrderDetailTM> getOrderDetailById(String orderId) throws SQLException, ClassNotFoundException {
        List<OrderDetailTM> orderDetailTMS=new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM order_details WHERE order_id=?",orderId);
        while (rst.next()){
            orderDetailTMS.add(new OrderDetailTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            ));
        }
        return orderDetailTMS;
    }
}
