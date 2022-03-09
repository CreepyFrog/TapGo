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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Chefs;
import tn.esprit.entities.Cours;
import tn.esprit.services.Chef_Services;
import tn.esprit.services.Cours_Services;
import tn.esprit.utils.DataSource;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Admin_CourController implements Initializable {


    private  Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    
    
    
    @FXML
    private TextField lNom_Cour;
    @FXML
    private TextField llibelle;
    @FXML
    private TableColumn<Cours, String> cnom;
    @FXML
    private TableColumn<Cours, String> clibelle;
    @FXML
    private TableView<Cours> Table_Cours;
    @FXML
    private TextField mots;
    
    ObservableList CoursList = FXCollections.observableArrayList();
   
    /**
     * Initializes the controller class.
     */
    
    
    public void initialize(URL url, ResourceBundle rb) {
     
        // TODO
        
        
        conn = DataSource.getInstance().getCnx();
 
       
       
       String sql="SELECT Nom_Cour,Libelle_Cour FROM Cours";
        try {
            ste=conn.prepareStatement(sql);
           ResultSet rs=ste.executeQuery(sql);
                while(rs.next()){
                Cours c = new Cours();
                c.setNom_cour(rs.getString("Nom_cour"));
                    
                c.setLibelle_Cour(rs.getString("Libelle_Cour"));
                CoursList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        cnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("Nom_cour"));
        clibelle.setCellValueFactory(new PropertyValueFactory<Cours, String>("Libelle_Cour"));
      
        
        
        Table_Cours.setItems(CoursList);
        
       
        FilteredList<Cours> filtredData= new FilteredList<> (CoursList, b->true);
        
        mots.textProperty().addListener((observable,oldValue,newValue) -> {
        filtredData.setPredicate(Cours -> {
        
        
        if (newValue.isEmpty() || newValue==null){
        return true;
        }
        
        String searchKeyword=newValue.toLowerCase();
        
        if(Cours.getNom_cour().toLowerCase().indexOf(searchKeyword )> -1){
        return true;
        }else 
            return false;
                
                });
    });
        
       SortedList<Cours> sortedData=new SortedList<>(filtredData);
       sortedData.comparatorProperty().bind(Table_Cours.comparatorProperty());
       Table_Cours.setItems(sortedData);
        
    }
     


    @FXML
    private void Refresh_Table(ActionEvent event) {
        try {
           
            CoursList.clear();
            
            String sql = "SELECT * FROM `Cours`";
             ste=conn.prepareStatement(sql);
               ResultSet rs=ste.executeQuery(sql);
            
            while (rs.next()){
                CoursList.add(new  Cours(
                        rs.getString("Nom_Cour"),
                        rs.getString("Libelle_Cour"),
                        new Chefs (rs.getInt("ID_Chef"))));                        
                Table_Cours.setItems(CoursList);
                
            }
            
            
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }

    @FXML
    private void Ajouter_Cour(ActionEvent event) throws AWTException {
        
        Cours_Services cs = new Cours_Services();
        Cours c = new Cours (lNom_Cour.getText(),llibelle.getText(),new Chefs(5));
        cs.ajouter_Cours(c);
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
        trayIcon.displayMessage("Gestion de formation", "cour ajoutée avec succée", MessageType.INFO);
        lNom_Cour.setText("");
        llibelle.setText("");
        
    }

    @FXML
    private void Supprimer_Cour(ActionEvent event) throws AWTException {
        Cours_Services cs = new Cours_Services();
        Cours c = new Cours();
        String nom=lNom_Cour.getText();
               
            c.setNom_cour(nom);
            cs.supprimer_Cours();
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
            lNom_Cour.setText("");
    }
        

}
    

