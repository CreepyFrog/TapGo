/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Evenement;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementFrontFXMLController implements Initializable {
    
    @FXML
    private Label NomArtiste;
    @FXML
    private Label NomEvenement;
    @FXML
    private Label DateEvenement;
    @FXML
    private Label NomRestaurant;
    @FXML
    private Label TypeDeMusique;
    @FXML
    private AnchorPane AnchorPaneArtiste;
    @FXML
    private VBox eButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loadData(Evenement e){
        NomEvenement.setText(e.getNom_Evenement());
        DateEvenement.setText(e.getDate_Evenement().toString());
        NomArtiste.setText(e.getArtiste().getNom_Artiste());
        TypeDeMusique.setText(e.getArtiste().getType_De_Musique().getGenre());
        NomRestaurant.setText(e.getRestaurant().getNom());
    }
}
