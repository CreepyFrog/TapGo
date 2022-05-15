/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ResourceBundle;
//import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
import models.Chefs;
import models.Cours;
import models.Inscription;
import service.Inscription_Service;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FormationsController implements Initializable {
    private  Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    @FXML
    private AnchorPane pane_formation;
    @FXML
    private Button Button_Inscription3;
    @FXML
    private Button Button_Inscription2;
    @FXML
    private Button Button_Inscription31;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
//    public List<Cours> afficher_Cours() {
//         
//       List<Cours> Cours = new ArrayList<>();
//        
//        String req = "SELECT * from `Cours` WHERE `Nom_Cour`=Pasta ";
//        
//        try {
//            pst = conn.prepareStatement(req);
//            ResultSet rs= pst.executeQuery();
//            
//            while(rs.next()){
//                Cours cc = new Cours();
//                cc.setId_Cour( rs.getInt("Id_Cour") );
//                cc.setNom_cour(rs.getString(2));
//                cc.setLibelle_Cour(rs.getString(3));
//                cc.setChefs(new Chefs (rs.getInt(4)));
//                Cours.add(cc);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        
//        return Cours;
//        
//    }
    
    private void setInterface(String location) throws IOException {
        pane_formation.getChildren().clear();
        pane_formation.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    
    
    @FXML
    private void Ajout1_Inscription(ActionEvent event) throws IOException {
    setInterface("Formulaire");
    }

    @FXML
    private void Ajout2_Inscription(ActionEvent event) throws IOException {
        
         setInterface("Formulaire"); 
    }

    

    @FXML
    private void Ajout3_Inscription(ActionEvent event) throws IOException {
         
        setInterface("Formulaire");
        
    }


  
  
 
    
}
