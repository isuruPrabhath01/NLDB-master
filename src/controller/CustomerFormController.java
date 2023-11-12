package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerModel;
import to.CustomerTo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerFormController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerEmail;
    public JFXTextField txtCustomerContact;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colMobileNo;

    public void initialize(){
        setCustomerTable();
        visibleData();
    }

    public CustomerTo makeCustomer(){
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String contact = txtCustomerContact.getText();
        String email = txtCustomerEmail.getText();

        return new CustomerTo(id,name,email,contact);

    }

    public void addCustomer(){
        CustomerTo customer = makeCustomer();

        try {
            boolean b = CustomerModel.addCustomer(customer);
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

    public void updateCustomer(){
        CustomerTo customer = makeCustomer();

        try {
            boolean b = CustomerModel.updateCustomer(customer);
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

    public void deleteCustomer(){
        CustomerTo customer = makeCustomer();
        //Confirmation AlERT START
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete " + customer.getName()
                + " From The System...This Cannot Be Recover", ButtonType.YES, ButtonType.NO).showAndWait();
        boolean no = buttonType.get().getText().equalsIgnoreCase("NO");
        if(no){
            new Alert(Alert.AlertType.WARNING,"Customer Not Deleted").show();
            return;
        }
        //Confirmation AlERT END
        try {
            //Send Data To Database Start
            boolean b = CustomerModel.deleteCustomer(customer);
            //Send Data To Database END
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Customer Delete Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer Not Deleted").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveCustomer(ActionEvent actionEvent) {
        addCustomer();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        updateCustomer();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        deleteCustomer();
    }

    public void visibleData(){
        colName.setCellValueFactory(new PropertyValueFactory<CustomerTo,String>("name"));
        colId.setCellValueFactory(new PropertyValueFactory<CustomerTo,String>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<CustomerTo,String>("email"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<CustomerTo,String>("contact"));
        //colNic.setCellValueFactory(new PropertyValueFactory<CustomerTo,String>("name"));
    }

    public void setCustomerTable(){
        try {
            ArrayList<CustomerTo> allCustomer = CustomerModel.getAllCustomer();
            ObservableList<CustomerTo> customerTos = FXCollections.observableArrayList(allCustomer);
            tblCustomer.setItems(customerTos);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}









