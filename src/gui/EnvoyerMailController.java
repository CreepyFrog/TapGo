/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.EnvoyerMail;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
/**
 * FXML Controller class
 *
 * @author Hp
 */
public class EnvoyerMailController implements Initializable {

    @FXML
    private AnchorPane espaceReservation;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton EnvoyerMail;
    @FXML
    private JFXTextField TextClient;
    @FXML
    private JFXTextField TextD;
    @FXML
    private JFXTextField TextEmail;
    @FXML
    private javafx.scene.control.Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchLabel(ActionEvent event) {
    }

    
    
      public static void sendMail(String recepient,String a , String b) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "houssem.setti@esprit.tn";
        String password = "181JMT3056";
        
        
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
          Message message = prepareMessage(session, myAccountEmail,recepient,a,b); 
          
          Transport.send(message);
          System.out.println("Message sent successfuly");
        }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String a,String b) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My first Email from Java");
            message.setText("Client : "+a+" Reservation confirmé pour la date du: "+b);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(EnvoyerMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
}
    
    
    
           private void setInterface(String location) throws IOException {
        espaceReservation.getChildren().clear();
        espaceReservation.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    
    
    
    @FXML
    private void Envoyer(ActionEvent event) throws Exception {
        String mail = TextEmail.getText();
        String date = TextD.getText();
        String client = TextClient.getText();
        
        sendMail(mail,client,date);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
         setInterface("HomeReservation");
    }
    
}
