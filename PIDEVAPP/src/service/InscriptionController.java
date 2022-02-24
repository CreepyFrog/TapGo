/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 */
public class InscriptionController implements Initializable {

    
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
    private ComboBox tfGender;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                ObservableList<String> list1= FXCollections.observableArrayList("Male","Female");
         tfGender.setItems(list1);

    
    }    
    
    String Role="user",Gender,acces;
        
    @FXML
    void Select1(ActionEvent event) {
            Gender=tfGender.getSelectionModel().getSelectedItem().toString();
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
    
     
              
    @FXML
    void Insert1 ( ActionEvent event) {
         if(tfPass.getText().equals(tfPass1.getText())){
             String acces="oui";
        String query ="INSERT INTO user VALUES("+ tfid.getText()+",'"+ tfName.getText() +"','"+tfLast.getText()+"','"+ tfMail.getText() +"','"+ tfPass.getText() +"','"+ Gender +"','"+ Role +"',"+ tfPhone.getText() +",'"+ java.sql.Date.valueOf(tfBir.getValue())  +"','"+ acces +"')";
        executeQuery(query);
        }
    }
      
      
      
}
