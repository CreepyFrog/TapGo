/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.Artiste;
import tn.esprit.entities.Evenement;
import tn.esprit.services.ArtisteService;
import tn.esprit.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementBrowserFXMLController implements Initializable {

    EvenementService es = new EvenementService();
    ArtisteService as = new ArtisteService();
    
    @FXML
    private TextField NomEvenement;
    @FXML
    private ComboBox<Artiste> NomArtiste;
    @FXML
    private ComboBox<String> TypeDeMusique;
    @FXML
    private Button ButtonChercher;
    @FXML
    private ToggleGroup DateOptionGroup;
    @FXML
    private RadioButton DateSpecifique;
    @FXML
    private RadioButton DatePeriode;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private AnchorPane PaneListe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chargerEvenements(es.afficher());
    }

    @FXML
    private void chercher(ActionEvent event) {
    }
    
    private void chargerEvenements(ObservableList<Evenement> evenements){
        
    }
    
    
}
