package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.AnimalModel;
import model.ItemModel;
import to.AnimalTo;
import to.ItemTo;
import util.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsFormController {

    public TableView tblCMeat;
    public TableView tblEgg1;
    public JFXComboBox cmbProducts;
    public TableView tblEgg;
    public AnchorPane ancAddProducts;
    public JFXTextField txtDate;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtAddingQty;
    public JFXComboBox cmbSelectProduct;
    public JFXTextField txtAnimalCom;
    public TableView<ItemTo> tblProduct;
    public TableColumn tblProductType;
    public TableColumn tblProductQty;
    public TableColumn tblProductUnitPrice;


    public void  initialize(){

        ancAddProducts.setTranslateX(1400);
        loadData();
        txtAnimalCom.setDisable(true);
        loadTable();
//        tblProductType.setCellValueFactory(new PropertyValueFactory<>("name"));
//        tblProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
//        tblProductUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    public void loadTable(){
        tblProductType.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblProductUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            ArrayList<ItemTo> all = ItemModel.searchByType("PRODUCT");
            ObservableList<ItemTo> itemTos = FXCollections.observableArrayList(all);
            tblProduct.setItems(itemTos);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void setComboBoxItem(){
        String[] items = {"Egg","Chicken","Pork"};
        cmbProducts.setItems(FXCollections.observableArrayList(items));
    }



    public void btnAddProduct(ActionEvent actionEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(ancAddProducts);

        slide.setToX(0);
        slide.play();

        ancAddProducts.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });
    }

    public void btnCancelPig(ActionEvent actionEvent) {
        ancAddProducts.setTranslateX(1400);
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean j=false;
        int animalQty=0;
        AnimalTo animalTo;
        String mealType = null;
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int addingQty = Integer.parseInt(txtAddingQty.getText());
        int netQty=qtyOnHand+addingQty;
        if(((cmbSelectProduct.getValue().toString().equals("Chicken") ||cmbSelectProduct.getValue().toString().equals("Pork")) && (txtAnimalCom.getText()!=null))||cmbSelectProduct.getValue().toString().equals("Egg")) {
            try {
                boolean i = ItemModel.updateQTY(new ItemTo((String) cmbSelectProduct.getValue(), netQty));
                if (cmbSelectProduct.getValue().toString().equals("Chicken")) {
                    mealType = "Chicken";
                } else if (cmbSelectProduct.getValue().toString().equals("Pork")) {
                    mealType = "Pork";
                }else if(cmbSelectProduct.getValue().toString().equals("Egg")){
                    mealType="Egg";
                }
                if (mealType.equals("Egg")){}else {
                    animalTo = AnimalModel.search(mealType);
                    animalQty = animalTo.getQty();
                    int lastAnimalqty = animalQty - Integer.parseInt(txtAnimalCom.getText());
                    j = AnimalModel.updateQtyAnimal(new AnimalTo(mealType, lastAnimalqty));
                }
                    if (i && j) {
                        new Alert(Alert.AlertType.CONFIRMATION, "QTY ADDED SUCCESSFULLY").show();
                    }else if (i && j==false){
                        new Alert(Alert.AlertType.CONFIRMATION, "Eggs ADDED SUCCESSFULLY").show();
                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION, "ERROR!!").show();
                    }
                } catch(SQLException e){
                    new Alert(Alert.AlertType.CONFIRMATION, "SQL ERROR").show();
                } catch(ClassNotFoundException e){
                    e.printStackTrace();
                }



        }
        loadTable();
        clearAll();
    }

    public void clearAll(){
        cmbSelectProduct.getSelectionModel().select(null);
        txtQtyOnHand.setText(null);
        txtAddingQty.setText(null);
        txtAnimalCom.setText(null);
        txtAnimalCom.setDisable(true);
    }

    public void btnClearAllOnAction(ActionEvent actionEvent) {
        clearAll();
    }

    public void cmbSelectProductOnAction(ActionEvent actionEvent) {
        ItemTo itemTo;
        try {
            if(cmbSelectProduct.getSelectionModel().getSelectedIndex()==-1)return;
            itemTo=ItemModel.search(cmbSelectProduct.getValue().toString());
            System.out.println(itemTo.getQty());
            txtQtyOnHand.setText(String.valueOf(itemTo.getQty()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(cmbSelectProduct.getValue().toString().equals("Chicken") ||cmbSelectProduct.getValue().toString().equals("Pork")){
            txtAnimalCom.setDisable(false);
        }else{
            txtAnimalCom.setDisable(true);
        }
    }

    public void loadData(){
        String[] items = {"Egg","Chicken","Pork"};
        cmbSelectProduct.setItems(FXCollections.observableArrayList(items));
        txtDate.setText(Service.setDate());
    }
}
