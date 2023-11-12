package model;

import to.CustomerTo;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean addCustomer(CustomerTo customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?)"
                , customer.getId(), customer.getName(), customer.getEmail(), customer.getContact());
    }

    public static boolean updateCustomer(CustomerTo customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name = ? , email = ? , contact = ? WHERE id = ?";
        return CrudUtil.execute(sql,customer.getName(),customer.getEmail(),customer.getContact(),customer.getId());
    }

    public static boolean deleteCustomer(CustomerTo customer) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE id = ?";
        return CrudUtil.execute(sql,customer.getId());
    }

    public static ArrayList<CustomerTo> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerTo> list = new ArrayList<>();
        ResultSet rs = CrudUtil.execute("SELECT * FROM customer");
        while (rs.next()){
            CustomerTo customer = new CustomerTo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            list.add(customer);
        }
        //new CustomerTo("","","","");
        return list;
    }

    public static String getCuNameByCusId(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM customer WHERE id=?",customerId);
        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}


