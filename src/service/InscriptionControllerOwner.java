/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.*;
import Util.DB.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.User;
import models.User;



/**
 * FXML Controller class
 *
 */
public class InscriptionControllerOwner implements Initializable {

    
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
    
    String Role="owner",Gender,acces;
        
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet31","root","");
            return conn;
        }catch (Exception ex ){
            System.out.println("Error :"+ex.getMessage());
            return null;
        }
    }
    
     
      
      
      
      /************************************************************************/
      
        public int verifier_auth(String email ) throws SQLException, IOException, ClassNotFoundException {
            
        Connection cnx;
        PreparedStatement ste ;
        cnx = DBConnection.getInstance().getConnection();


        String sql = "Select * from user where email  =? ";
        ResultSet rs;
        ste=cnx.prepareStatement(sql);
        User u = new User ();

        int x=0;
        ste.setString(1, email);
        rs = ste.executeQuery();
        if (rs.next()){

            u.setId(rs.getInt(1));
            u.setEmail(rs.getString(4));
            u.setPhone(rs.getInt(8));
            u.setGender(rs.getString(6));
            u.setLastname(rs.getString(3));
            u.setName(rs.getString(2));
            u.setPassword(rs.getString(12));
            u.setRole(rs.getString(7));
            u.setBirthday(rs.getDate(9));
            u.setAcces(rs.getString(10));
    
            if(rs.getString(4).equals(email)){ x = 1; }
                     
        }
            
        return x;
    }
   
    @FXML
    void Insert1 ( ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        
        
           if(tfLast.getText().equals("")||tfMail.getText().equals("")||tfName.getText().equals("")||tfPhone.getText().equals("")){
                

                           Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/alertRemplir.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
                 
             }
                     else{
        
        String Mail=tfMail.getText();
        
         int S=verifier_auth( Mail );
        
         if(tfPass.getText().equals(tfPass1.getText())&& tfPass.getText().length()>6 ){
             String acces="oui";
             if( S==0 ){
           String alexResult  =tfPass.getText().substring(0, 3)+"nisqpfdbn$hreb6b8e6"+tfPass.getText().substring(3);
   
        //String query ="INSERT INTO user VALUES("+ tfid.getText()+",'"+ tfName.getText() +"','"+tfLast.getText()+"','"+ tfMail.getText() +"','"+alexResult +"','"+ Gender +"','"+ Role +"',"+ tfPhone.getText() +",'"+ java.sql.Date.valueOf(tfBir.getValue())  +"','"+ acces +"')";
            String query ="INSERT INTO user(name,lastname,email,passwordjava,Gender,Role,phone,birthday,acces) VALUES("+"'"+ tfName.getText() +"','"+tfLast.getText()+"','"+ tfMail.getText() +"','"+alexResult +"','"+ Gender +"','"+ Role +"',"+ tfPhone.getText() +",'"+ java.sql.Date.valueOf(tfBir.getValue())  +"','"+ acces +"')";
           tfName.setText("") ;
                   tfLast.setText("");
                   tfMail.setText("") ;
                           tfPhone.setText("") ;
                           tfPass.setText("");
                                   tfPass1.setText("");
                                   tfBir.setValue(null);
                                   //tfGender.setValue("Gender");

        executeQuery(query);

       
          //tfPass.getScene().getWindow().hide();
          }
             
        }
        
    }
    }
      
      
}
