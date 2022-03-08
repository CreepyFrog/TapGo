/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tn.esprit.entities.Artiste;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ArtisteFrontFXMLController implements Initializable {

    @FXML
    private Label NomArtiste;
    @FXML
    private Label TypeDeMusique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loadData(Artiste a){
        NomArtiste.setText(a.getNom_Artiste());
        TypeDeMusique.setText(a.getType_De_Musique());
    }
}
