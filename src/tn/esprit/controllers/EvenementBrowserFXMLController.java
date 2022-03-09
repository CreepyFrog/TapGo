/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tn.esprit.entities.Artiste;
import tn.esprit.entities.Evenement;
import tn.esprit.services.ArtisteService;
import tn.esprit.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementBrowserFXMLController implements Initializable {

    EvenementService es = new EvenementService();
    ArtisteService as = new ArtisteService();
    
    @FXML
    private TextField NomEvenement;
    @FXML
    private ComboBox<Artiste> NomArtiste;
    @FXML
    private ComboBox<String> TypeDeMusique;
    @FXML
    private Button ButtonChercher;
    @FXML
    private ToggleGroup DateOptionGroup;
    @FXML
    private RadioButton DateSpecifique;
    @FXML
    private RadioButton DatePeriode;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private Label feedbackLabel;
    @FXML
    private VBox vboxE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            chargerEvenements(es.afficher());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        fillCBArtiste();
        fillCBType();
    }
    
//--------------------------------------------------------------------------
//  ActionEvent handlers
//-------------------------------------------------------------------------- 
    @FXML
    private void chercher(ActionEvent event) {
        ObservableList<Evenement> evenements = FXCollections.observableArrayList();
        
        Date dd = Date.valueOf(DateDebut.getValue());
        Date df = Date.valueOf(DateFin.getValue());
        evenements = es.recherche(dd, df, NomArtiste.getValue().getNom_Artiste(),
                TypeDeMusique.getValue(), NomEvenement.getText());
        try{
            chargerEvenements(evenements);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        NomEvenement.setText("");
        NomArtiste.setValue(null);
        TypeDeMusique.setValue(null);
        DateDebut.setValue(null);
        DateFin.setValue(null);
        feedbackLabel.setText("");
    }
       
    @FXML
    private void setDateSearch(ActionEvent event) {
        DateDebut.setPromptText("Date");
        DateFin.setDisable(true);
    }

    @FXML
    private void setPeriodSearch(ActionEvent event) {
        DateDebut.setPromptText("Date debut de recherche");
        DateFin.setPromptText("Date fin de recherche");
        DateFin.setDisable(false);
    }
    
    @FXML
    private void setActiverArtiste(ActionEvent event) {
        TypeDeMusique.setValue(null);
    }

    @FXML
    private void setActiverGenre(ActionEvent event) {
        NomArtiste.setValue(null);
    }
    
    @FXML
    private void getStats(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        for (String type : as.typesMusique()){
            pieChartData.add(new PieChart.Data(type,es.numberByType(type)));
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Genre de Musique dans les evenements");
        vboxE.getChildren().clear();
        vboxE.getChildren().add(chart);
    }
    
//--------------------------------------------------------------------------
//  Fonctions Utilises par le controleur
//--------------------------------------------------------------------------
    
    //afficher un liste d'evenements dans la page
    private void chargerEvenements(ObservableList<Evenement> evenements) 
            throws IOException{
        vboxE.getChildren().clear();
        for (Evenement e :evenements){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(
                        "/tn/esprit/view/EvenementFrontFXML.fxml"));
                AnchorPane APane = loader.load();
                
                EvenementFrontFXMLController eController = 
                        loader.getController();
                eController.loadData(e);
                
                vboxE.getChildren().add(APane);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    private void fillCBArtiste() {
        ObservableList<Artiste> la = as.afficher();
        NomArtiste.setItems(la);
    }
    
    private void fillCBType() {
        ObservableList<String> lt = as.typesMusique();
        TypeDeMusique.getItems().addAll(lt);
    }

    
    
}
