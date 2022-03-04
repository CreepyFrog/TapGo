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
    private TextField search;
    @FXML
    private AnchorPane espaceTable;
    @FXML
    private TableView<table> ListTable;
    @FXML
    private JFXTextField TextID_Table;
    @FXML
    private JFXTextField TextType;
    @FXML
    private JFXTextField TextTableRestaurant;
    @FXML
    private TableColumn<table, Integer> Id_Tablecol;
    @FXML
    private TableColumn<table, Integer> Typecol;
    @FXML
    private TableColumn<table, Restaurant> Restaurantcol;
    @FXML
    private Button AjouterButton;
    @FXML
    private Button AfficherButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button ModifierButton;

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
            b.setrest(new Restaurant(rs.getInt(3)));
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
        Restaurantcol.setCellValueFactory(new PropertyValueFactory<table,Restaurant>("rest"));
        
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
 
   /* @FXML
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
                String lowerCaseFilter = newValue.toLowerCase();

                if (a.getType_Table().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
    private void HandlerAjouter(ActionEvent event) {
      
    
        String value1 = TextID_Table.getText();
        String value2 = TextType.getText();
        String value3 = TextTableRestaurant.getText();
        
        ServiceTable sr= new ServiceTable();
        table t = new table();
        t.setId_Table(0);
        t.setType_Table(Integer.parseInt(TextType.getText()));
        t.setrest(new Restaurant(17));
        
       // addReservationToBase(t);
        sr.Ajouter(t);
    }
    
    public void addTableToBase(table t) {
         try{
        Statement st=cnx.createStatement();  
        String req="INSERT INTO table_restaurant(Type_Table,Id_Restaurant) VALUES ("+t.getType_Table()+","+t.getrest().getId_restaurant()+")";;
        st.executeUpdate(req);
        System.out.println("table ajouté !");
        }catch(SQLException ex){    
        System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void HandlerAfficher(ActionEvent event) {
        getAllTable();
    }

    @FXML
    private void HandlerDelete(ActionEvent event) {
                Supprimer(ListTable.getSelectionModel().getSelectedItem().getId_Table());
        ListTable.getItems().removeAll(ListTable.getSelectionModel().getSelectedItem()); 
        
        getAllTable();
    }

    @FXML
    private void HandlerModifier(ActionEvent event) {
    }
    
}
