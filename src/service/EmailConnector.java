package service;


import com.email.durgesh.Email;
import javafx.concurrent.Task;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class EmailConnector extends Task<Boolean> {

    String mail;
    String text;
    String subject;
    File file;

    public EmailConnector() {
    }

    public EmailConnector(String mail, String text, String subject, File file) {
        this.mail = mail;
        this.text = text;
        this.subject = subject;
        this.file=file;
    }

    public EmailConnector(String mail, String subject , String text) {
        this.mail = mail;
        this.text = text;
        this.subject=subject;
        try {
            call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Boolean call() throws Exception {

        try {
            Email mail = new Email("isuruprabhath111@gmail.com", "ebwqevsvevkiyvpk");
            mail.setFrom("isuruprabhath111@gmail.com", "NLDB-FARM MANAGEMENT SYSTEM");
            mail.setSubject(subject);
            mail.setContent(text, "text/html");
            if(file!=null) {
                MimeBodyPart mbp1 = new MimeBodyPart();
                mbp1.setContent(text, "text/html");
                MimeBodyPart mbp2 = new MimeBodyPart();
                mbp2.attachFile(file);
                Multipart mp = new MimeMultipart();
                mp.addBodyPart(mbp1);
                mp.addBodyPart(mbp2);
                mail.addAttatchment(mp);
            }

            mail.addRecipient(this.mail);
            updateProgress(50,100);
            mail.send();
            updateProgress(99,100);
            Thread.sleep(250);
            return true;
        } catch (Exception ex) {
            updateMessage("Connection Error - Sending User Details Error");
        }
        return false;
    }
}

