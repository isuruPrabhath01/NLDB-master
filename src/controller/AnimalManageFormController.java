package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AnimalModel;
import model.CustomerModel;
import thread.TemparatureService;
import to.AnimalTo;
import to.CustomerTo;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class AnimalManageFormController {



    //public AnchorPane AllContext;
    public AnchorPane AddChickenContext;
    //public AnchorPane AddPigContext;
    public AnchorPane AnimalContext;
    public AnchorPane ChickenCageContext;
    public TableView tblPig;
    public TableView tblChicken;
    public TableView tblChickenInCage;
    public AnchorPane AnimalContext1;
    public TableView tblPigInCage;
    public AnchorPane PigCageContext;
    public JFXTextField txtId;
    public JFXTextField txtQty;
    public JFXDatePicker txtDate;
    public JFXComboBox cbTypes;
    //public  TableView tblAnimal;
    public Label lblTemp4;
    public Label lblTemp1;
    public Label lblTemp2;
    public Label lblTemp3;


    public void  initialize(){

        String [] types = {"PIG","CHICKEN"};
        ObservableList<String> types2 = FXCollections.observableArrayList(types);

        cbTypes.setItems(types2);

        PigCageContext.setTranslateX(1700);
        ChickenCageContext.setTranslateX(1700);
        AddChickenContext.setTranslateX(1400);
        //AddPigContext.setTranslateX(1400);
        setThreads();

    }


    public void btnCancelChicken(ActionEvent actionEvent) {

        AddChickenContext.setTranslateX(1400);
    }

    public void setThreads(){
        TemparatureService s = new TemparatureService();
        s.valueProperty().addListener((a,oldValue,newValue)->{
            lblTemp1.setText(String.valueOf(newValue));
        });

        Thread t1 = new Thread(s);
        t1.start();
    }

    public void btnCancelPig(ActionEvent actionEvent) {
        //AddPigContext.setTranslateX(1400);

    }

    public void btnUpdateAnimal(ActionEvent actionEvent) {
        updateAnimal();
   }

    public void deleteAnimal(){
        AnimalTo animal = makeAnimal();
        //Confirmation AlERT START
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete " + animal.getId()
                + " From The System...This Cannot Be Recover", ButtonType.YES, ButtonType.NO).showAndWait();
        boolean no = buttonType.get().getText().equalsIgnoreCase("NO");
        if(no){
            new Alert(Alert.AlertType.WARNING,"Animal Not Deleted").show();
            return;
        }
        //Confirmation AlERT END
        try {
            //Send Data To Database Start
            boolean b = AnimalModel.deleteAnimal(animal);
            //Send Data To Database END
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Animal Delete Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Animal Not Deleted").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setAnimalTable(){
        try {
            ArrayList<AnimalTo> allAnimal = AnimalModel.getAllAnimal();
            ObservableList<AnimalTo> animalList = FXCollections.observableArrayList(allAnimal);
            //tblAnimal.setItems(animalList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddPig(ActionEvent actionEvent) {
        /*AddChickenContext.setTranslateX(1400);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(AddPigContext);

        slide.setToX(0);
        slide.play();

        AddPigContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });*/
        addAnimal();
    }

    public void addAnimal(){
        try {
            Parent load = FXMLLoader.load(getClass().getResource("../views/AddAnimalForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(lblTemp1.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddChicken(ActionEvent actionEvent) {
        /*AddPigContext.setTranslateX(1400);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(AddChickenContext);

        slide.setToX(0);
        slide.play();

        AddChickenContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });*/
        addAnimal();
    }

    public void updateAnimal(){
       AnimalTo animal = makeAnimal();

        try {
            boolean b = AnimalModel.updateAnimal(animal);
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

    public AnimalTo makeAnimal(){
        String id = txtId.getText();
        int qty = Integer.parseInt(txtQty.getText());

        return new AnimalTo(qty,id);

    }

    public void btnPCage(ActionEvent actionEvent) throws IOException {
        ChickenCageContext.setTranslateX(1700);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(PigCageContext);

        slide.setToX(0);
        slide.play();

        PigCageContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });

    }

    public void btnCCage(ActionEvent actionEvent) throws IOException {
        PigCageContext.setTranslateX(1700);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(ChickenCageContext);

        slide.setToX(0);
        slide.play();

        ChickenCageContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });


    }

    public void btnBackChicken(ActionEvent actionEvent) throws IOException {
        ChickenCageContext.setTranslateX(1700);
        PigCageContext.setTranslateX(1700);
    }

    public void btnToCancelPCage(ActionEvent actionEvent) {

        PigCageContext.setTranslateX(1700);
        ChickenCageContext.setTranslateX(1700);
    }

    public void btnToPigCage(ActionEvent actionEvent) {

        ChickenCageContext.setTranslateX(1700);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(PigCageContext);

        slide.setToX(0);
        slide.play();

        PigCageContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });

    }

    public void btnBackPigCage(ActionEvent actionEvent) {
        PigCageContext.setTranslateX(1700);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(ChickenCageContext);

        slide.setToX(0);
        slide.play();

        ChickenCageContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });


    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        saveAnimal();
    }

    public void saveAnimal(){
        String id = txtId.getText();
        String type = cbTypes.getSelectionModel().getSelectedItem().toString();

        int qty = -1;
        try{
            qty = Integer.parseInt(txtQty.getText());
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Only Numbers Allowed In Qty Field").show();
            return;
        }


        Date date = Date.valueOf(txtDate.getValue());

        AnimalTo animal = new AnimalTo(id,type,qty,date);

        try {
            boolean b = AnimalModel.addAnimal(animal);

            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Success").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed").show();
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
