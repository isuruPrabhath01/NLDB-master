package model;

import to.CustomerTo;
import to.StockTo;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockModel {
    public static boolean addStock(StockTo stock) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO stock VALUES(?,?,?,?)"
                ,stock.getId(),stock.getName(),stock.getType(),stock.getQty(),stock.getPrice());
    }
    public static boolean updateStock(StockTo stock) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE stock SET name = ? , qty = ? , price = ? WHERE id = ?";
        return CrudUtil.execute(sql,stock.getName(),stock.getQty(),stock.getPrice());
    }

    public static boolean deleteStock(StockTo stock) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM stock WHERE id = ?";
        return CrudUtil.execute(sql,stock.getId());
    }

    public static ArrayList<StockTo> getAllStock() throws SQLException, ClassNotFoundException {
        ArrayList<StockTo> list = new ArrayList<>();
        ResultSet sl = CrudUtil.execute("SELECT * FROM customer");
        while (sl.next()){
            StockTo stock = new StockTo(sl.getString(1),sl.getString(2),sl.getString(3),sl.getInt(4),sl.getDouble(5));
            list.add(stock);
        }
        //new StockTo("","","","");
        return list;
    }
}
