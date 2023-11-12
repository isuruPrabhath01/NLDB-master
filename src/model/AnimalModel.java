package model;

import db.DBConnection;
import tm.AnimalTM;
import to.AnimalTo;
import to.CustomerTo;
import to.ItemTo;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalModel {
        public static boolean addAnimal(AnimalTo Animal) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("INSERT INTO animal VALUES(?,?,?,?)",
                    Animal.getId(),
                    Animal.getType(),
                    Animal.getQty(),
                    Animal.getDate()
            );
        }

    public static AnimalTo search(String type) throws SQLException, ClassNotFoundException {
        ResultSet rst =CrudUtil.execute("SELECT * FROM animal WHERE type='"+type+"'");
        System.out.println(rst);
        if (rst.next()){
            return new AnimalTo(rst.getString(1),rst.getString(2),rst.getInt(3), rst.getDate(4));
        }
        return null;
    }

    public static boolean updateQtyAnimal(AnimalTo animal) throws SQLException, ClassNotFoundException {
        Boolean i = CrudUtil.execute("UPDATE animal SET qty = ? WHERE type = ?",
                animal.getQty(),
                animal.getType());
        return i;
    }

        public static boolean updateAnimal(AnimalTo animal) throws SQLException, ClassNotFoundException {
            String sql = "UPDATE animal SET id = ? , qty = ? WHERE id = ?";
            return CrudUtil.execute(sql,animal.getId(),animal.getType(),animal.getQty(),animal.getDate());
        }

        public static boolean deleteAnimal(AnimalTo animal) throws SQLException, ClassNotFoundException {
            String sql = "DELETE FROM animal WHERE id = ?";
            return CrudUtil.execute(sql,animal.getId());
        }


        public static ArrayList<AnimalTo> getAllAnimal() throws SQLException, ClassNotFoundException {
        ArrayList<AnimalTo> list = new ArrayList<>();
        ResultSet al = CrudUtil.execute("SELECT * FROM animal");
        while (al.next()){
            AnimalTo animal = new AnimalTo(al.getString(1),al.getString(2),al.getInt(3),al.getDate(4));
            list.add(animal);
        }
        //new CustomerTo("","","","");
        return list;
    }

    public static String getNewId() throws SQLException, ClassNotFoundException {
        String lastStudentId=getLastId();
        if(lastStudentId==null){
            return "AA-000000000001";
        }else{
            String[] split=lastStudentId.split("[A][A][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newStudentId=String.format("AA-%012d", lastDigits);
            return newStudentId;
        }
    }


    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id from animal order by id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    public static boolean transaction(AnimalTM animalTM) throws SQLException, ClassNotFoundException {
        DBConnection.getInstance().getConnection().setAutoCommit(false);
        try {
            boolean b = addAnimal(new AnimalTo(animalTM.getId(), animalTM.getType(), animalTM.getQty(), animalTM.getDate()));
            if(b){
                boolean b1 = CageModel.updateAnimalCount(animalTM);
                if(b1){
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;

        }catch (SQLException e){
            DBConnection.getInstance().getConnection().rollback();
            throw e;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}




