/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceTable;
import models.Restaurant;
import models.table;
import Util.DB.DataSources;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AfficherTableAdminController implements Initializable {
  ObservableList<table> TRS;
    table aem;
    ServiceTable ts;
    Connection cnx = DataSources.getInstance().getCnx();
 ObservableList<table> TR =FXCollections.observableArrayList();
 
    @FXML
    private AnchorPane espaceTable;
    @FXML
    private TableView<table> ListTable;
    @FXML
    private TableColumn<table, Integer> Id_Tablecol;
    @FXML
    private TableColumn<table, Integer> Typecol;
    @FXML
    private TableColumn<table, Restaurant> Restaurantcol;
    @FXML
    private TextField search;
    @FXML
    private Button AjouterButton;
    @FXML
    private Button AfficherButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button ModifierButton;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            ServiceTable t = new ServiceTable();
        TR = t.Afficher();
        getAllTable();
    }    

      public ObservableList<table> getListTable() { 
      ObservableList<table> TR =FXCollections.observableArrayList();
        try{
        String requete = "SELECT * FROM table_restaurant";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
            table b = new table();
            b.setId_Table(rs.getInt(1));
            b.setType_Table(rs.getInt(2));
            b.setRest1(new Restaurant(rs.getInt(3)));
            TR.add(b);
        }
        System.out.println("table affiché!");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        System.out.println(TR);
        return TR;
}
 public void getAllTable(){
        ObservableList<table> TR =getListTable();
        
        Id_Tablecol.setCellValueFactory(new PropertyValueFactory<table,Integer>("Id_Table"));
        Typecol.setCellValueFactory(new PropertyValueFactory<table,Integer>("Type_Table"));
        Restaurantcol.setCellValueFactory(new PropertyValueFactory<table,Restaurant>("Rest1"));
        
        ListTable.setItems(TR);
        System.out.println("Houssemmmm");
        
        }  
    
     public void Supprimer(int id) {
        try{
        String requete = "DELETE FROM table_restaurant WHERE Id_Table=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("table Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
     
     
     
    @FXML
    private void searchLabel(ActionEvent event) {
    }

    @FXML
    private void HandlerAjouter(ActionEvent event) {
    }

    @FXML
    private void HandlerAfficher(ActionEvent event) {
         getAllTable();
    }

    @FXML
    private void HandlerDelete(ActionEvent event) throws AWTException {
            Supprimer(ListTable.getSelectionModel().getSelectedItem().getId_Table());
        ListTable.getItems().removeAll(ListTable.getSelectionModel().getSelectedItem()); 
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Gestion des tables", "table supprimé avec succée", MessageType.INFO);
        getAllTable();
    }
    
    private void setInterface(String location) throws IOException {
        espaceTable.getChildren().clear();
        espaceTable.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }

    
    @FXML
    private void HandlerModifier(ActionEvent event) {
    }

   

    @FXML
    private void goBack(ActionEvent event) throws IOException {
         setInterface("HomeReservation");
    }
    
}
