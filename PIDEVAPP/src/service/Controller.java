package service;

import models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;


public class Controller implements Initializable {

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLast;

     @FXML
    private TextField tfMail;

    @FXML
    private TextField tfPass;
    
        @FXML
    private TextField tfPass1;
    
   
    @FXML
    private TextField tfPhone;

    @FXML
    private DatePicker tfBir;


    @FXML
    private TableView<User> tvser;

    @FXML
    private TableColumn<User, Integer> colID;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colLast;
    
       @FXML
    private TableColumn<User, String> colMail;

    @FXML
    private TableColumn<User, Date> colBir;

    @FXML
    private TableColumn<User, String> colGender;
    
        @FXML
    private TableColumn<User, String> colRole;

    @FXML
    private TableColumn<User, Integer> colPhone;


        
        @FXML
    private ComboBox tfGender;


    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;
    
               @FXML
    private ComboBox tfAcces;
    

    @FXML
    void handleButtonAction( ActionEvent event) {
        if(event.getSource()== btninsert){
            insertRecord();
        } else if (event.getSource()==btnupdate){
            update();
        }
        else if (event.getSource()==btndelete){
            delete();
        }

    }
    

    
    public Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","");
            return conn;
        }catch (Exception ex ){
            System.out.println("Error :"+ex.getMessage());
            return null;
        }
    }
    public ObservableList<User> getserList(){
        ObservableList<User> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM user";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            User user;
            while (rs.next()){
                user =new User(rs.getInt("id"), rs.getString("name"),rs.getString("password"),rs.getString("role"),rs.getString("lastname"), rs.getString("email"), rs.getString("gender"),rs.getInt("phone"),rs.getDate("birthday"),rs.getString("acces"));
                bookList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bookList;
    }
    public void showser(){
        ObservableList<User> list =getserList();
        colID.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        colLast.setCellValueFactory(new PropertyValueFactory<User,String>("lastname"));
        colMail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
        colRole.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
        colPhone.setCellValueFactory(new PropertyValueFactory<User,Integer>("phone"));
        colBir.setCellValueFactory(new PropertyValueFactory<User,Date>("birthday"));
        
        tvser.setItems(list);
    }
String Role="owner";
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showser();
                    ObservableList<String> list= FXCollections.observableArrayList("oui","non");
         tfAcces.setItems(list);
           ObservableList<String> list1= FXCollections.observableArrayList("Male","Female");
         tfGender.setItems(list1);
    }
    
    String Gender;
    
      @FXML
    void Select1(ActionEvent event) {
            Gender=tfGender.getSelectionModel().getSelectedItem().toString();
    }
    
    String acces;
            @FXML
    void Select2(ActionEvent event) {
             acces=tfAcces.getSelectionModel().getSelectedItem().toString();
    }
    
    private void insertRecord(){
        if(tfPass.getText().equals(tfPass1.getText())){
           // String acces="oui";
        String query ="INSERT INTO user VALUES("+ tfid.getText()+",'"+ tfName.getText() +"','"+tfLast.getText()+"','"+ tfMail.getText() +"','"+ tfPass.getText() +"','"+ Gender +"','"+ Role +"',"+ tfPhone.getText() +",'"+ java.sql.Date.valueOf(tfBir.getValue()) +"','"+acces +"')";
        executeQuery(query);
        showser();
        }
    }
    private void update(){
        String query = "UPDATE user SET name='"+ tfName.getText() +"',lastname='"+ tfLast.getText() +"',email='"+ tfMail.getText() +"',password='"+tfPass.getText()+"',gender='"+ Gender +"',role='"+ Role +"',phone="+ tfPhone.getText() +",birthday='"+ java.sql.Date.valueOf(tfBir.getValue())
               +"' WHERE id = "+tfid.getText()+"";
        executeQuery(query);
        showser();
    }
    private void delete(){
        String query ="DELETE FROM user WHERE id ="+tfid.getText()+"";
        executeQuery(query);
        showser();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st =conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
}
