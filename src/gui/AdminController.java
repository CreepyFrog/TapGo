/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import models.Chefs;
import service.Chef_Services;
import Util.DB.DataSources;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AdminController implements Initializable {
   private  Connection conn;
    private PreparedStatement pst;
    private Statement ste;
        
    @FXML
    private TextField lNom_Chef;
    @FXML
    private TextField lCour_associé;
    @FXML
    private TextField lAdresse;
    
    @FXML
    private TableColumn<Chefs, Integer> cId;
    @FXML
    private TableColumn<Chefs, String> cNom;
    @FXML
    private TableColumn<Chefs, String> ccour;
    @FXML
    private TableColumn<Chefs, String> cadresse;
    @FXML
    private TableView<Chefs> Table_Chefs;
    @FXML
    private TextField mots;
    
    ObservableList ChefsList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane paneAdmin;
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
         
        conn = DataSources.getInstance().getCnx();
 
       
       
       String sql="SELECT * FROM Chefs";
        try {
            ste=conn.prepareStatement(sql);
           ResultSet rs=ste.executeQuery(sql);
                while(rs.next()){
                Chefs ch = new Chefs();
                //ch.setID_Chef(rs.getInt("ID_Chef"));
                ch.setNom_Chef(rs.getString("Nom_Chef"));
                ch.setCours_Associe(rs.getString("Cours_Associe"));
                ch.setAdresse_Chef(rs.getString("Adresse_Chef")) ;
                ChefsList.add(ch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        //cId.setCellValueFactory(new PropertyValueFactory<Chefs, Integer>("ID_Chef"));
        cNom.setCellValueFactory(new PropertyValueFactory<Chefs, String>("Nom_Chef"));
        ccour.setCellValueFactory(new PropertyValueFactory<Chefs, String>("Cours_Associe"));
        cadresse.setCellValueFactory(new PropertyValueFactory<Chefs, String>("Adresse_Chef"));
        
        
        Table_Chefs.setItems(ChefsList);        
        
        
       
        FilteredList<Chefs> filtredData= new FilteredList<> (ChefsList, b->true);
        
        mots.textProperty().addListener((observable,oldValue,newValue) -> {
        filtredData.setPredicate(Chefs -> {
        
        
        if ( (newValue.isEmpty()) || (newValue == null)){
        return true;
        }
        
        String searchKeyword=newValue.toLowerCase();
        
        if( (Chefs.getNom_Chef().toLowerCase().indexOf(searchKeyword )) > -1){
        return true;
        }else 
            return false;
                
                });
    });
        
       SortedList<Chefs> sortedData=new SortedList<>(filtredData);
       sortedData.comparatorProperty().bind(Table_Chefs.comparatorProperty());
        Table_Chefs.setItems(sortedData);
        
        
    }    

    @FXML
    private void Ajouter_Chef(ActionEvent event) throws AWTException {
        
        Chef_Services ch = new Chef_Services();
        Chefs c = new Chefs (lNom_Chef.getText(),lCour_associé.getText(),lAdresse.getText());
        ch.ajouter_Chef(c);
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
        trayIcon.displayMessage("Gestion de formation", "Chef ajoutée avec succée", MessageType.INFO);
        lNom_Chef.setText("");
        lCour_associé.setText("");
        lAdresse.setText("");
    }

    

    
    
    @FXML
    private void Supprimer_Chef(ActionEvent event) throws SQLException, AWTException {
        

        Chef_Services cs = new Chef_Services();
        System.out.println(lNom_Chef.getText());
        Chefs ch = new Chefs();
        String nom=lNom_Chef.getText();
               
            ch.setNom_Chef(nom);
            cs.supprimer_Chef(nom);
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
        trayIcon.displayMessage("Gestion de formation", "Chef supprimé avec succée", MessageType.INFO);
            lNom_Chef.setText("");
            
    }
    
    
private void getSelected(MouseEvent event) 

{           
    
Chefs clickedChefs = Table_Chefs.getSelectionModel().getSelectedItem();         
cNom.setText(String.valueOf(clickedChefs.getNom_Chef()));         
ccour.setText(String.valueOf(clickedChefs.getCours_Associe()));         
cadresse.setText(String.valueOf(clickedChefs.getAdresse_Chef()));   


 }
    

//    public void showChef() {
//         Chef_Services  cs= new Chef_Services();
//    	ObservableList<Chefs> list = (ObservableList<Chefs>) cs.afficher();
//    	
//    	cNom.setCellValueFactory(new PropertyValueFactory<Chefs,String>("Nom_Chef"));
//    	ccour.setCellValueFactory(new PropertyValueFactory<Chefs,String>("cour_associe"));
//        cadresse.setCellValueFactory(new PropertyValueFactory<Chefs,String>("Adresse_Chef"));
//    
//    	
//    	Table_Chefs.setItems(list);
//    } 

    @FXML
    private void Refresh_Table(ActionEvent event) {
        
        try {
           
            ChefsList.clear();
            
            String sql = "SELECT * FROM `Chefs`";
             ste=conn.prepareStatement(sql);
               ResultSet rs=ste.executeQuery(sql);
            
            while (rs.next()){
                ChefsList.add(new  Chefs(
                        rs.getInt("ID_Chef"),
                        rs.getString("Nom_Chef"),
                        rs.getString("Cours_Associe"),
                        rs.getString("Adresse_Chef")));                        
                Table_Chefs.setItems(ChefsList);
                
            }
            
            
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
     private void setInterface(String location) throws IOException {
        paneAdmin.getChildren().clear();
        paneAdmin.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    

    @FXML
    private void addCour(ActionEvent event) throws IOException {
        
        setInterface("Admin_Cour");
        
    }

    @FXML
    private void print(ActionEvent event) {
 
    }














   
} 
    
    

