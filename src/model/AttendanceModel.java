package model;

import javafx.collections.ObservableList;
import tm.AttendenceTM;
import to.AttendanceTo;
import to.WorkingDayTo;
import util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceModel {
    public static ArrayList<AttendanceTo> getAttendanceByDay(String dayId) throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceTo> list = new ArrayList<>();
        ResultSet rs = CrudUtil.execute("SELECT * FROM attendence where day_id = ?", dayId);
        while (rs.next()){
            list.add(new AttendanceTo(rs.getString(1),rs.getString(2),rs.getTime(3),rs.getString(4)));
        }
        return list;
    }

    public static boolean addAttendance(ArrayList<AttendanceTo> list) throws SQLException, ClassNotFoundException {
        for (AttendanceTo attendance : list){
            if(!addAttendance(attendance)){
                return false;
            }
        }
        return true;
    }

    public static boolean addAttendance(AttendanceTo attendance) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into attendence(employee_id,day_id) values(?,?)", attendance.getEmployeeId(), attendance.getDayId());
    }

    public static ArrayList<AttendenceTM> getAllAttendance(String dateId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT e.id,e.name , a.status from attendence a left join employee e on a.employee_id = e.id where a.day_id = ?\n" +
                "\n", dateId);
        ArrayList<AttendenceTM> list = new ArrayList<>();
        while (rs.next()){
            list.add(new AttendenceTM(rs.getString(1),rs.getString(2),rs.getString(3)));
        }
        return list;
    }

}
