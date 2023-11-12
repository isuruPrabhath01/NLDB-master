package model;


import to.AnimalTo;
import to.CustomerTo;
import to.EmployeeTo;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//static method
public class EmployeeModel {
    public static boolean addEmployee(EmployeeTo employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)"
                ,employee.getId(),employee.getName(),employee.getSalary(),employee.getContact()
        ,employee.getAddress(),employee.getNic());

    }

    public static boolean updateEmployee(EmployeeTo employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET id = ? , name = ? , salary = ? contact = ? address = ? nic = ? WHERE id = ?";
        return CrudUtil.execute(sql,employee.getId(),employee.getName(),employee.getSalary(),employee.getContact(),employee.getAddress(),employee.getNic());
    }

    public static boolean deleteEmployee(EmployeeTo employee) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE id = ?";
        return CrudUtil.execute(sql,employee.getId());
}

    public static ArrayList<EmployeeTo> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeTo> list = new ArrayList<>();
        ResultSet el = CrudUtil.execute("SELECT * FROM employee");
        while (el.next()){
            EmployeeTo employee = new EmployeeTo(el.getString(1),el.getString(2),el.getDouble(3),el.getString(4),el.getString(5),el.getString(6));
            list.add(employee);
        }
        //new CustomerTo("","","","");
        return list;
    }
}
