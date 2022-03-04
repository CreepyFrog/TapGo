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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Artiste;
import tn.esprit.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ArtisteController implements Initializable {
private Connection conn = DataSource.getInstance().getConnection();;
    private PreparedStatement pst;
    @FXML
    private TextField NomArtiste;
    @FXML
    private TextField TypeMusique;
    @FXML
    private Button ButtonAjouter;
    @FXML
    private TableView listeArtiste;
    @FXML
    private TableColumn Id_Artiste;
    @FXML
    private TableColumn Nom_Artiste;
    @FXML
    private TableColumn Type_De_Musique;
    @FXML
    private Label AddSucces;
    @FXML
    private Label AddEchec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getAllArtistes();
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
    @FXML
    private void addArtiste_Interface(ActionEvent event) {
        Artiste a =new Artiste();
        AddSucces.setVisible(false);
        AddEchec.setVisible(false);
        a.setNom_Artiste(NomArtiste.getText());
        a.setType_De_Musique(TypeMusique.getText());
        ajouter(a);
        AddSucces.setVisible(true);
        getAllArtistes();
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
        
        AddSucces.setVisible(false);
        AddEchec.setVisible(false);
        getAllArtistes();
        
    }
    
    public void getAllArtistes() {
        ObservableList<Artiste> la = afficher();
        Id_Artiste.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("Id_Artiste"));
        Nom_Artiste.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Nom_Artiste"));
        Type_De_Musique.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Type_De_Musique"));
        listeArtiste.setItems(la);
    }
    
}
