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
    }

    @FXML
    private void Ajouter_Cour(ActionEvent event) {
        
        Cours_Services cs = new Cours_Services();
        Cours c = new Cours (lNom_Cour.getText(),llibelle.getText(),new Chefs(5));
        cs.ajouter_Cours(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("cour is added successfully!");
        alert.show();
        lNom_Cour.setText("");
        llibelle.setText("");
        
    }

    @FXML
    private void Supprimer_Cour(ActionEvent event) {
        Cours_Services cs = new Cours_Services();
        Cours c = new Cours();
        String nom=lNom_Cour.getText();
               
            c.setNom_cour(nom);
            cs.supprimer_Cours();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("cour supprimé avec succes!");
            alert.show();
            lNom_Cour.setText("");
    }
        

}
    

