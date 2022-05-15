/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Artiste;
import models.Evenement;
import service.ArtisteService;
import service.EvenementService;

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
    
    @FXML
    private AnchorPane AnchorPaneArtiste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillArtisteTV();
    }
    
    @FXML
    private void addArtiste(ActionEvent event) {
        addArtisteDB();
        fillArtisteTV();
    }
    
    @FXML
    private void refreshTV(ActionEvent event) {
        fillArtisteTV();
    }
    
    @FXML
    private void supprimerArtiste(ActionEvent event) {
        supprimerArtisteDB();
        fillArtisteTV();
        
    }
    
    @FXML
    private void modifierArtiste(ActionEvent event) {
        modifierArtisteDB();
        fillArtisteTV();
    }
    
    public void fillArtisteTV() {
        ObservableList<Artiste> la = as.afficher();
        Nom_Artiste.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Nom_Artiste"));
        Type_De_Musique.setCellValueFactory(new PropertyValueFactory<Artiste, String>("Type_De_Musique"));
        listeArtiste.setItems(la);
    }
    
    public void addArtisteDB() {
        if ((NomArtiste.getText().equals("")) || (TypeDeMusique.getText().equals(""))){
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");
            try {
                tray.add(trayIcon);
            } catch(AWTException ex) {
                System.out.println(ex.getMessage());
            }

            trayIcon.displayMessage("Gestion Artiste",
                    "L'artiste doit avoir un nom et un genre de musique",
                    TrayIcon.MessageType.INFO);
        }
        else {
            Artiste a = new Artiste(NomArtiste.getText(),TypeDeMusique.getText());
            as.ajouter(a);
            NomArtiste.setText("");
            TypeDeMusique.setText("");
        }
        
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
        if ((NomArtiste.getText().equals("")) || (TypeDeMusique.getText().equals(""))){
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");
            try {
                tray.add(trayIcon);
            } catch(AWTException ex) {
                System.out.println(ex.getMessage());
            }

            trayIcon.displayMessage("Gestion Artiste", "L'artiste doit avoir un nom et un genre de musique", TrayIcon.MessageType.INFO);
        }
        else {
            Artiste selected = listeArtiste.getSelectionModel().getSelectedItem();
            selected.setNom_Artiste(NomArtiste.getText());
            selected.setType_De_Musique(TypeDeMusique.getText());
            as.modifier(selected);
            NomArtiste.setText("");
            TypeDeMusique.setText("");
        }
    }
    
    
    
    private void setInterface(String location) throws IOException {
        AnchorPaneArtiste.getChildren().clear();
        AnchorPaneArtiste.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }

    @FXML
    private void chargerEvenement(ActionEvent event) {
        try{
            setInterface("EvenementBackFXML");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void fillFields(MouseEvent event) {
        Artiste selected = listeArtiste.getSelectionModel().getSelectedItem();
        NomArtiste.setText(selected.getNom_Artiste());
        TypeDeMusique.setText(selected.getType_De_Musique());
    }
    
}
