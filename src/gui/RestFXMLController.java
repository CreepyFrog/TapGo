/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Util.DB.DataSource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Restaurant;

/**
 * FXML Controller class
 *
 * @author YOUSSEF RIAHI
 */
public class RestFXMLController implements Initializable {
 Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private AnchorPane Pub1Block;
    @FXML
    private Pane PubBlock;
    @FXML
    private Label id_id;
    @FXML
    private Label restBlockLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setBlock(Restaurant r){
//        +" \n " +p.getId_Publication()
        restBlockLabel.setText("Nom Restaurant :  "+r.getNom()+"\nAdresse:  "+r.getAdresse()+"\nDomaine :  " + r.getDomaine());
//        pubBlockLabel.setText(p.getId_Publication());
        
        
    }
    
    

    
}
