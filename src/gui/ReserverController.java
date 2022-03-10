/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD
package gui;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import tn.esprit.entites.Reservation;
import tn.esprit.utils.DataSource;
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
=======
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
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
<<<<<<< HEAD
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javax.swing.JOptionPane;
import service.ServiceReservation;
import tn.esprit.entites.Restaurant;
import tn.esprit.entites.User;
import tn.esprit.entites.table;
import tn.esprit.entites.Maptet;
import com.google.api.services.calendar.Calendar;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ReserverController implements Initializable {
    ObservableList<Reservation> LRS;
    Reservation aem;
    ServiceReservation rs;
    Connection cnx = DataSource.getInstance().getConnection();
 ObservableList<Reservation> LR =FXCollections.observableArrayList();
    @FXML
    private AnchorPane espaceReservation;
    private ComboBox<?> ComboTable;
    private ComboBox<?> ComboRestaurant;
    private ComboBox<?> ComboUser;
    private JFXDatePicker DatePickReservation;
    private JFXTimePicker TimePickReservation;
    private TableView<Reservation> ListReservation;
    private TableColumn<Reservation, Integer> Idcol;
    private TableColumn<Reservation, Integer> Heurecol;
    private TableColumn<Reservation, String> Datecol;
    private TableColumn<Reservation, table> Tablecol;
    private TableColumn<Reservation, String> Restaurantcol;
    private TableColumn<Reservation, User> Usercol;
    @FXML
    private JFXButton AjouterButton;
    private TextField search;
    @FXML
    private JFXTextField TextDate;
    @FXML
    private JFXTextField TextHeure;
    @FXML
    private JFXTextField TextTable;
    @FXML
    private JFXTextField TextRestaurant;
    @FXML
    private JFXTextField TextUser;
    @FXML
    private Button ModifierButton;
    @FXML
    private Button back;
    @FXML
    private JFXButton ButtonMap;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            ServiceReservation r = new ServiceReservation();
        LR = r.Afficher();
       
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
 /*public void getAllReservation(){
        ObservableList<Reservation> LR =getListReservation();

        Idcol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Id_Reservation"));
        Heurecol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Heure"));
        Datecol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Date"));
        Tablecol.setCellValueFactory(new PropertyValueFactory<Reservation,table>("tbl"));
        Restaurantcol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("rest"));
        Usercol.setCellValueFactory(new PropertyValueFactory<Reservation,User>("Usr"));
        ListReservation.setItems(LR);
        System.out.println("Houssemmmm");
        
        }  */
 
 

    private void handlerAfficherButton(ActionEvent event) {
       // getAllReservation();
        //System.out.println(rs.afficherPublication());
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
        
   public void ModifierReservationToBase(Reservation t) {
       try{
        String requete = "UPDATE reservation SET Heure=? , Date=? WHERE Id_Reservation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);    
        pst.setInt(1, t.getHeure());
        pst.setString(2, t.getDate());
        pst.setInt(3, t.getId_Reservation());
        pst.executeUpdate();
        System.out.println("reservation modifié !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
         String value1 = TextDate.getText();
        
        
        
        ServiceReservation sr= new ServiceReservation();
        Reservation t = new Reservation();
        t.setId_Reservation(0);
        t.setDate(value1);
        t.setHeure(Integer.parseInt(TextHeure.getText()));
        
                LR.clear();
                ListReservation.refresh();
               
      TextDate.setText("");
      TextHeure.setText("");
  
       // addReservationToBase(t);
        sr.Modifier(t);
    }
   

    private void handlerDeleteButton(ActionEvent event) {
                
        Supprimer(ListReservation.getSelectionModel().getSelectedItem().getId_Reservation());
        ListReservation.getItems().removeAll(ListReservation.getSelectionModel().getSelectedItem()); 
        
     //   getAllReservation();
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
            Desktop.getDesktop().open(new File("C:\\xampp\\htdocs\\tapgo\\document\\ReservationPDF.pdf"));

        }
    }

    @FXML
    private void HandlerAjouterButton(ActionEvent event) {
          if (validateNumber() && validateFields()) {
        
        String value1 = TextDate.getText();
        String value3 = TextTable.getText();
        String value4 = TextRestaurant.getText();
        String value5 = TextUser.getText();
        ServiceReservation sr= new ServiceReservation();
        Reservation t = new Reservation();
        t.setId_Reservation(0);
        t.setDate(value1);
        t.setHeure(Integer.parseInt(TextHeure.getText()));
        t.setTbl(new table(3));
        t.setRest(new Restaurant(17));
        t.setUsr(new User(35));
       // addReservationToBase(t);
        sr.Ajouter(t);
        
        } else if(validateNumber()==false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur Validation!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un nombre de personne valide");
            alert.showAndWait();
        } else if(validateFields()==false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur Validation!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
    }
   public void addReservationToBase(Reservation t) {
        try{
        String requete = "INSERT INTO reservation(Heure,Date,Id_Table,Id_Restaurant,Id_User) VALUES ("+t.getHeure()+","+t.getDate()+","+t.getTbl().getId_Table()+","+t.getRest().getId_restaurant()+","+t.getUsr().getId()+")";
        
        PreparedStatement pst =new DataSource().cnx.prepareStatement(requete);
        
       /* pst.setInt(1, t.getHeure());
        pst.setString(2, t.getDate());
        pst.setInt(3, t.getId_Table());
        pst.setInt(4, t.getId_Restaurant());
        pst.setInt(5, t.getId_User());
        */
        
        
        pst.executeUpdate();
        System.out.println("Reservation ajouté !");
        }catch (SQLException ex) {
              Logger.getLogger(service.ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 private boolean validateNumber() {
        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(TextDate.getText());
        if(m.find() && m.group().equals(TextDate.getText())) {
            return true;
        } else {
            return false;
        }
    }
    // Test de validation de saisie
    private boolean validateFields(){
        if( TextRestaurant.getText().isEmpty()|| TextUser.getText().isEmpty() || TextHeure.getText().isEmpty() || TextDate.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    
    private void setInterface(String location) throws IOException {
        espaceReservation.getChildren().clear();
        espaceReservation.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        setInterface("HomeUser");
    }

    
    String localisation;
    
       
   Stage stage1;
    FXMLLoader loader;
      public void showMap(Stage stage) {
       
            // create web engine and view
         WebEngine webEngine = new WebEngine(
                getClass().getResource("/tn/esprit/utils/googleMap.html").toString());
         WebView webView = new WebView();
         webView.getEngine().load("file:///C:\\xampp\\htdocs\\tapgo\\src\\tn\\esprit\\utils\\googleMap.html");
        // create map type buttons
               
             stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(webView));
            stage.show();
        
        final ToggleGroup mapTypeGroup = new ToggleGroup();
        final ToggleButton road = new ToggleButton("Road");
        road.setSelected(true);
        road.setToggleGroup(mapTypeGroup);
        final ToggleButton satellite = new ToggleButton("Retour");
        
        satellite.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                stage1 = new Stage(StageStyle.UTILITY);
                   loader= new FXMLLoader(getClass().getResource("Evenement.fxml"));
                    loader.setController(new MapsController());
                 try {
                     stage1.setScene(new Scene(loader.load()));
                 } catch (IOException ex) {
                     Logger.getLogger(MapsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            stage1.show();
                 
             }
         });
    
        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>(){
             @Override
             public void handle(WindowEvent event) {
                 System.out.println("close");
                 String adresse =webView.getEngine().executeScript("fullName").toString();
                 
                 
                 
                   System.out.println("localisation = "+adresse);
                 
                 
             }
            
        });
       
        
     
        

   }
    
    
    
    
    
    @FXML
    private void HandlerMap(ActionEvent event) {
        
                   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     
        showMap(stage);
    }

   

}
=======
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
    

>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
