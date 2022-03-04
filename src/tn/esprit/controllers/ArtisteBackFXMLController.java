/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Artiste;
import tn.esprit.services.ArtisteService;
import tn.esprit.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ArtisteBackFXMLController implements Initializable {
    
    /*
    @FXML
    private Button addArtiste;
    @FXML
    private Button showArtistes;
    @FXML
    */
    private TextField nomArtiste;
    private TextField typeDeMusique;
    private Label ajoutSucces;
    private Label ajoutEchec;
    
    private TableView<Artiste> listeArtiste;
    private TableColumn<Artiste, Integer> Id_Artiste;
    private TableColumn<Artiste, String> Nom_Artiste;
    private TableColumn<Artiste, String> Type_De_Musique;
    
    private Connection conn;
    private PreparedStatement pst;
    
    
    
    ArtisteService as = new ArtisteService();
    @FXML
    private Button ButtonAjouter;
    @FXML
    private TextField NomArtiste;
    @FXML
    private TextField TypeMusique;
    
    //private Connection conn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //conn = DataSource.getInstance().getConnection();
        
    }    
    
    public void ajouter(Artiste a) {
        String req = "INSERT INTO `artiste`(`Nom_Artiste`, `Type_De_Musique`) VALUES (?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, a.getNom_Artiste());
            pst.setString(2,a.getType_De_Musique());
            
            pst.executeUpdate();
            System.out.println("Artiste ajouté");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void addArtiste(ActionEvent event) {
        
        Artiste a = new Artiste();
//        nomArtiste.getText(),typeDeMusique.getText()
        
        ajoutSucces.setVisible(false);
        ajoutEchec.setVisible(false);
        a.setNom_Artiste(NomArtiste.getText());
        a.setType_De_Musique(TypeMusique.getText());
        this.ajouter(a);
        nomArtiste.setText("");
        typeDeMusique.setText("");
        ajoutSucces.setVisible(true);
    }
    
    private ObservableList<Artiste> afficher() {
        ObservableList<Artiste> artistes = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `artiste`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Artiste a = new Artiste();
                
                a.setId_Artiste(rs.getInt("Id_Artiste"));
                a.setNom_Artiste(rs.getString(2));
                a.setType_De_Musique(rs.getString(3));
                
                artistes.add(a);
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return artistes;
    }
    
    @FXML void showArtistes(ActionEvent event) {
        
        ObservableList<Artiste> la = afficher();
        Id_Artiste.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("Id_Artiste"));
        Nom_Artiste.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Nom_Artiste"));
        Type_De_Musique.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Type_De_Musique"));
        listeArtiste.setItems(la);
        
    }

    @FXML
    private void addArtiste_Interface(ActionEvent event) {
        
        Artiste a = new Artiste();
//        nomArtiste.getText(),typeDeMusique.getText()
        
        ajoutSucces.setVisible(false);
        ajoutEchec.setVisible(false);
        a.setNom_Artiste(NomArtiste.getText());
        a.setType_De_Musique(TypeMusique.getText());
        this.ajouter(a);
        nomArtiste.setText("");
        typeDeMusique.setText("");
        ajoutSucces.setVisible(true);
    }
}
