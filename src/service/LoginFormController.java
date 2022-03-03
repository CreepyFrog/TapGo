package service;


import models.User;
import Util.DB.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane root;
        @FXML
    private Label t01;
    
  
    
    

    public int verifier_auth(String email ,String pass) throws SQLException, IOException, ClassNotFoundException {
pass=pass.substring(0, 3)+"nisqpfdbn$hreb6b8e6"+pass.substring(3);
        Connection cnx;
        PreparedStatement ste ;
        cnx = DBConnection.getInstance().getConnection();


        String sql = "Select * from user where email  =? and password  =?";
        ResultSet rs;
        ste=cnx.prepareStatement(sql);
        User u = new User ();

        int x=0;
        ste.setString(1, email);
        ste.setString(2, pass);
        rs = ste.executeQuery();
        if (rs.next()){


            u.setId(rs.getInt(1));
            u.setEmail(rs.getString(4));
            u.setPhone(rs.getInt(8));
            u.setGender(rs.getString(6));
            u.setLastname(rs.getString(3));
            u.setName(rs.getString(2));
            u.setPassword(rs.getString(5));
            
                     

            
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

    public void SignupOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/Inscription.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
    }
    
    
    public void LoginOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {


        String uNom = txtUserName.getText();
        String upass = txtPassword.getText();

        
        if (verifier_auth(uNom, upass) == 1) {
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
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/designOwner.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }

        if (verifier_auth(uNom, upass) == 3) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/design.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }
        

    }
    public void btnCloaseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
    
 /**********************************************************************/
        @FXML
    private AnchorPane rec;
    @FXML
    private Button btn;
    @FXML
    private Button btnr1;
    
    

    
    @FXML
    private AnchorPane recpane;

     @FXML
    private void logout(ActionEvent event) throws IOException {
                       rec.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/LoginForm.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
    }
}

