/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
 * @author TECHNOPC
 */
public class DesignController implements Initializable {

    @FXML
    private AnchorPane rec;
    @FXML
    private Button btn;
    @FXML
    private Button btnr1;
    @FXML
    private AnchorPane recpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    private void t1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Reserver.fxml"));
           recpane.getChildren().setAll(pane);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginInterface.fxml"));
           rec.getChildren().setAll(pane);
    }
    
}
