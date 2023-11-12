package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ChickenCageFormController   {

    public AnchorPane AllContext;
    public AnchorPane AnimalCageContext;

    //public AnchorPane AllContext;
    public void btnCancelChicken(ActionEvent actionEvent) throws IOException {
       URL resource = getClass().getResource("../views/AnimalManageForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);

    }
}
