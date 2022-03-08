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
import entities.Publication;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class PubController implements Initializable {

    @FXML
    private AnchorPane Pub1Block;
    @FXML
    private Pane PubBlock;
    @FXML
    private Label pubBlockLabel;
    @FXML
    private AnchorPane AnchorPaneBlockPub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setBlock(Publication p){
        pubBlockLabel.setText(p.getLibelle_Publication());
    }
    
}
