/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import models.Restaurant;
import service.ServiceRestaurant;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutRestaurantController implements Initializable {

    @FXML
    private TextField ftNom;
    @FXML
    private TextField ftAdresse;
    @FXML
    private Button btn;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_adresse;
    @FXML
    private Button btnd;
    ArrayList name = new ArrayList();
    @FXML
    private TextField fid;
    @FXML
    private Button btnu;
    @FXML
    private TextField ftR;
    
      private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    
    @FXML
    private ComboBox<String> ftdomaine;
    @FXML
    private ComboBox<String> ftowner;
    ObservableList<Restaurant> list = FXCollections.observableArrayList();
    
 
    @FXML
    private TableView<Restaurant> tablerestaurant;
    @FXML
    private TableColumn<?, ?> col_domaine;
    @FXML
    private TableColumn<?, ?> col_owner;
    @FXML
    private TextField iddd;
    @FXML
    private TableColumn<?, Integer> col_owner1;
    @FXML
    private TextField ftplace;
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
            Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
       
           
    }  
     private void afficher(){
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
          col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
          col_domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
          col_owner.setCellValueFactory(new PropertyValueFactory<>("owner"));
          col_owner1.setCellValueFactory(new PropertyValueFactory<>("nb"));
         
            
            
        tablerestaurant.setItems(list);
    }
    
    public void initialiserlist() throws SQLException {
        // TODO
             
            
            ftowner.setPromptText("Les restaurants ");
            ftdomaine.setPromptText("Chosir le domaine");
              try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM restaurant");
            while(rs.next()){
                Preferences UserId = Preferences.userRoot();
              
            list.add(new Restaurant(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));    }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
               ftdomaine.getItems().addAll("chinoix","italien","tunisien");
          
              Connection cnx = DataSource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT name FROM users where role='owner'");
            // Now add the comboBox addAll statement
           while (rs.next()){
               
            ftowner.getItems().addAll(rs.getString("name"));
           
           }
        
    }    

  
    @FXML
    private void getSelected(MouseEvent event) {
                  int index = tablerestaurant.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    ftNom.setText(col_nom.getCellData(index).toString());
    ftdomaine.setValue(col_domaine.getCellData(index).toString());
    ftAdresse.setText(col_adresse.getCellData(index).toString());
    ftowner.setValue(col_owner.getCellData(index).toString());
     ftplace.setText(col_owner1.getCellData(index).toString());
    
    
    
    }
 public Restaurant gettemp(TableColumn.CellEditEvent edittedCell) {
        Restaurant test = (Restaurant) tablerestaurant.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    private void SupprimerStade(ActionEvent event) throws SQLException {
         TableColumn.CellEditEvent edittedcell = null;
        Restaurant x = gettemp(edittedcell);

        if (x != null) {

            int i = x.getId_restaurant();
            
            ServiceRestaurant cat = new ServiceRestaurant();

            int s = cat.deleterestaurant(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation deleted");
                alert.showAndWait();
                
                list.clear();
                initialiserlist(); 
                afficher();
                tablerestaurant.refresh();
                  iddd.setText("");
    ftNom.setText("");
    ftAdresse.setText("");
    ftowner.setValue("Owner");
    ftdomaine.setValue("");
   
    
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
 public void Edit () throws SQLException{   
        try {
            cnx = DataSource.ConnectDb();
            String value0 = iddd.getText();
            String value2 = ftNom.getText();
            
            String value6 = ftAdresse.getText();
            
            String value3 = ftowner.getValue();
            
            String value1 = ftdomaine.getValue();
            String value4 = ftplace.getText();
            
            String sql = "update restaurant set nom= '"+value2+"',adresse= '"+value6+"',domaine= '"+
                    value1+"',owner= '"+value3+"' ,nb= '"+value4+"'  where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
           
            JOptionPane.showMessageDialog(null, "Update");
           
                
                list.clear();
                initialiserlist(); 
                afficher();
                tablerestaurant.refresh(); 
       iddd.setText("");
    ftNom.setText("");
    ftAdresse.setText("");
    ftowner.setValue("Owner");
    ftdomaine.setValue("");
    ftplace.setText("");
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }list.clear();
        initialiserlist(); 
                afficher();
                tablerestaurant.refresh();
    }

    @FXML
    private void SearchStade(ActionEvent event) {
    }

    @FXML
    private void BPDF(ActionEvent event) {
    }

    @FXML
    private void savetoword(ActionEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
         ServiceRestaurant r = new ServiceRestaurant();
        int f = Integer.parseInt(ftplace.getText());
        r.Ajouter(new Restaurant(ftNom.getText(),ftAdresse.getText(),ftdomaine.getValue(),ftowner.getValue(),f));
        list.clear();
        initialiserlist(); 
                afficher();
                tablerestaurant.refresh();
    }

    @FXML
    private void rest(ActionEvent event) throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("AddMenu.fxml"));
           recpane.getChildren().setAll(pane);
    }

    @FXML
    private void Plats(ActionEvent event) throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("Plat.fxml"));
           recpane.getChildren().setAll(pane);
    }
    
}
