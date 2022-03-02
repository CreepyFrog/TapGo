/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.entities.Artiste;
import tn.esprit.services.ArtisteService;
import tn.esprit.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ArtisteBackFXMLController implements Initializable {
    
    @FXML
    private Button addArtiste;
    @FXML
    private Button showArtistes;
    @FXML
    private TextField nomArtiste;
    @FXML
    private TextField typeDeMusique;
    @FXML
    private Label ajoutSucces;
    @FXML
    private Label ajoutEchec;
    
    ArtisteService as = new ArtisteService();
    
    //private Connection conn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //conn = DataSource.getInstance().getConnection();
        
    }    
    
    @FXML
    private void addArtiste(ActionEvent event) {
        
        Artiste a = new Artiste(nomArtiste.getText(),typeDeMusique.getText());
        
        ajoutSucces.setVisible(false);
        ajoutEchec.setVisible(false);
        
        as.ajouter(a);
        nomArtiste.setText("");
        typeDeMusique.setText("");
        ajoutSucces.setVisible(true);
    }
    
    @FXML void showArtistes(ActionEvent event) {
        
    }
}
