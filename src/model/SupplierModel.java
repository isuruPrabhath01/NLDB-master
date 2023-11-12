package model;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static ArrayList<String> getSupplierId(String type) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id FROM supplier WHERE type = ? ", type);
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()){
            ids.add(rs.getString(1));
        }
        return ids;
    }
}
