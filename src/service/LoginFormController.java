package service;


import models.User;
import Util.DB.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.prefs.Preferences;
//import java.util.logging.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.UserHolder;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane root;
    @FXML
    private CheckBox remember;
    private UserHolder ur=UserHolder.getInstance();
  
    Preferences preference;
    boolean rememberPreference;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btInscription;
    @FXML
    private Button recover;
//    public LoginFormController()
//    {
//      
//       initCompnents();
//       this.setLocationRelativeTo(null);
//        rememberMe();
//    }
//    
    private  void rememberMe()
    { 
       
      preference=Preferences.userNodeForPackage(this.getClass());
      rememberPreference = preference.getBoolean("rememberMe",Boolean.valueOf(""));
      if(rememberPreference) {
         
          txtUserName.setText(preference.get("email", ""));
          txtPassword.setText(preference.get("password", ""));
         // remember.setSelected(rememberPreference);
          
      }
    }
    

    public int verifier_auth(String email ,String pass) throws SQLException, IOException, ClassNotFoundException {
        
pass=pass.substring(0, 3)+"nisqpfdbn$hreb6b8e6"+pass.substring(3);
        Connection cnx;
        PreparedStatement ste ;
        cnx = DBConnection.getInstance().getConnection();


        String sql = "Select * from user where email  =? and passwordjava  =?";
        ResultSet rs;
        ste=cnx.prepareStatement(sql);
        User u = new User ();

        int x=0;
        ste.setString(1, email);
        ste.setString(2, pass);
        rs = ste.executeQuery();
        
       
        if (rs.next()){
            if (remember.isSelected() && !(rememberPreference))
            {
                preference.put("email", txtUserName.getText());
                preference.put("password", txtPassword.getText());
                preference.putBoolean("rememberMe", true );
                
            }else if ( !(remember.isSelected()) && rememberPreference) {
                preference.put("email", "");
                preference.put("password", "");
                preference.putBoolean("rememberMe", false );
            }

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
    

            
                

            if(rs.getString(10).equals("oui")){
            if(rs.getString(7).equals("admin")){ x = 1; }
            if(rs.getString(7).equals("owner")){ x = 2; }
            if(rs.getString(7).equals("user")){ x = 3; }
            

            }
             
            else if (rs.getString(10).equals("non")){
               
                   
                
                return x=404;}
           
        }          
         
        
        
        return x;

    }

    @FXML
    public void SignupOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/Inscription.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
    }
    
       public int verifier_authh(String email ) throws SQLException, IOException, ClassNotFoundException {
            
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
    
            if(rs.getString(4).equals(email)){ return x = 1; }
                     
        }
            
        return x;
    }
   
    
    
    @FXML
    public void LoginOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

//rememberMe();
        String uNom = txtUserName.getText();
        String upass = txtPassword.getText();
 int v = verifier_authh(uNom);
 
        User u=new User();
        u.setName(uNom);
        ur.setUser(u);
        
rememberMe();

             if (uNom.isEmpty() && upass.isEmpty()) {
        JOptionPane.showMessageDialog(null, "you have to give your email and your  password");}
        
             else if ( upass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "you have to give password");
                       }
              else if (uNom.isEmpty()){
        JOptionPane.showMessageDialog(null, "you have to give your email ");
                       }
             
             if (!(uNom.isEmpty()) && !(upass.isEmpty())) {
                 if (v == 0) {
                             JOptionPane.showMessageDialog(null, "you are not a user you must register or check your email");

                 }
                   }

        
         
////if( )
//{
//    
//}
//rememberMe();

        if (verifier_auth(uNom, upass) == 1) {
                                     JOptionPane.showMessageDialog(null, "You have successfully logged in");

            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/Dashboard.fxml")));

            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }
        
         if (verifier_auth(uNom, upass) == 404) {
            txtUserName.getScene().getWindow().hide();
             Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/alertCompte.fxml")));

             Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }
        if (verifier_auth(uNom, upass) == 2) {
                                                 JOptionPane.showMessageDialog(null, "You have successfully logged in");

            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/designOwner.fxml")));

            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }

        if (verifier_auth(uNom, upass) == 3) {
                                                 JOptionPane.showMessageDialog(null, "You have successfully logged in");

            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/design.fxml")));

            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            
            
            

        }
        
//rememberMe();

    }
    public void btnCloaseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void RecoverOnAction(ActionEvent event)  throws IOException, SQLException, ClassNotFoundException  {
     Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/Recover.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
    }
    
    
 /**********************************************************************/
//    private AnchorPane rec;
//
//    private void logout(ActionEvent event) throws IOException {
//                       rec.getScene().getWindow().hide();
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/LoginForm.fxml")));
//            Stage mainStage = new Stage();
//            Scene scene = new Scene(root);
//            mainStage.setScene(scene);
//            mainStage.show();
           // rememberMe();
//    }
}

