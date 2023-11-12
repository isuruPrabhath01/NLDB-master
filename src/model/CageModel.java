package model;

import org.bridj.cpp.com.VARIANT;
import tm.AnimalTM;
import to.CageTo;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CageModel {
    public static ArrayList<CageTo> getID(String type) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM cage WHERE id LIKE '%"+type+"%'");
        ArrayList<CageTo> list = new ArrayList<>();
        while(rs.next()){
            list.add(new CageTo(rs.getString(1),rs.getString(2),rs.getInt(3)));
        }
        return list;
    }

    public static boolean updateAnimalCount(AnimalTM animalTM) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE cage set animal_count = animal_count+? WHERE id = ?",animalTM.getQty(),animalTM.getCageId());
    }
}
