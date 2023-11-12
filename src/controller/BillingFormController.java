package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.CustomerModel;
import model.ItemModel;
import model.OrderModel;
import tm.CartTM;
import to.CustomerTo;
import to.ItemTo;
import to.Orders;
import model.PlaceOrderModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BillingFormController {

    public JFXTextField txtQty;
    public JFXComboBox cbType;
    public JFXComboBox<ItemTo> cbItem;
    public JFXTextField txtItemName;
    public JFXTextField txtQtyOnHand;
    public Label lblTotal;
    public JFXTextField txtUnitPrice;
    public TableView<CartTM> tblCart;
    public TableColumn colItemCode;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colSubTotal;
    public Label lblOrderId;
    public JFXTextField txtCustomerContact;
    public JFXTextField txtCustomerEmail;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerId;
    public ComboBox <CustomerTo>cbCustomerType;
    private ItemTo selectedItem;
    private CustomerTo selectedCustomer;

    public void initialize(){
        String types[]  = {"ANIMAL","PRODUCT"};
        cbType.setItems(FXCollections.observableArrayList(types));
        setTable();setOrderId();
        setCustomerComboBox();

    }

    public void setOrderId()  {
        try {
            lblOrderId.setText(OrderModel.getNewId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setTable(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<CartTM, String>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<CartTM, String>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<CartTM, String>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<CartTM, String>("unitPrice"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<CartTM, String>("subTotal"));

    }
    public void setCustomerComboBox(){
        try {
            ArrayList<CustomerTo> allCustomer = CustomerModel.getAllCustomer();
            cbCustomerType.setItems(FXCollections.observableArrayList(allCustomer));

            cbCustomerType.setConverter(new StringConverter<CustomerTo>() {
                @Override
                public String toString(CustomerTo object) {
                    return object.getId()+" - "+object.getName();
                }

                @Override
                public CustomerTo fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        int i = Integer.parseInt(txtQty.getText());
        CartTM cartTM = new CartTM(selectedItem.getId(), selectedItem.getName(), i, selectedItem.getPrice(), selectedItem.getPrice() * i);
        selectedItem.setQty(selectedItem.getQty()-i);
        tblCart.getItems().add(cartTM);

    }


    public void cbItemTypeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String type = cbType.getSelectionModel().getSelectedItem().toString();
        ArrayList<ItemTo> itemTos = ItemModel.searchByType(type);
        cbItem.setItems(FXCollections.observableArrayList(itemTos));


        cbItem.setConverter(new StringConverter<ItemTo>() {
            @Override
            public String toString(ItemTo object) {
                return object.getId()+" - "+object.getName();
            }

            @Override
            public ItemTo fromString(String string) {
                return null;
            }
        });
    }

    public void cbItemNameOnAction(ActionEvent actionEvent) {
        ItemTo itemTo = cbItem.getItems().get(cbItem.getSelectionModel().getSelectedIndex());
        selectedItem=itemTo;
        txtItemName.setText(itemTo.getName());
        txtQtyOnHand.setText(String.valueOf(itemTo.getQty()));
        txtUnitPrice.setText(String.valueOf(itemTo.getPrice()));
    }

    public void cbCustomerTypeOnAction(ActionEvent actionEvent) {
        selectedCustomer = cbCustomerType.getSelectionModel().getSelectedItem();
        txtCustomerId.setText(selectedCustomer.getId());
        txtCustomerContact.setText(selectedCustomer.getContact());
        txtCustomerEmail.setText(selectedCustomer.getEmail());
        txtCustomerName.setText(selectedCustomer.getName());
    }

    public void placeOrder(){
        ObservableList<CartTM> items = tblCart.getItems();
        ArrayList<CartTM> list = new ArrayList<>();
        for(CartTM cartTM : items){
            list.add(cartTM);
        }
        Orders orders = new Orders(lblOrderId.getText(), LocalDate.now().toString(), selectedCustomer.getId());
        try {
            boolean b = PlaceOrderModel.placeOrder(orders, list, selectedCustomer);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Order Placed").show();
                setOrderId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        placeOrder();
    }
}
