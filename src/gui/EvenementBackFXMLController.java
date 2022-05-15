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
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Restaurant;
import service.ServiceRestaurant;
import models.Artiste;
import models.Evenement;
import service.ArtisteService;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EvenementBackFXMLController implements Initializable {
    
    EvenementService es = new EvenementService();
    ArtisteService as = new ArtisteService();
    ServiceRestaurant rs = new ServiceRestaurant();
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
    @FXML
    private AnchorPane AnchorPaneEvenement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillEvenementTV();
        fillCBArtiste();
        fillCBRestaurant();
    }    


    @FXML
    private void refreshTV(ActionEvent event) {
        fillEvenementTV();
    }

    @FXML
    private void addEvenement(ActionEvent event) {
        addEvenementDB();
        fillEvenementTV();
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        supprimerEvenementDB();
        fillEvenementTV();
    }

    @FXML
    private void modifierEvenement(ActionEvent event) {
        modifierEvenementDB();
        fillEvenementTV();
    }
    
    private void modifierEvenementDB() {
        if ((NomEvenement.getText().equals("")) || (NomEvenement.getText() == null)){
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

            trayIcon.displayMessage("Gestion Evenement", "L'évènement doit avoir un nom", MessageType.INFO);
        }
        else{
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
        
    }
    
    private void supprimerEvenementDB() {
        selected = listeEvenement.getSelectionModel().getSelectedItem();
        es.supprimer(selected);
    }
    
    private void addEvenementDB() {
        if ((NomEvenement.getText().equals("")) || (NomEvenement.getText() == null)){
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

            trayIcon.displayMessage("Gestion Evenement", "L'évènement doit avoir un nom", MessageType.INFO);
        }
        else{
            Date date = Date.valueOf(DateEvenement.getValue());
            Evenement e = new Evenement(NomEvenement.getText(), date,
                    ArtisteEvenement.getSelectionModel().getSelectedItem(),
                    RestaurantEvenement.getSelectionModel().getSelectedItem());
            es.ajouter(e);
            NomEvenement.setText("");
        }
        
    }
    
    private void fillEvenementTV() {
        ObservableList<Evenement> le = es.afficher();
        Nom_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement,String>("Nom_Evenement"));
        Date_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement, Date>("Date_Evenement"));
        Artiste_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement, Artiste>("artiste"));
        Restaurant_Evenement.setCellValueFactory(new PropertyValueFactory
                <Evenement, Restaurant>("restaurant"));
        listeEvenement.setItems(le);
        
        
    }
    
    private void fillCBArtiste() {
        ObservableList<Artiste> la = as.afficher();
        ArtisteEvenement.setItems(la);
    }
    
    private void fillCBRestaurant() {
        ObservableList<Restaurant> lr = rs.Afficher();
        RestaurantEvenement.setItems(lr);
    }

    private void setInterface(String location) throws IOException {
        AnchorPaneEvenement.getChildren().clear();
        AnchorPaneEvenement.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    @FXML
    private void chargerArtiste(ActionEvent event) {
        try{
            setInterface("ArtisteBackFXML");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void fillFields(MouseEvent event) {
        Evenement selected = listeEvenement.getSelectionModel().getSelectedItem();
        NomEvenement.setText(selected.getNom_Evenement());
        DateEvenement.setValue(selected.getDate_Evenement().toLocalDate());
        ArtisteEvenement.setValue(selected.getArtiste());
        RestaurantEvenement.setValue(selected.getRestaurant());
        
    }
    
    
                    
}
