package model;

import tm.CartTM;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemModel {
    public static boolean addData(ArrayList<CartTM> cartTMS,String orderId ) throws SQLException, ClassNotFoundException {
        for (CartTM cartTM : cartTMS){
            if(!add(cartTM,orderId)){
                return false;
            }
        }
        return true;
    }

    private static boolean add(CartTM cartTM,String orderId) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("insert into order_details VALUES(?,?,?)",orderId,cartTM.getItemCode(),cartTM.getQty());
    }
}
