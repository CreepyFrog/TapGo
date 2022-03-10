/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.itextpdf.text.DocumentException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import models.Restaurant;
import service.ServiceRestaurant;
import Util.DB.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutRestaurantController implements Initializable {

    @FXML
    private TextField ftNom;
    @FXML
    private TextField ftAdresse;
    @FXML
    private Button btn;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_adresse;
    @FXML
    private Button btnd;
    ArrayList name = new ArrayList();
    @FXML
    private TextField fid;
    @FXML
    private Button btnu;
    @FXML
    private TextField ftR;
    
      private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    
    @FXML
    private ComboBox<String> ftdomaine;
    @FXML
    private ComboBox<String> ftowner;
    ObservableList<Restaurant> list = FXCollections.observableArrayList();
    
 
    @FXML
    private TableView<Restaurant> tablerestaurant;
    @FXML
    private TableColumn<?, ?> col_domaine;
    @FXML
    private TableColumn<?, ?> col_owner;
    @FXML
    private TextField iddd;
    @FXML
    private TableColumn<?, Integer> col_owner1;
    @FXML
    private TextField ftplace;
    @FXML
    private AnchorPane recpane;
    @FXML
    private TextField ftNom1;
    @FXML
    private TextField ownerid;
    @FXML
    private Button btnu1;
    @FXML
    private Button pdfButton;
    @FXML
    private Button gotostat;

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
       
           
    }  
     private void afficher(){
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
          col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
          col_domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
          col_owner.setCellValueFactory(new PropertyValueFactory<>("owner"));
          col_owner1.setCellValueFactory(new PropertyValueFactory<>("nb"));
         
            
            
        tablerestaurant.setItems(list);
    }
    
    public void initialiserlist() throws SQLException {
        // TODO
             
            
            ftowner.setPromptText("Restaurant Owners ");
            ftdomaine.setPromptText("Chosir le domaine");
              try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM restaurant");
            while(rs.next()){
                Preferences UserId = Preferences.userRoot();
              
            list.add(new Restaurant(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));    }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
               ftdomaine.getItems().addAll("chinoix","italien","tunisien");
          
              Connection cnx = DataSource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT name FROM user where role='owner'");
            // Now add the comboBox addAll statement
           while (rs.next()){
               
            ftowner.getItems().addAll(rs.getString("name"));
           
           }
        
    }    

  
    @FXML
    private void getSelected(MouseEvent event) {
                  int index = tablerestaurant.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    ftNom.setText(col_nom.getCellData(index).toString());
    ftdomaine.setValue(col_domaine.getCellData(index).toString());
    ftAdresse.setText(col_adresse.getCellData(index).toString());
    ftowner.setValue(col_owner.getCellData(index).toString());
    ownerid.setText(col_owner.getCellData(index).toString());
     ftplace.setText(col_owner1.getCellData(index).toString());
    
    
    
    }
 public Restaurant gettemp(TableColumn.CellEditEvent edittedCell) {
        Restaurant test = (Restaurant) tablerestaurant.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    private void SupprimerStade(ActionEvent event) throws SQLException {
         TableColumn.CellEditEvent edittedcell = null;
        Restaurant x = gettemp(edittedcell);

        if (x != null) {

            int i = x.getId_restaurant();
            
            ServiceRestaurant cat = new ServiceRestaurant();

            int s = cat.deleterestaurant(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant deleted");
                alert.showAndWait();
                
                list.clear();
                initialiserlist(); 
                afficher();
                tablerestaurant.refresh();
                  iddd.setText("");
    ftNom.setText("");
    ftAdresse.setText("");
    ftowner.setValue("Owner");
    ftdomaine.setValue("");
   
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }

    }
  @FXML
 public void Edit () throws SQLException{   
        try {
            cnx = DataSource.ConnectDb();
            String value0 = iddd.getText();
            String value2 = ftNom.getText();
            
            String value6 = ftAdresse.getText();
            
            String value3 = ftowner.getValue();
            
            String value1 = ftdomaine.getValue();
            String value4 = ftplace.getText();
            
            String sql = "update restaurant set nom= '"+value2+"',adresse= '"+value6+"',domaine= '"+
                    value1+"',owner= '"+value3+"' ,nb= '"+value4+"'  where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
           
            JOptionPane.showMessageDialog(null, "Update");
           
                
                list.clear();
                initialiserlist(); 
                afficher();
                tablerestaurant.refresh(); 
       iddd.setText("");
    ftNom.setText("");
    ftAdresse.setText("");
    ftowner.setValue("Owner");
    ftdomaine.setValue("");
    ftplace.setText("");
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }list.clear();
        initialiserlist(); 
                afficher();
                tablerestaurant.refresh();
    }

    @FXML
    private void SearchStade(ActionEvent event) {
    }

    @FXML
    private void BPDF(ActionEvent event) {
    }

    @FXML
    private void savetoword(ActionEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        if (validateNumber() && validateFields()) {
         ServiceRestaurant r = new ServiceRestaurant();
        int f = Integer.parseInt(ftplace.getText());
        r.Ajouter(new Restaurant(ftNom.getText(),ftAdresse.getText(),ftdomaine.getValue(),ftowner.getValue(),f));
        list.clear();
        initialiserlist(); 
                afficher();
                tablerestaurant.refresh();
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
    
    
    
     private boolean validateNumber() {
        return false;
        //Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        //Matcher m = p.matcher(TextDate.getText());
        //if(m.find() && m.group().equals(TextDate.getText())) {
          //  return true;
        //} else {
         //   return false;
        
    }
    // Test de validation de saisie
    private boolean validateFields(){
        if( ftNom.getText().isEmpty()|| ftAdresse.getText().isEmpty() || fid.getText().isEmpty() || ftR.getText().isEmpty() || ftplace.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    
    

    @FXML
    private void rest(ActionEvent event) throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("AddMenu.fxml"));
           recpane.getChildren().setAll(pane);
    }

    @FXML
    private void Plats(ActionEvent event) throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("Plat.fxml"));
           recpane.getChildren().setAll(pane);
    }

   
    @FXML
     public void recherche(){
    ServiceRestaurant re= new ServiceRestaurant() ;
    List<Restaurant>results = new ArrayList<>();
    results = re.Afficher();
     FilteredList<Restaurant> filteredData = new FilteredList<>(list , b -> true);
		Restaurant r = new Restaurant();
		// 2. Set the filter Predicate whenever the filter changes.
		ftNom1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(restaurant -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (restaurant.getDomaine().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (restaurant.getDomaine().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (restaurant.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (restaurant.getOwner().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (restaurant.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(r.getAdresse()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Restaurant> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tablerestaurant.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tablerestaurant.setItems(sortedData);
               
        
    }
     

     @FXML
    public void send() throws SQLException, MessagingException{
    senemail();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("message envoyé");
    }
    public void senemail() throws SQLException, AddressException, MessagingException{
      //String req="Select User_Email from 'membres' where User_nom='user.getText()'"; 
     // Statement st = cnx.createStatement();
         //   ResultSet rs = st.executeQuery(req);
 //String r = emailToField.getText();
 
    
            Connection cnx = DataSource.getInstance().getCnx();
 
 String req = "SELECT email FROM users where name='"+ownerid.getText()+"'";
 Statement st = cnx.createStatement();
  ResultSet rsd = st.executeQuery(req);
            rsd.next();
            String r=rsd.getString("email");
  String to = r;
 String from = "Youssef.riahi@esprit.tn"; 
 final String username="Youssef.riahi@esprit.tn";
 final String password="Curvasud";
 
   
 String host = "smtp.google.com";
 
       Properties props = new Properties();
         props.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        props.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        props.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        props.put("mail.smtp.port", "587");
  Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
  try {
     
            
      MimeMessage m = new MimeMessage(session);
      m.setFrom(new InternetAddress(from));
      m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
      m.setSubject("votre menu est bien ajoutée");
      m.setText(r);      
      
      
      Transport.send(m);
      System.out.println("message envoyé");
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("message envoyé");
      
  } catch (MessagingException e){
  e.printStackTrace();
  }
  }

    @FXML
    private void GetPDF(ActionEvent event) {
         try {
            ServiceRestaurant pdf = new ServiceRestaurant();
            pdf.e_bookPDF();
            JOptionPane.showMessageDialog(null, "PDF DONE!");
        } catch (DocumentException ex) {
            Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez ouvrir le fichier PDF ?!");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
           // Desktop.getDesktop().open(new File("C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\document\\e_bookPDF.pdf"));

        }
    }
    
    private void setInterface(String location) throws IOException {
        recpane.getChildren().clear();
        recpane.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }

    @FXML
    private void gotostatistique(ActionEvent event) throws IOException {
        setInterface("statistics");
    }
    
}
