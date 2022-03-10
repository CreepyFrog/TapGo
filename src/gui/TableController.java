/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceReservation;
import service.ServiceTable;
import tn.esprit.entites.Reservation;
import tn.esprit.entites.Restaurant;
import tn.esprit.entites.User;
import tn.esprit.entites.table;
import tn.esprit.utils.DataSource;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class TableController implements Initializable {
    ObservableList<table> TRS;
    table aem;
    ServiceTable ts;
    Connection cnx = DataSource.getInstance().getConnection();
 ObservableList<table> TR =FXCollections.observableArrayList();
 
    @FXML
    private AnchorPane espaceTable;
    private TableView<table> ListTable;
    @FXML
    private JFXTextField TextID_Table;
    @FXML
    private JFXTextField TextType;
    @FXML
    private JFXTextField TextTableRestaurant;
    private TableColumn<table, Integer> Id_Tablecol;
    private TableColumn<table, Integer> Typecol;
    private TableColumn<table, Restaurant> Restaurantcol;
    @FXML
    private Button AjouterButton;
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
 /*
    @FXML
    private void searchLabel(ActionEvent event) {
        Id_Tablecol.setCellValueFactory(new PropertyValueFactory<table,Integer>("Id_Table"));
        Typecol.setCellValueFactory(new PropertyValueFactory<table,Integer>("Type_Table"));
        Restaurantcol.setCellValueFactory(new PropertyValueFactory<table,Restaurant>("rest"));
        
        ServiceTable r = new ServiceTable();
        TR= (ObservableList<table>) r.Afficher();
        ListTable.setItems(TR);
        FilteredList<table> filteredData = new FilteredList<>(TR, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //String lowerCaseFilter = newValue.toLowerCase();

                if (a.getType_Table() != null) {
                    return true; // Filter Auteur
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<table> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ListTable.comparatorProperty());
        ListTable.setItems(sortedData);
    }*/

    @FXML
    private void HandlerAjouter(ActionEvent event) throws AWTException {
      
    
        String value1 = TextID_Table.getText();
        String value2 = TextType.getText();
        String value3 = TextTableRestaurant.getText();
        
        ServiceTable sr= new ServiceTable();
        table t = new table();
        t.setId_Table(0);
        t.setType_Table(Integer.parseInt(TextType.getText()));
        t.setRest1(new Restaurant(17));
        
       // addReservationToBase(t);
        sr.Ajouter(t);
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
        trayIcon.displayMessage("Gestion des tables", "table ajouter avec succée", MessageType.INFO);
    }
    
    public void addTableToBase(table t) {
         try{
        Statement st=cnx.createStatement();  
        String req="INSERT INTO table_restaurant(Type_Table,Id_Restaurant) VALUES ("+t.getType_Table()+","+t.getRest1().getId_restaurant()+")";;
        st.executeUpdate(req);
        System.out.println("table ajouté !");
        }catch(SQLException ex){    
        System.err.println(ex.getMessage());
        }
    }

    private void HandlerAfficher(ActionEvent event) {
        
    }

    private void HandlerDelete(ActionEvent event) {
                Supprimer(ListTable.getSelectionModel().getSelectedItem().getId_Table());
        ListTable.getItems().removeAll(ListTable.getSelectionModel().getSelectedItem()); 
        
       
    }

    @FXML
    private void HandlerModifier(ActionEvent event) {
    }

    
    private void setInterface(String location) throws IOException {
        espaceTable.getChildren().clear();
        espaceTable.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    
    
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        setInterface("HomeUser");
    }

    
}
