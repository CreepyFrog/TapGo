/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.HeadlessException;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import models.Menu;
import models.Plat;
import models.Restaurant;
import service.ServicePlat;
import service.ServiceRestaurant;
import Util.DB.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PlatController implements Initializable {

    @FXML
    private TextField ftNom;
    @FXML
    private TextField ftelement;
    @FXML
    private Button btn;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_adresse;
    @FXML
    private TableColumn<?, ?> col_domaine;
    @FXML
    private Button btnu;
    
      private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    
    @FXML
    private ComboBox<String> ftmenu;
    ObservableList<Plat> list = FXCollections.observableArrayList();
    @FXML
    private TextField iddd;
    @FXML
    private TableView<Plat> tablepl;
    @FXML
    private TableColumn<?, ?> col_d;
    @FXML
    private AnchorPane recpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(PlatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
                 ServicePlat r = new ServicePlat();
      //  String c= String.valueOf(ftmenu.getValue());
        r.Ajouter(new Plat(ftNom.getText(),ftelement.getText(),new Menu(String.valueOf(ftmenu.getValue()))));
        list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
    }
     private void afficher(){
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
          col_adresse.setCellValueFactory(new PropertyValueFactory<>("element"));
          col_domaine.setCellValueFactory(new PropertyValueFactory<>("menu"));
        
         
                    
            
        tablepl.setItems(list);
    }
     @FXML
 private void getSelected(MouseEvent event) {
                  int index = tablepl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    ftNom.setText(col_nom.getCellData(index).toString());
    
    iddd.setText(col_id.getCellData(index).toString());
    ftelement.setText(col_adresse.getCellData(index).toString());
    ftmenu.setValue(col_domaine.getCellData(index).toString());
    
    
    
    }
    
    

     public void initialiserlist() throws SQLException {
        // TODO
             
            
            ftmenu.setPromptText("Chosir le menu correspondat");
              try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM plat");
            while(rs.next()){
                Preferences UserId = Preferences.userRoot();
              
            list.add(new Plat(rs.getInt(1),rs.getString(2), rs.getString(3),new Menu(rs.getString(4))));    }
            } catch (SQLException ex) {
            Logger.getLogger(Gui.AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
          
              Connection cnx = DataSource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT nom FROM menu ");
            // Now add the comboBox addAll statement
           while (rs.next()){
            ftmenu.getItems().addAll(rs.getString("nom"));
           
           }
        
    }    

    @FXML
    private void Edit(ActionEvent event) throws SQLException {
        try {
            cnx = DataSource.ConnectDb();
            String value0 = iddd.getText();
            String value1 = ftNom.getText();
            
            String value2 = ftelement.getText();
            
            String value3 = ftmenu.getValue();
            
           
            String sql = "update plat set nom= '"+value1+"',element= '"+value2+"',menu= '"+
                    value3+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
                list.clear();
                initialiserlist(); 
                 list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
               
            JOptionPane.showMessageDialog(null, "Update");
           
            
                iddd.setText("");

            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
    }

      public Plat gettemp(TableColumn.CellEditEvent edittedCell) {
        Plat test = (Plat) tablepl.getSelectionModel().getSelectedItem();
        return test;
    }
   private void Supprimer(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Plat x = gettemp(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServicePlat cat = new ServicePlat();

            int s = cat.deletere(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation deleted");
                alert.showAndWait();
              ;
                list.clear();
                initialiserlist(); 
                afficher();
                tablepl.refresh();
                  iddd.setText("");
    ftNom.setText("");
    ftmenu.setValue("");
    ftelement.setText("");
  
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        
        TableColumn.CellEditEvent edittedcell = null;
        Plat x = gettemp(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServicePlat cat = new ServicePlat();

            int s = cat.deleteplat(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Plat deleted");
                alert.showAndWait();
              ;
                list.clear();
                initialiserlist(); 
                afficher();
                tablepl.refresh();
                  iddd.setText("");
    ftNom.setText("");
    ftmenu.setValue("");
    ftelement.setText("");
  
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
    }

    @FXML
    private void rest(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutRestaurant.fxml"));
           recpane.getChildren().setAll(pane);
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("AddMenu.fxml"));
           recpane.getChildren().setAll(pane);
    }

    
}
