/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class HomeUserController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneHome;
    @FXML
    private Button Reserver;
    @FXML
    private Button Table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    private void setInterface(String location) throws IOException {
        AnchorPaneHome.getChildren().clear();
        AnchorPaneHome.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    
    
    @FXML
    private void gotoReserver(ActionEvent event) throws IOException {
         setInterface("Reserver");
    }


    @FXML
    private void gotoTable(ActionEvent event) throws IOException {
         setInterface("Table");
    }
    
}
