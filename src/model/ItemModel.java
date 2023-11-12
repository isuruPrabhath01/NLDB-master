package model;

import tm.CartTM;
import to.AnimalTo;
import to.ItemTo;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
//    public static ArrayList<ItemTo> searchByType(String type) throws SQLException, ClassNotFoundException {
//        ResultSet rst =CrudUtil.execute("SELECT * FROM Item WHERE type='"+type+"'");
//        System.out.println(rst);
//        ArrayList<ItemTo> itemView=new ArrayList<>();
//
//        while(rst.next()){
//            itemView.add(
//                    new ItemTo(rst.getString(1),
//                            rst.getString(2),
//                            rst.getInt(3),
//                            rst.getDouble(4),
//                            rst.getString(5))
//            );
//        }
//        return itemView;
//    }
//    public boolean save(ItemTo itemTo) throws SQLException, ClassNotFoundException {
//        boolean i =CrudUtil.execute("INSERT INTO Item VALUES(?,?,?,?,?)",
//                itemTo.getId(),
//                itemTo.getName(),
//                itemTo.getType(),
//                itemTo.getQty(),
//                itemTo.getPrice()
//        );
//        System.out.println(i);
//        return i;
//    }

    public static ItemTo search(String type) throws SQLException, ClassNotFoundException {
        ResultSet rst =CrudUtil.execute("SELECT * FROM Item WHERE name='"+type+"'");
        System.out.println(rst);
        if (rst.next()){
            return new ItemTo(rst.getString(1),rst.getString(2),rst.getInt(3), rst.getDouble(4), rst.getString(5));
        }
        return null;
    }

    public static ArrayList<ItemTo> searchByType(String type) throws SQLException, ClassNotFoundException {
        ResultSet rst =CrudUtil.execute("SELECT * FROM Item WHERE type='"+type+"'");
        System.out.println(rst);
        ArrayList<ItemTo> itemView=new ArrayList<>();

        while(rst.next()){
            itemView.add(
                    new ItemTo(rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getDouble(4),
                            rst.getString(5))
            );
        }
        return itemView;
    }


    public static boolean update(ItemTo itemTo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE stock set name=?, qty=? , price = ? where id=?",
                itemTo.getName(),
                itemTo.getQty(),
                itemTo.getPrice(),
                itemTo.getId()
        );
    }

    public static boolean updateQTY(ItemTo itemTo) throws SQLException, ClassNotFoundException {
        boolean i =CrudUtil.execute("UPDATE item set qty=? where name=?",
                itemTo.getQty(),
                itemTo.getName()
        );
        System.out.println(i);
        return i;
    }

    public static boolean removeQty(ArrayList<CartTM> arrayList) throws SQLException, ClassNotFoundException {
        for (CartTM cartTM : arrayList){
            if(!removeQty(cartTM)){
                return false;
            }
        }
        return true;
    }

    private static boolean removeQty(CartTM cartTM) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update item set qty = qty-? where id = ? ",cartTM.getQty(),cartTM.getItemCode());
    }

    public static boolean save(ItemTo itemTo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO stock VALUES(?,?,?,?,?)",
                itemTo.getId(),
                itemTo.getName(),
                itemTo.getType(),
                itemTo.getQty(),
                itemTo.getPrice()
        );
    }

    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT id FROM stock ORDER BY id DESC LIMIT 1");
        if (execute.next()){
            return String.format("C%03d",Integer.parseInt(execute.getString(1).substring(1))+1);
        }
        return "C001";
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM stock WHERE id=?",id);
    }

    public static ArrayList<ItemTo> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemTo> itemView=new ArrayList<>();
        ResultSet rst=CrudUtil.execute("SELECT * FROM stock");

        while(rst.next()){
            itemView.add(
                    new ItemTo(rst.getString(1),
                            rst.getString(2),
                            rst.getInt(4),
                            rst.getDouble(5),
                            rst.getString(3)
                    )
            );
        }
        return itemView;
    }

    public static double getUnitPriceById(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT price FROM item WHERE id=?",itemId);
        rst.next();
        return rst.getDouble(1);
    }
}