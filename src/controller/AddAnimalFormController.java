package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.util.StringConverter;
import model.AnimalModel;
import model.CageModel;
import model.SupplierModel;
import tm.AnimalTM;
import to.CageTo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddAnimalFormController {

    public JFXTextField txtId;
    public JFXTextField txtQty;
    public JFXDatePicker txtDate;
    public JFXComboBox cbType;
    public JFXComboBox cbSupplier;
    public JFXCheckBox checkBox;
    public JFXComboBox<CageTo> cbCage;

    public void initialize(){
        setComboBox();
        setLabel();
    }

    public void setLabel(){
        try {
            txtId.setText(AnimalModel.getNewId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setComboBox(){

        try {
            String animalTypes[]  = {"CHICKEN","PIG"};
            ArrayList<String> supplier = SupplierModel.getSupplierId("ANIMAL");
            cbType.setItems(FXCollections.observableArrayList(animalTypes));
            cbSupplier.setItems(FXCollections.observableArrayList(supplier));



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void btnUpdateOnACtion(ActionEvent actionEvent) {
    }


    public AnimalTM getAnimal(){
        String id = txtId.getText();
        String type = cbType.getSelectionModel().getSelectedItem().toString();
        String supplierId = cbSupplier.getSelectionModel().getSelectedItem().toString();
        int qty = Integer.parseInt(txtQty.getText());
        Date date = Date.valueOf(txtDate.getValue());
        CageTo to = cbCage.getSelectionModel().getSelectedItem();

        return new AnimalTM(id,type,qty,date,supplierId,to.getId());
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            boolean add = AnimalModel.transaction(getAnimal());
            if(add){
                new Alert(Alert.AlertType.INFORMATION,"Saved Animal Data").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelPig(ActionEvent actionEvent) {

    }

    public void cbTypeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(cbType.getSelectionModel().getSelectedItem()==null)return;
        String s = cbType.getSelectionModel().getSelectedItem().toString();
        char[] chars = s.toCharArray();
            ArrayList<CageTo> p = CageModel.getID(String.valueOf(chars[0]));
            cbCage.setItems(FXCollections.observableArrayList(p));
            cbCage.setConverter(new StringConverter<CageTo>() {
                @Override
                public String toString(CageTo object) {
                    return object.getName();
                }

                @Override
                public CageTo fromString(String string) {
                    return null;
                }
            });

    }

    public void checkBoxOnAction(ActionEvent actionEvent) {
            cbSupplier.setDisable(checkBox.isSelected());
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
