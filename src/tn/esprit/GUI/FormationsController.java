/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.entities.Chefs;
import tn.esprit.entities.Cours;
import tn.esprit.entities.Inscription;
import tn.esprit.services.Inscription_Service;

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
    private Button Button_Inscription2;
    @FXML
    private Button Button_Inscription3;

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
    
    
    
@FXML
    private void Ajout_Inscription(ActionEvent event  ) {
        
        List<Cours> Cours = new ArrayList<>();
        Inscription_Service IS = new Inscription_Service();
        Inscription I = new Inscription();
        
        
        
        I.setCours(new Cours());
        IS.ajouter_Inscription(I);
        
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Inscription is added successfully!");
        alert.show();
        
        
    }


  
  
 
    
}
