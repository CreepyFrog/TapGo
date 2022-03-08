/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import entities.Commentaire;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class CommfxmlController implements Initializable {

    @FXML
    private Pane CommBlock;
    @FXML
    private Label commBlockLabel;
    @FXML
    private AnchorPane Comm1Block;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setBlock(Commentaire c){
        commBlockLabel.setText(c.getLibelle_commentaire());
    }
    
}
