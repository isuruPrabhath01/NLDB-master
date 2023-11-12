package service;


import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgessBar implements Initializable{


        public ProgressBar prbProgressBar;
        public Text txtLoadingText;
        public Text txtText;
        LoadingScreen loadingScreen;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            loadingScreen = new LoadingScreen(prbProgressBar, txtLoadingText);
            startProgress();
        }
        void startProgress() {
            Thread thread = new Thread(loadingScreen);
            thread.setDaemon(true);
            thread.start();
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
                    if (k<=20){
                        txtText.setText("Initializing Application....");
                    }else if (k<=40){
                        txtText.setText("Loading Internal Resources....");
                    }else if (k<=60){
                        txtText.setText("Loading UIs....");
                    }else if (k<=80){
                        txtText.setText("Loading Images....");
                    }else if (k<100){
                        txtText.setText("Loading Images....");
                    }
                }
                txtLoadingText.setText("100%...");
                txtText.setText("Welcome to NLDB FMS v1.0.0");

            }
        }
}

