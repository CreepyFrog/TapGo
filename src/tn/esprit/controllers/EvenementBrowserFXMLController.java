/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementBrowserFXMLController implements Initializable {

    @FXML
    private TextField NomEvenement;
    @FXML
    private ComboBox<?> NomArtiste;
    @FXML
    private ComboBox<?> TypeDeMusique;
    @FXML
    private Button ButtonChercher;
    @FXML
    private RadioButton DateOption;
    @FXML
    private ToggleGroup DateOptionGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chercher(ActionEvent event) {
    }
    
    
}
