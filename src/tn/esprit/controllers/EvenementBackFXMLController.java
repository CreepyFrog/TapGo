/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Artiste;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.Restaurant;
import tn.esprit.services.ArtisteService;
import tn.esprit.services.EvenementService;
import tn.esprit.services.RestaurantService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementBackFXMLController implements Initializable {
    
    EvenementService es = new EvenementService();
    ArtisteService as = new ArtisteService();
    RestaurantService rs = new RestaurantService();
    Evenement selected = null;

    @FXML
    private TextField NomEvenement;
    @FXML
    private DatePicker DateEvenement;
    @FXML
    private ComboBox<Artiste> ArtisteEvenement;
    @FXML
    private ComboBox<Restaurant> RestaurantEvenement;
    @FXML
    private Button buttonAjouter;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonModifier;
    @FXML
    private Button buttonAfficher;
    @FXML
    private TableView<Evenement> listeEvenement;
    @FXML
    private TableColumn<Evenement, String> Nom_Evenement;
    @FXML
    private TableColumn<Evenement, Date> Date_Evenement;
    @FXML
    private TableColumn<Evenement, Artiste> Artiste_Evenement;
    @FXML
    private TableColumn<Evenement, Restaurant> Restaurant_Evenement;

    private final String DFBUTTONCOLOR = "#ff0000";
    private final String CONFIRMEDBUTTON ="#90EE90";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setButtonsDefault();
        fillEvenementTV();
        fillCBArtiste();
        fillCBRestaurant();
    }    


    @FXML
    private void refreshTV(ActionEvent event) {
        setButtonsDefault();
        fillEvenementTV();
    }

    @FXML
    private void addEvenement(ActionEvent event) {
        setButtonsDefault();
        addEvenementDB();
        buttonAjouter.setStyle("-fx-background-color:"+CONFIRMEDBUTTON+";");
        fillEvenementTV();
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        setButtonsDefault();
        supprimerEvenementDB();
        buttonSupprimer.setStyle("-fx-background-color:"+CONFIRMEDBUTTON+";");
        fillEvenementTV();
    }

    @FXML
    private void modifierEvenement(ActionEvent event) {
        setButtonsDefault();
        modifierEvenementDB();
        buttonModifier.setStyle("-fx-background-color:"+CONFIRMEDBUTTON+";");
        fillEvenementTV();
    }
    
    private void modifierEvenementDB() {
        selected = listeEvenement.getSelectionModel().getSelectedItem();
        Date date = Date.valueOf(DateEvenement.getValue());
        selected.setNom_Evenement(NomEvenement.getText());
        selected.setDate_Evenement(date);
        selected.setArtiste(ArtisteEvenement.getSelectionModel()
                .getSelectedItem());
        selected.setRestaurant(RestaurantEvenement.getSelectionModel()
                .getSelectedItem());
        es.modifier(selected);
    }
    
    private void supprimerEvenementDB() {
        selected = listeEvenement.getSelectionModel().getSelectedItem();
        es.supprimer(selected);
    }
    
    private void addEvenementDB() {
        
        Date date = Date.valueOf(DateEvenement.getValue());
        Evenement e = new Evenement(NomEvenement.getText(), date,
                ArtisteEvenement.getSelectionModel().getSelectedItem(),
                RestaurantEvenement.getSelectionModel().getSelectedItem());
        es.ajouter(e);
        NomEvenement.setText("");
    }
    
    private void fillEvenementTV() {
        ObservableList<Evenement> le = es.afficher();
        Nom_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement,String>("Nom_Evenement"));
        Date_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement, Date>("Date_Evenement"));
        Artiste_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement, Artiste>("Id_Artiste"));
        Restaurant_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement, Restaurant>("Id_Restaurant"));
        listeEvenement.setItems(le);
        
        
    }
    
    private void fillCBArtiste() {
        ObservableList<Artiste> la = as.afficher();
        ArtisteEvenement.setItems(la);
    }
    
    private void fillCBRestaurant() {
        ObservableList<Restaurant> lr = rs.afficher();
        RestaurantEvenement.setItems(lr);
    }
    
    private void setButtonsDefault(){
        buttonAjouter.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
        buttonSupprimer.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
        buttonModifier.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
        buttonAfficher.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
    }
    
    
                    
}
