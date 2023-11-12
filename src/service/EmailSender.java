package service;
import controller.ForgotPasswordController;
import to.DTOTransfer;

import java.util.Random;

public class EmailSender {
    String Email;
    public static String OTP;
    public EmailSender() {}
    public EmailSender(String email) {

        Email = email;
        System.out.println(Email);
        genarateOTP();
    }

    EmailConnector emailConnector = new EmailConnector();

    public void genarateOTP(){
        String OTP2=null;
        int number=0;
        L1: while(true){
            Random num =new Random();
            number=num.nextInt(9999);
            if(number>1000){
                OTP2=String.valueOf(number);
                setOTP(OTP2);
                DTOTransfer dtoTransfer=new DTOTransfer(OTP2);
                break L1;
            }
        }

        EmailConnector emailConnector = new EmailConnector(Email, "Your new OTP getIn hear ", "Your OTP is "+ getOTP());

        System.out.println(getOTP());

         }

    public String getOTP(){
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
