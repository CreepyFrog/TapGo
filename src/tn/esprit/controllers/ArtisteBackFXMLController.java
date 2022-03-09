/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.Artiste;
import tn.esprit.entities.Evenement;
import tn.esprit.services.ArtisteService;
import tn.esprit.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ArtisteBackFXMLController implements Initializable {
    
    ArtisteService as = new ArtisteService();
    EvenementService es = new EvenementService();
    
    @FXML
    private TextField NomArtiste;
    @FXML
    private TextField TypeDeMusique;
    @FXML
    private TableView<Artiste> listeArtiste;
    @FXML
    private TableColumn<Artiste, String> Nom_Artiste;
    @FXML
    private TableColumn<Artiste, String> Type_De_Musique;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonAfficher;
    @FXML
    private Button buttonAjouter;
    @FXML
    private Button buttonModifier;
    
    private final String DFBUTTONCOLOR = "#a550de";
    private final String CONFIRMEDBUTTON ="#90EE90";
    @FXML
    private AnchorPane AnchorPaneArtiste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setButtonsDefault();
        fillArtisteTV();
    }
    
    @FXML
    private void addArtiste(ActionEvent event) {
        setButtonsDefault();
        addArtisteDB();
        buttonAjouter.setStyle("-fx-background-color:"+CONFIRMEDBUTTON+";");
        fillArtisteTV();
    }
    
    @FXML
    private void refreshTV(ActionEvent event) {
        setButtonsDefault();
        fillArtisteTV();
    }
    
    @FXML
    private void supprimerArtiste(ActionEvent event) {
        setButtonsDefault();
        supprimerArtisteDB();
        buttonSupprimer.setStyle("-fx-background-color:"+CONFIRMEDBUTTON+";");
        fillArtisteTV();
        
    }
    
    @FXML
    private void modifierArtiste(ActionEvent event) {
        setButtonsDefault();
        modifierArtisteDB();
        buttonModifier.setStyle("-fx-background-color:"+CONFIRMEDBUTTON+";");
        fillArtisteTV();
    }
    
    public void fillArtisteTV() {
        ObservableList<Artiste> la = as.afficher();
        Nom_Artiste.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Nom_Artiste"));
        Type_De_Musique.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Type_De_Musique"));
        listeArtiste.setItems(la);
    }
    
    public void addArtisteDB() {
        Artiste a = new Artiste(NomArtiste.getText(),TypeDeMusique.getText());
        as.ajouter(a);
        NomArtiste.setText("");
        TypeDeMusique.setText("");
    }
    
    private void supprimerArtisteDB(){
        Artiste selected = listeArtiste.getSelectionModel().getSelectedItem();
        ObservableList<Evenement> evenements = es.recherche(selected.getId_Artiste());
        List<Integer> idEv = new ArrayList<Integer>();
        for (Evenement e : evenements){
            idEv.add(e.getId_Evenement());
        }
        if (evenements != null) {
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
            try {
                tray.add(trayIcon);
            } catch(AWTException ex) {
                System.out.println(ex.getMessage());
            }

            trayIcon.displayMessage("Gestion Artiste", "Artiste selectionné existe dans les evenements : "+evenements, TrayIcon.MessageType.INFO);
        }
        as.supprimer(selected);
    }
    
    private void modifierArtisteDB(){
        Artiste selected = listeArtiste.getSelectionModel().getSelectedItem();
        selected.setNom_Artiste(NomArtiste.getText());
        selected.setType_De_Musique(TypeDeMusique.getText());
        as.modifier(selected);
        NomArtiste.setText("");
        TypeDeMusique.setText("");
    }
    
    private void setButtonsDefault(){
        buttonAjouter.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
        buttonSupprimer.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
        buttonModifier.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
        buttonAfficher.setStyle("-fx-background-color:"+DFBUTTONCOLOR+";");
    }
    
    private void setInterface(String location) throws IOException {
        AnchorPaneArtiste.getChildren().clear();
        AnchorPaneArtiste.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/tn/esprit/view/" + location + ".fxml")));
    }

    @FXML
    private void chargerEvenement(ActionEvent event) {
        try{
            setInterface("EvenementBackFXML");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
