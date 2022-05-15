/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javax.swing.JOptionPane;
import service.ServiceReservation;
import models.Reservation;
import models.Restaurant;
import models.User;
import models.table;
import Util.DB.DataSources;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import javafx.fxml.FXMLLoader;
/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AfficherReservationAdminController implements Initializable {
    ObservableList<Reservation> LRS;
    Reservation aem;
    ServiceReservation rs;
    Connection cnx = DataSources.getInstance().getCnx();
 ObservableList<Reservation> LR =FXCollections.observableArrayList();
    @FXML
    private AnchorPane espaceReservation;
    @FXML
    private TextField search;
   @FXML
    private TableView<Reservation> ListReservation;
    @FXML
    private TableColumn<Reservation, Integer> Idcol;
    @FXML
    private TableColumn<Reservation, Integer> Heurecol;
    @FXML
    private TableColumn<Reservation, String> Datecol;
    @FXML
    private TableColumn<Reservation, table> Tablecol;
    @FXML
    private TableColumn<Reservation, String> Restaurantcol;
    @FXML
    private TableColumn<Reservation, User> Usercol;
    @FXML
    private JFXButton AfficherButton;
    @FXML
    private JFXButton DeleteButton;
    @FXML
    private JFXButton PdfButton;
    @FXML
    private Button ModifierButton;
    @FXML
    private Button ImprimerReservation;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            ServiceReservation r = new ServiceReservation();
        LR = r.Afficher();
        getAllReservation();
      
    }    

    
    
    
    
    
    public ObservableList<Reservation> getListReservation() { 
    ObservableList<Reservation> LR =FXCollections.observableArrayList();
            
       try{
        String requete = "SELECT * FROM reservation";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
            Reservation a = new Reservation();
            a.setId_Reservation(rs.getInt(1));
            a.setHeure(rs.getInt(2));
            a.setDate(rs.getString(3));
            a.setTbl(new table(rs.getInt(4)));
            a.setRest(new Restaurant(rs.getInt(5)));
            a.setUsr(new User(rs.getInt(6)));
            LR.add(a);
           // int Heure, String Date, table tbl, Restaurant rest, User Usr
        }
        System.out.println("Reservation !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
       System.out.println(LR);
        return LR;
}
 public void getAllReservation(){
        ObservableList<Reservation> LR =getListReservation();

        Idcol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Id_Reservation"));
        Heurecol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Heure"));
        Datecol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Date"));
        Tablecol.setCellValueFactory(new PropertyValueFactory<Reservation,table>("tbl"));
        Restaurantcol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("rest"));
        Usercol.setCellValueFactory(new PropertyValueFactory<Reservation,User>("Usr"));
        ListReservation.setItems(LR);
        System.out.println("Houssemmmm");
        
        }  
    
    
    
    
    
    
     public void chercher() {
        FilteredList<Reservation> filteredData = new FilteredList<>(LR, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tff -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (tff.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                    
                    
                } 
                
                else {
                    return false;
                }
            });
        });
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ListReservation.comparatorProperty());
        ListReservation.setItems(sortedData);
    }
     
    @FXML
    private void searchLabel(ActionEvent event) {
          Idcol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Id_Reservation"));
        Heurecol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Heure"));
        Datecol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Date"));
        Tablecol.setCellValueFactory(new PropertyValueFactory<Reservation,table>("tbl"));
        Restaurantcol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("rest"));
        Usercol.setCellValueFactory(new PropertyValueFactory<Reservation,User>("Usr"));
        ServiceReservation r = new ServiceReservation();
        LR= (ObservableList<Reservation>) r.Afficher();
        ListReservation.setItems(LR);
        FilteredList<Reservation> filteredData = new FilteredList<>(LR, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (a.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter Auteur
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ListReservation.comparatorProperty());
        ListReservation.setItems(sortedData);
    }


    @FXML
    private void handlerAfficherButton(ActionEvent event) {
        getAllReservation();
    }

    
    
     public void Supprimer(int id) {
        try{
        String requete = "DELETE FROM reservation WHERE Id_Reservation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Reservation Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    
    
    
    @FXML
    private void handlerDeleteButton(ActionEvent event) throws AWTException {
        Supprimer(ListReservation.getSelectionModel().getSelectedItem().getId_Reservation());
        ListReservation.getItems().removeAll(ListReservation.getSelectionModel().getSelectedItem()); 
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
        trayIcon.displayMessage("Gestion de Reservation", "Reservation supprimé avec succée", MessageType.INFO);
        getAllReservation();
    }


    @FXML
    private void Imprimer(ActionEvent event) {
         try {
            printNode(ListReservation);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout
                = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX
                = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY
                = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        double scaleZ
                = pageLayout.getPrintableWidth() + pageLayout.getPrintableHeight() / node.getBoundsInParent().getDepth();

        Scale scale = new Scale(scaleX, scaleY, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();

            }
        }
        node.getTransforms().remove(scale);
    }

    @FXML
    private void GetPDF(ActionEvent event) throws IOException {
               try {
            ServiceReservation pdf = new ServiceReservation();
            pdf.e_bookPDF();
            JOptionPane.showMessageDialog(null, "PDF DONE!");
        } catch (DocumentException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez ouvrir le fichier PDF ?!");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Desktop.getDesktop().open(new File("C:\\Users\\TECHNOPC\\Desktop\\seif w youssef w chams 1\\seif and youssef\\PIDEVAPP\\ReservationPDF.pdf"));

        }
    }

       private void setInterface(String location) throws IOException {
        espaceReservation.getChildren().clear();
        espaceReservation.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    
    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        setInterface("HomeReservation");
    }

  
    
}
