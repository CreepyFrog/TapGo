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
import service.ServiceMenu;
import service.ServicePlat;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddMenuController implements Initializable {

    @FXML
    private TextField ftNom;
    @FXML
    private Button btn;
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
    @FXML
    private TableColumn<?, ?> col_d;
    @FXML
    private Button btnu;
    @FXML
    private ComboBox<String> ftrestaurant;
    @FXML
    private TextField iddd;
    @FXML
    private ComboBox<String> fttype;
     private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    
    
    ObservableList<Menu> list = FXCollections.observableArrayList();
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
            Logger.getLogger(AddMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
    }    
    
     public void initialiserlist() throws SQLException {
        // TODO
             
            
            fttype.setPromptText("Chosir le type  ");
             fttype.setPromptText("Chosir le restaurant  ");
              try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM Menu");
            while(rs.next()){
                Preferences UserId = Preferences.userRoot();
              
            list.add(new Menu (rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));    }
            } catch (SQLException ex) {
            Logger.getLogger(Gui.AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
                fttype.getItems().addAll("sandwich","plat");
           
          
              Connection cnx = DataSource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT nom FROM restaurant ");
            // Now add the comboBox addAll statement
           while (rs.next()){
            ftrestaurant.getItems().addAll(rs.getString("nom"));
           
           }
        
    }  
     private void afficher(){
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
          col_adresse.setCellValueFactory(new PropertyValueFactory<>("type"));
          col_domaine.setCellValueFactory(new PropertyValueFactory<>("Restaurant"));
        
         
                    
            
        tablepl.setItems(list);
    }

    @FXML
   private void Ajouter(ActionEvent event) throws SQLException {
                 ServiceMenu r = new ServiceMenu();
        String c= String.valueOf(ftrestaurant.getValue());
        String v= String.valueOf(fttype.getValue());
        
        r.ajouter(new Menu(ftNom.getText(),v,c));
        list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = tablepl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    iddd.setText(col_id.getCellData(index).toString());
    fttype.setValue(col_adresse.getCellData(index).toString());
    ftNom.setText(col_nom.getCellData(index).toString());
    ftrestaurant.setValue(col_domaine.getCellData(index).toString());
   
    
    
    }
       @FXML
 public void Edit () throws SQLException{   
        try {
            cnx = DataSource.ConnectDb();
            String value0 = iddd.getText();
            String value1 = ftNom.getText();
            
            String value2 = fttype.getValue();
            
            String value3 = ftrestaurant.getValue();
            
         
            String sql = "update menu set nom= '"+value1+"',type= '"+value2+"',restaurant= '"+
                    value3+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
            
                list.clear();
                initialiserlist(); 
                afficher();
                tablepl.refresh();
            JOptionPane.showMessageDialog(null, "Update");
           
             list.clear();
                initialiserlist(); 
                afficher();
                tablepl.refresh(); 
                iddd.setText("");
    fttype.setValue("");
    /*prod.setText("");*/
    ftrestaurant.setValue("");
    ftNom.setText("");
    
   
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }
     @FXML
   private void delete(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Menu x = gettempReclamation(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServiceMenu cat = new ServiceMenu();

            int s = cat.deletemenu(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("menu deleted");
                alert.showAndWait();
                  list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
               
                  iddd.setText("");
    fttype.setValue("");
    ftrestaurant.setValue("");
    ftNom.setText("");
 
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
    public Menu gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Menu test = (Menu) tablepl.getSelectionModel().getSelectedItem();
        return test;
    }

    @FXML
    private void rest(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutRestaurant.fxml"));
           recpane.getChildren().setAll(pane);
    }

    @FXML
    private void Plats(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("Plat.fxml"));
           recpane.getChildren().setAll(pane);
    }

  
    
    
}
