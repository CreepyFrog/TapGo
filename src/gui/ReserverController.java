/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import service.ServiceReservation;

import util.DataSource;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tn.esprit.entites.Reservation;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TablePosition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.awt.Window;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import service.ServiceRestaurant;
import static util.DataSource.ConnectDb;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReserverController implements Initializable {

    @FXML
    private Button btnr;
    @FXML
    private ComboBox<String> com1;
    @FXML
    private ComboBox<String> com2;
    ObservableList<Reservation> optionS ;
    ObservableList<Reservation> list = FXCollections.observableArrayList();
    private ObservableList<Reservation> listM;
    private ObservableList<Reservation> listR = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    ObservableList<Reservation> list1 = FXCollections.observableArrayList();
    ObservableList<Reservation> listS = FXCollections.observableArrayList();
    @FXML
    private TextField ftid;
    private TableColumn<Reservation, Integer> tid;
    private TableColumn<Reservation, String> tnom;
    private TableColumn<Reservation, String> tadresse;
    private TableColumn<Reservation, Integer> tprixph;
    private TableColumn<Reservation, String> tcontact;
    private TableColumn<Reservation, String> timage;
    private TableView<Reservation> table_Reservation;
    @FXML
    private TextField mapadress;
    private TextField ftR;
    ArrayList name = new ArrayList();
       private Stage primaryStage;
    @FXML
    private DatePicker dater;
    
    @FXML
    private Label rand;
    @FXML
    private TableView<Reservation> table_reservation;
    @FXML
    private TableColumn<?, ?> col_r;
    @FXML
    private TableColumn<?, ?> col_h;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private Button btns;
    @FXML
    private AnchorPane recpane;
    @FXML
    private Button btnr1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            
            
            com1.setPromptText("Les restaurants ");
            com2.setPromptText("Chosir l'heure");
            ResultSet rs1 = null;
            
          
            try {
                Connection cnx = DataSource.getInstance().getCnx();
                rs1 = cnx.createStatement().executeQuery("SELECT nom FROM restaurant");
            } catch (SQLException ex) {
                Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs1.next()) {  // loop
                    
                    // Now add the comboBox addAll statement
                    com1.getItems().addAll(rs1.getString("nom"));
                    
                }} catch (SQLException ex) {
                    Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            com2.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
            
       
            
            
               
            
            
           Connection cnx = DataSource.getInstance().getCnx();
        try {
            ResultSet rs8 = cnx.createStatement().executeQuery("SELECT nom FROM restaurant");
            while(rs8.next()){
                name.add(rs8.getString("nom"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
        afficher();
            
        
            
    }    
   
   
    @FXML
    private void Reserver(ActionEvent event) throws SQLException {
        ResultSet rs6 = null;
      
        Connection cnx = DataSource.getInstance().getCnx();
        int j =Integer.parseInt(com2.getValue());
        
        String n = com1.getValue();
String date = dater.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        rs6 = cnx.createStatement().executeQuery("SELECT id FROM restaurant where nom='"+n+"'");  
     
        rs6.next();
  int id1 = rs6.getInt("id");
  
  
  
 
            
     Connection cnx1 = DataSource.getInstance().getCnx();
        
        

        
        ServiceReservation sr = new ServiceReservation();
          
        ServiceRestaurant sr1 = new ServiceRestaurant();
            int k =sr.getNbrReserv(id1)+1;
        
        
        if(
            k <= sr1.getNbrRest
            (id1)
                )
        {
        sr.Ajouter(new Reservation(id1,j,n,date));
        }
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("réeservation ajoutée");
                
                alert.showAndWait();
                }
        
// SMS ss = new SMS();
 //ss.SendSMS("bechir12", "123456789bB", "felicitation", "21626472874", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
        
    

    @FXML
    private void SelectReservation(ActionEvent event) throws SQLException {

    }

    @FXML
    private void SelectHeure(ActionEvent event) {
    }

    
   
  

    private void getadressclicked(MouseEvent event) {
         int index = table_Reservation.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
           mapadress.setText(tadresse.getCellData(index).toString());
    }   
    private void afficher(){
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
          col_h.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
          col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
         
                    
            
        table_reservation.setItems(list1);
    }

   

    private void SearchReservation(ActionEvent event) throws SQLException {
          Connection conn = ConnectDb();
      
        String value9 = ftR.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from Reservation where nom Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();
            
            while (rs5.next()){   
                
                listR.add(new Reservation( rs5.getInt(1),rs5.getString(2), rs5.getString(3), rs5.getInt(4), rs5.getString(5), rs5.getString(6)));               
            }
        tid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id_Reservation"));
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tprixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
        tcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        timage.setCellValueFactory(new PropertyValueFactory<>("image"));

         table_Reservation.setItems(listR);
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void SupprimerReservation(ActionEvent event) {
    }

    @FXML
    private void Edit(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException  {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginInterface.fxml"));
           recpane.getChildren().setAll(pane);
    }

  

   
}
    

