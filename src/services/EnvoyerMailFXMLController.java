/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class EnvoyerMailFXMLController implements Initializable {

    @FXML
    private TextField lnom;
    @FXML
    private TextField lemail;
    @FXML
    private AnchorPane AnchorPaneSendMail;
    @FXML
    private Button BackHome;
    @FXML
    private Button sendMail_Button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Envoyer_mail(ActionEvent event) throws Exception {
        Boolean ok=false;
        if ((lnom.getText().toString().compareTo("") == 0) || (lemail.getText().toString().compareTo("")==0) ) {
         ok=true;
            JOptionPane.showMessageDialog(null, "Empty Field");
        }
        else {
        EnvoyerMail.sendMail(lemail.getText(), lnom.getText());
        }
        
    }

    @FXML
    private void goToEspaceForum(ActionEvent event) throws IOException {
        AnchorPaneSendMail.getChildren().clear();
        AnchorPaneSendMail.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/GUI/ForumInterface.fxml")));
    }
    
}
