package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AttendanceModel;
import model.CustomerModel;
import model.EmployeeModel;
import model.WorkingDayModel;
import service.QrPerformance;
import tm.AttendenceTM;
import to.AttendanceTo;
import to.CustomerTo;
import to.EmployeeTo;
import to.WorkingDayTo;
import util.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class EmployeeFormController implements QrPerformance {
    public AnchorPane EmployeeContext;
    public AnchorPane EmployeeAttendanceContext;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtSalary;
    public JFXTextField txtNic;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView<EmployeeTo> tblEmployee;
    public Label lblEmployee;
    public TableView<AttendenceTM> tblAttendence;
    public TableColumn colEmployeeName;
    public TableColumn colStatus;


    public void  initialize(){
        setEmployeeTable();
        addWorkingDay();
        EmployeeAttendanceContext.setTranslateX(1700);

    }

    public void addWorkingDay(){
        try {
            boolean b = false;
            WorkingDayTo workingDay = WorkingDayModel.getWorkingDay(Date.valueOf(LocalDate.now()));
            if(workingDay==null){
                String id = WorkingDayModel.getNewId();
                ArrayList<EmployeeTo> allEmployee = EmployeeModel.getAllEmployee();
                ArrayList<AttendanceTo> attendance = new ArrayList<>();
                for (EmployeeTo employee : allEmployee){
                    attendance.add(new AttendanceTo(employee.getId(),id, Time.valueOf(LocalTime.now()),"AB"));
                }
                b= WorkingDayModel.addWorkingDay(attendance);
            }
            setTable();
            System.out.println("Already Added "+!b);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void setTable(){
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<AttendenceTM,String >("employeeName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<AttendenceTM,String>("status"));
        try {
            //ArrayList<AttendanceTo> at = AttendanceModel.getAttendanceByDay(WorkingDayModel.getWorkingDay(Date.valueOf(LocalDate.now())).getId());
            WorkingDayTo workingDay = WorkingDayModel.getWorkingDay(Date.valueOf(LocalDate.now()));
            if(workingDay==null)return;
            ArrayList<AttendenceTM> at = AttendanceModel.getAllAttendance(workingDay.getId());

            tblAttendence.setItems(FXCollections.observableArrayList(at));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnEmployeeAttendance(ActionEvent actionEvent) {

        //PigCageContext.setTranslateX(1700);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(EmployeeAttendanceContext);

        slide.setToX(0);
        slide.play();

        EmployeeAttendanceContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });
    }

    public EmployeeTo makeEmployee(){
        String id = txtId.getText();
        String name = txtName.getText();
        double sallary = Double.parseDouble(txtSalary.getText());
        String contact = txtContact.getText();
        String address= txtAddress.getText();
        String nic = txtNic.getText();

        return new EmployeeTo(id,name,sallary,contact,address,nic);

    }

    public void addEmployee(){
        String id = txtId.getText();
        String name = txtName.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        EmployeeTo employee = new EmployeeTo(id,name,salary,contact,address,nic);

        try {
            boolean b = EmployeeModel.addEmployee(employee);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //new EmployeeTo(id,"","","","","");
    }

    public void updateEmplooyee(){
      EmployeeTo employee = makeEmployee();

        try {
            boolean b = EmployeeModel.updateEmployee(employee);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteEmployee(){
       EmployeeTo employee = makeEmployee();
        //Confirmation AlERT START
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete " + employee.getName()
                + " From The System...This Cannot Be Recover", ButtonType.YES, ButtonType.NO).showAndWait();
        boolean no = buttonType.get().getText().equalsIgnoreCase("NO");
        if(no){
            new Alert(Alert.AlertType.WARNING,"Employeer Not Deleted").show();
            return;
        }
        //Confirmation AlERT END
        try {
            //Send Data To Database Start
            boolean b = EmployeeModel.deleteEmployee(employee);
            //Send Data To Database END
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Employee Delete Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Employee Not Deleted").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setEmployeeTable(){
        try {
            ArrayList<EmployeeTo> allEmployee = EmployeeModel.getAllEmployee();
            ObservableList<EmployeeTo> employeeList = FXCollections.observableArrayList(allEmployee);
            tblEmployee.setItems(employeeList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        addEmployee();
    }

    public void btnQrOnACtion(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/QrScannerForm.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent load = loader.load();
        QrScannerFormController controller = loader.getController();
        controller.setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }
    public void markAttendance(String id){
        ObservableList<AttendenceTM> items = tblAttendence.getItems();
        for (AttendenceTM a : items){
            if(a.getEmployeeId().equals(id)){
                a.setStatus("P");
                tblAttendence.refresh();
            }
        }
    }

    @Override
    public void qrIdRequestAction(String id) {

        System.out.println("Recieved "+id);
        markAttendance(id);
    }

    @Override
    public String getStudentDetail(String id) {
        return null;
    }
}
