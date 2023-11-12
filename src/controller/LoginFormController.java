package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.Global;
import org.bytedeco.javacpp.presets.opencv_core;
import service.EmailSender;
import to.PasswordTo;
import util.NotificationController;

import java.io.IOException;

public class LoginFormController  {


    public JFXTextField UserName;
    public Label lblError;
    public AnchorPane LoginFormContext;
    public JFXPasswordField Password;
    public String email;
    public String user;
    public String password;
    public String passGet;

    public void setPassword(){
        if(ForgotPasswordController.password==null){
            this.password="1234";
        }else {
            this.password = ForgotPasswordController.password;
            System.out.println(password);
        }
    }

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        LoginFormManager();
    }

    public void LoginFormManager() throws IOException {
        user = "USER";
        setPassword();
        if (UserName.getText().equals(user) && Password.getText().equals(password)) {
        Stage window = (Stage) LoginFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DashboardForm.fxml"))));
        window.centerOnScreen();
        } else if (UserName.getText().isEmpty() && Password.getText().isEmpty()) {
            lblError.setText("Your User Name Or Password IS Empty...!");
            UserName.clear();
            Password.clear();
        }
        else if (!UserName.getText().equals(user)) {
            lblError.setText("Your User Name is incorrect..!");
            UserName.clear();
            Password.clear();
        } else if (!Password.getText().equals(password)) {
            lblError.setText("Your Password is incorrect..!");
            UserName.clear();
            Password.clear();
        }
    }

    public void btnForgotPasswordOnAction(ActionEvent actionEvent) {
        NotificationController notificationController=new NotificationController();
        email="Roshennkf@gmail.com";
        EmailSender emailSender = new EmailSender(email);
        Stage window = (Stage) LoginFormContext.getScene().getWindow();
        notificationController.confirmMassage("OTP IN Your Mail","Your OTP is in your "+email);
        try {
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/PasswordForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.centerOnScreen();

    }


}






