package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {

    public AnchorPane DashBoardFormContext;

    public AnchorPane AllContext;

    public void initialize() throws IOException {
        home();
    }


    public void home() throws IOException {
        URL resource = getClass().getResource("../views/DisplayDashBoard.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);


    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {

        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LogInForm.fxml"))));
        window.centerOnScreen();
        }



    public void btnCustomer(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnDashBoard(ActionEvent actionEvent) throws IOException {
        home();
    }

    public void btnProducts(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ProductsForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/EmployeeForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnOrders(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/OrdersForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnBilling(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/BillingForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnAnimal(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AnimalManageForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnStock(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/StockForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ReportsForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnSupplier(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/SupplierForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }
}
