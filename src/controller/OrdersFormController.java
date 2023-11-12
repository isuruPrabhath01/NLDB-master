package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.CustomerModel;
import model.OrderModel;
import tm.OrderDetailTM;
import tm.OrdersTM;
import to.Orders;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersFormController {
    @FXML

    public TableColumn<OrderDetailTM,String> colOrderIdIdOD;
    @FXML
    private TableView<OrderDetailTM> tblOrderDetails;

    @FXML
    private TableColumn<OrderDetailTM, String> colItemIdOD;



    @FXML
    private TableColumn<OrderDetailTM, Integer> colQtyOD;


    @FXML
    private TableView<OrdersTM> tblOrders;

    @FXML
    private TableColumn<OrdersTM, String> colCusID;

    @FXML
    private TableColumn<OrdersTM, String> colCusName;

    @FXML
    private TableColumn<OrdersTM, String> colOrderId;

    @FXML
    private TableColumn<OrdersTM, LocalDate>colDate;

    @FXML
    private TableColumn<OrdersTM, Double> colTotal;
    private final ObservableList<OrdersTM> ordersTMObservableList= FXCollections.observableArrayList();
    private final ObservableList<OrderDetailTM>orderDetailTMObservableList=FXCollections.observableArrayList();

    public void initialize(){
        setCellFactory();
        loadOrdersTable();
    }

    private void refreshOrderDetailsTable(String orderId) {
        orderDetailTMObservableList.clear();
        try {
            orderDetailTMObservableList.addAll(OrderModel.getOrderDetailById(orderId));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        tblOrderDetails.setItems(orderDetailTMObservableList);
    }

    private void loadOrdersTable() {
        try {
            for (Orders orders:OrderModel.getAll()){
                String cusName = CustomerModel.getCuNameByCusId(orders.getCustomerId());
                double total = OrderModel.getTotalById(orders.getId());
                ordersTMObservableList.add(new OrdersTM(orders.getCustomerId(),cusName,orders.getId(),LocalDate.parse(orders.getOrderDate()),total));
            }
            tblOrders.setItems(ordersTMObservableList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        }
    }

    private void setCellFactory() {
        //orders
        colCusID.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        //order details
        colOrderIdIdOD.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemIdOD.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colQtyOD.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    @FXML
    void tblOrders(MouseEvent event) {
        OrdersTM ordersTM = tblOrders.getSelectionModel().getSelectedItem();
        if (ordersTM!=null) {
            refreshOrderDetailsTable(ordersTM.getOrderId());
        }
    }

}
