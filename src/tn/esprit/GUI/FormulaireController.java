/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.esprit.services.EnvoyerMail;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FormulaireController implements Initializable {

    @FXML
    private TextField lnom;
    @FXML
    private TextField lcour;
    @FXML
    private TextField lemail;
    @FXML
    private AnchorPane pane_formulaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 private void setInterface(String location) throws IOException {
        pane_formulaire.getChildren().clear();
        pane_formulaire.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/tn/esprit/GUI/" + location + ".fxml")));
    }
    @FXML
    private void Envoyer_mail(ActionEvent event) throws Exception {
        
        String nom=lnom.getText();
        String Ncour=lcour.getText();
        String mail=lemail.getText();
            
    EnvoyerMail.sendMail(mail,nom,Ncour);
     
    }


    @FXML
    private void Consulter_cour(ActionEvent event) throws IOException {
        setInterface("Ajout1");
    }
    
}
