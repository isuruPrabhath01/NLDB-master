package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bytedeco.javacpp.presets.opencv_core;
import service.EmailConnector;
import service.EmailSender;
import service.ProgessBar;
import sun.security.util.Password;
import to.DTOTransfer;
import to.PasswordTo;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ForgotPasswordController  implements Initializable {

    public AnchorPane AddPorkContext;
    public JFXTextField txtTextOTP;
    public Text txtOTPVerification;
    public JFXTextField txtNewPassword;
    public int progressCollector;
    public Text txtLoadingText;
    public ProgressBar progressInd;
    public JFXTextField txtReEnterPassword;
    public ImageView imvEneterPasswordHide;
    public JFXPasswordField txtPasswordEnter;
    public JFXPasswordField txtPasswordReEnter;
    public ImageView imvEneterPasswordShow;
    PasswordTo pst = new PasswordTo();
    LoadingScreen loadingScreen;
    String OTP;
    public static String password;


    public ForgotPasswordController() {

    }

    public ForgotPasswordController(String OTP) {
        this.OTP = OTP;
    }

    public void checkOTP(){

        OTP= EmailSender.OTP;
        String enOTP=txtTextOTP.getText();
        System.out.println(OTP+" Check");
        System.out.println(txtTextOTP.getText());
        loadingScreen = new ForgotPasswordController.LoadingScreen(progressInd, txtLoadingText);
        startProgress();
        System.out.println(progressCollector);



    }

    public void btnCheckOnAction(ActionEvent actionEvent) {
        txtNewPassword.setVisible(false);
        txtReEnterPassword.setVisible(false);
        progressInd.setVisible(true);
        checkOTP();
    }

    public void btnChangePassword(ActionEvent actionEvent) throws IOException {
        String password2=null;
        String reEnterPassword=null;
        if(txtNewPassword.isVisible()){
            password2=txtNewPassword.getText();
            reEnterPassword=txtReEnterPassword.getText();
        }else if(txtPasswordEnter.isVisible()){
            password2=txtPasswordEnter.getText();
            reEnterPassword=txtPasswordReEnter.getText();
        }
        if (password2.equals(reEnterPassword)){
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"DO YOU WANT TO CHANGE PASSWORD",ButtonType.YES,ButtonType.NO).showAndWait();
            if (buttonType.get()==ButtonType.YES){
                password=password2;
                Stage window = (Stage) AddPorkContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LogInForm.fxml"))));

                window.centerOnScreen();
            }if (buttonType.get()==ButtonType.NO){
                txtNewPassword.setText(null);
                txtReEnterPassword.setText(null);
            }

        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"PASSWORD IS INCORRECT").show();
            txtNewPassword.setText(null);
            txtReEnterPassword.setText(null);
        }

    }





    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    void startProgress() {
        Thread thread = new Thread(loadingScreen);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNewPassword.setDisable(true);
        txtReEnterPassword.setDisable(true);
        txtPasswordEnter.setDisable(true);
        txtPasswordReEnter.setDisable(true);
        progressInd.setVisible(false);
    }

    public void eneterPasswordHideOnClick(MouseEvent mouseEvent) {
        txtPasswordEnter.setText(txtNewPassword.getText());
        txtPasswordReEnter.setText(txtReEnterPassword.getText());
        txtPasswordReEnter.setVisible(true);
        txtPasswordEnter.setVisible(true);

        txtReEnterPassword.setVisible(false);
        txtNewPassword.setVisible(false);
        imvEneterPasswordHide.setVisible(false);
        imvEneterPasswordShow.setVisible(true);
    }

    public void eneterPasswordShowOnClick(MouseEvent mouseEvent) {
        txtNewPassword.setText(txtPasswordEnter.getText());
        txtReEnterPassword.setText(txtPasswordReEnter.getText());
        txtReEnterPassword.setVisible(true);
        txtNewPassword.setVisible(true);

        txtPasswordEnter.setVisible(false);
        txtPasswordReEnter.setVisible(false);
        imvEneterPasswordShow.setVisible(false);
        imvEneterPasswordHide.setVisible(true);
    }

    public void BlackOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) AddPorkContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LogInForm.fxml"))));
        window.centerOnScreen();
    }

    public class LoadingScreen implements Runnable {
        ProgressBar prbProgressBar;
        Text txtloadingText;
        public LoadingScreen(ProgressBar prbProgressBar, Text txtloadingText) {
            this.prbProgressBar = prbProgressBar;
            this.txtloadingText = txtLoadingText;
        }

        @Override
        public void run() {
            for(int i=1; prbProgressBar.getProgress() <= 1; i++) {
                int k= i;
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        prbProgressBar.setProgress(prbProgressBar.getProgress() +0.01);
                        txtLoadingText.setText(k+"%...");
                    }
                });

                synchronized (this){
                    try{
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
            txtLoadingText.setText("100%...");
            prbProgressBar.setVisible(false);
            txtLoadingText.setVisible(false);
            String enOTP=txtTextOTP.getText();
            if (OTP.equals(enOTP)) {
                txtTextOTP.clear();
                txtOTPVerification.setText("OTP is correct");
                txtNewPassword.setDisable(false);
                txtReEnterPassword.setDisable(false);
                txtPasswordEnter.setDisable(false);
                txtPasswordReEnter.setDisable(false);
            } else {
                txtTextOTP.clear();
                txtOTPVerification.setText("OTP is incorrect");
            }
            progressCollector=100;
        }
    }
}

