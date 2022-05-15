/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Util.DB.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Menu;
import models.Plat;

/**
 * FXML Controller class
 *
 * @author YOUSSEF RIAHI
 */
public class MenuClientFXMLController implements Initializable {
    
      private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    
    ObservableList<Menu> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane recpane;
    @FXML
    private TableView<Menu> tablepl;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_adresse;
    @FXML
    private TableColumn<?, ?> col_domaine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       afficher();
    }  
    private void afficher(){
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
          col_adresse.setCellValueFactory(new PropertyValueFactory<>("type"));
          col_domaine.setCellValueFactory(new PropertyValueFactory<>("Restaurant"));
        
         
                    
            
        tablepl.setItems(list);
    }

       


    @FXML
    private void getSelected(MouseEvent event) {
    }


    @FXML
    private void menu(ActionEvent event) {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM Menu");
            while(rs.next()){
                Preferences UserId = Preferences.userRoot();
              
            list.add(new Menu (rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));    }
            } catch (SQLException ex) {
            Logger.getLogger(Gui.AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
    }
    
    

    
    

     



   
  

    

   

}
