package model;

import db.DBConnection;
import model.ItemModel;
import model.OrderItemModel;
import tm.CartTM;
import to.CustomerTo;
import to.Orders;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderModel {
    public static boolean placeOrder(Orders order, ArrayList<CartTM> cartTM, CustomerTo customer) throws SQLException, ClassNotFoundException {
        DBConnection.getInstance().getConnection().setAutoCommit(false);
        try {
            Boolean add = CrudUtil.execute("INSERT INTO orders VALUES(?,?,?)", order.getId(), order.getOrderDate(), order.getCustomerId());
            if(add){
                boolean add1 = OrderItemModel.addData(cartTM, order.getId());
                if(add1){
                    boolean add3 = ItemModel.removeQty(cartTM);
                    if(add3){
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }

                }

            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } catch (SQLException e) {
            DBConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
        return false;
    }
}
