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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
    private ScrollPane PaneList;
    @FXML
    private Label feedbackLabel;
    @FXML
    private ToggleGroup ArtisteGenreGroup;
    @FXML
    private RadioButton ActiverArtiste;
    @FXML
    private RadioButton ActiverGenre;

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
        if (NomEvenement.getText() != null) {
            if (NomArtiste.getValue() != null) {
                if(DateSpecifique.isSelected()){
                    if(DateDebut.getValue() != null){//nomEvenement && nomArtiste && Date
                        Date date = Date.valueOf(DateDebut.getValue());
                        evenements = es.recherche(date, true,NomArtiste.getValue().getNom_Artiste());
                        evenements = es.find(NomEvenement.getText(), evenements);
                    }
                    else { //nomEvenement && nomArtiste
                        evenements = es.recherche(true,NomArtiste.getValue().getNom_Artiste());
                        evenements = es.find(NomEvenement.getText(), evenements);
                    }
                }
                else {
                    if ((DateDebut.getValue() != null)&&(DateFin.getValue() != null)){ //nomEvenement && nomArtiste && periode
                        Date dd = Date.valueOf(DateDebut.getValue());
                        Date df = Date.valueOf(DateFin.getValue());
                        evenements = es.recherche(dd, df, true, NomArtiste.getValue().getNom_Artiste());
                        evenements = es.find(NomEvenement.getText(), evenements);
                    }
                    else{ //nomEvenement && nomArtiste && periode?
                        if ((DateDebut.getValue() == null)&&(DateFin.getValue() == null)) { //nomEvenement && nomArtiste
                            evenements = es.recherche(true,NomArtiste.getValue().getNom_Artiste());
                            evenements = es.find(NomEvenement.getText(), evenements);
                        }
                        else { //periodeError
                            feedbackLabel.setText("Vous devez specifier les dates limites de votre recherche");
                            feedbackLabel.setVisible(true);
                            evenements = null;
                        }
                    }
                }
            }
            else { //nomEvenement !nomArtiste
                if(TypeDeMusique.getValue() != null) { //nomEvenement Type
                    if(DateSpecifique.isSelected()){
                        if(DateDebut.getValue() != null){//nomEvenement && Type && Date
                            Date date = Date.valueOf(DateDebut.getValue());
                            evenements = es.recherche(date, false, TypeDeMusique.getValue());
                            evenements = es.find(NomEvenement.getText(), evenements);
                        }
                        else { //nomEvenement && Type
                            evenements = es.recherche(false, TypeDeMusique.getValue());
                            evenements = es.find(NomEvenement.getText(), evenements);
                        }
                    }
                    else {
                        if ((DateDebut.getValue() != null)&&(DateFin.getValue() != null)){ //nomEvenement && Type && periode
                            Date dd = Date.valueOf(DateDebut.getValue());
                            Date df = Date.valueOf(DateFin.getValue());
                            evenements = es.recherche(dd, df,false, TypeDeMusique.getValue());
                            evenements = es.find(NomEvenement.getText(), evenements);
                        }
                        else{ //nomEvenement && Type && periode?
                            if ((DateDebut.getValue() == null)&&(DateFin.getValue() == null)) { //nomEvenement && Type
                                evenements = es.recherche(false, TypeDeMusique.getValue());
                                evenements = es.find(NomEvenement.getText(), evenements);
                            }
                            else { //periodeError
                                feedbackLabel.setText("Vous devez specifier les dates limites de votre recherche");
                                feedbackLabel.setVisible(true);
                                evenements = null;
                            }
                        }
                    }
                }
                else { //nomEvenement !Type
                    if(DateSpecifique.isSelected()){
                        if(DateDebut.getValue() != null){//nomEvenement && Date
                            Date date = Date.valueOf(DateDebut.getValue());
                            evenements = es.recherche(date);
                            evenements = es.find(NomEvenement.getText(), evenements);
                        }
                        else { //nomEvenement
                            evenements = es.find(NomEvenement.getText(), es.afficher());
                        }
                    }
                    else {
                        if ((DateDebut.getValue() != null)&&(DateFin.getValue() != null)){ //nomEvenement && periode
                            Date dd = Date.valueOf(DateDebut.getValue());
                            Date df = Date.valueOf(DateFin.getValue());
                            evenements = es.recherche(dd, df);
                            evenements = es.find(NomEvenement.getText(), evenements);
                        }
                        else{ //nomEvenement && nomArtiste && periode?
                            if ((DateDebut.getValue() == null)&&(DateFin.getValue() == null)) { //nomEvenement
                                evenements = es.find(NomEvenement.getText(), es.afficher());
                            }
                            else { //periodeError
                                feedbackLabel.setText("Vous devez specifier les dates limites de votre recherche");
                                feedbackLabel.setVisible(true);
                                evenements = null;
                            }
                        }
                    }
                }
            }
        }
        else { // !nomEvenement
            if (NomArtiste.getValue() != null) { // nomArtiste
                if(DateSpecifique.isSelected()){
                    if(DateDebut.getValue() != null){//nomArtiste && Date
                        Date date = Date.valueOf(DateDebut.getValue());
                        evenements = es.recherche(date, true, NomArtiste.getValue().getNom_Artiste());
                    }
                    else { //nomArtiste
                        evenements = es.recherche(true, NomArtiste.getValue().getNom_Artiste());
                    }
                }
                else {
                    if ((DateDebut.getValue() != null)&&(DateFin.getValue() != null)){ //nomArtiste && periode
                        Date dd = Date.valueOf(DateDebut.getValue());
                        Date df = Date.valueOf(DateFin.getValue());
                        evenements = es.recherche(dd, df, true, NomArtiste.getValue().getNom_Artiste());
                    }
                    else{ //nomArtiste && periode?
                        if ((DateDebut.getValue() == null)&&(DateFin.getValue() == null)) { //nomArtiste
                            evenements = es.recherche(true, NomArtiste.getValue().getNom_Artiste());
                        }
                        else { //periodeError
                            feedbackLabel.setText("Vous devez specifier les dates limites de votre recherche");
                            feedbackLabel.setVisible(true);
                            evenements = null;
                        }
                    }
                }
            }
            else { // !nomArtiste
                if (TypeDeMusique.getValue() != null){ // Type
                    if(DateSpecifique.isSelected()){
                        if(DateDebut.getValue() != null){// Type && Date
                            Date date = Date.valueOf(DateDebut.getValue());
                            evenements = es.recherche(date, false, TypeDeMusique.getValue());
                        }
                        else { // Type
                            evenements = es.recherche(false, TypeDeMusique.getValue());
                        }
                    }
                    else {
                        if ((DateDebut.getValue() != null)&&(DateFin.getValue() != null)){ //Type && periode
                            Date dd = Date.valueOf(DateDebut.getValue());
                            Date df = Date.valueOf(DateFin.getValue());
                            evenements = es.recherche(dd, df, false, TypeDeMusique.getValue());
                        }
                        else{ //Type && periode?
                            if ((DateDebut.getValue() == null)&&(DateFin.getValue() == null)) { // Type
                                evenements = es.recherche(false, TypeDeMusique.getValue());
                            }
                            else { //periodeError
                                feedbackLabel.setText("Vous devez specifier les dates limites de votre recherche");
                                feedbackLabel.setVisible(true);
                                evenements = null;
                            }
                        }
                    }
                }
                else {
                    if(DateSpecifique.isSelected()){
                        if(DateDebut.getValue() != null){// Date
                            Date date = Date.valueOf(DateDebut.getValue());
                            evenements = es.recherche(date);
                        }
                        else { // All
                            evenements = es.afficher();
                        }
                    }
                    else {
                        if ((DateDebut.getValue() != null)&&(DateFin.getValue() != null)){ //periode
                            Date dd = Date.valueOf(DateDebut.getValue());
                            Date df = Date.valueOf(DateFin.getValue());
                            evenements = es.recherche(dd, df);
                        }
                        else{ //nomEvenement && nomArtiste && periode?
                            if ((DateDebut.getValue() == null)&&(DateFin.getValue() == null)) { // All
                                evenements = es.afficher();
                            }
                            else { //periodeError
                                feedbackLabel.setText("Vous devez specifier les dates limites de votre recherche");
                                feedbackLabel.setVisible(true);
                                evenements = null;
                            }
                        }
                    }
                }
            }
        }
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
    
//--------------------------------------------------------------------------
//  Fonctions Utilises par le controleur
//--------------------------------------------------------------------------
    
    //afficher un liste d'evenements dans la page
    private void chargerEvenements(ObservableList<Evenement> evenements) 
            throws IOException{
        VBox vboxE = new VBox();
        vboxE.setSpacing(10);
        for (Evenement e :evenements){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(
                        "/tn/esprit/view/EvenementFrontFXML.fxml"));
                AnchorPane APane = null;
                APane = loader.load();
                
                EvenementFrontFXMLController eController = 
                        loader.getController();
                eController.loadData(e);
                
                vboxE.getChildren().add(APane);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        PaneList.setContent(vboxE);
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
