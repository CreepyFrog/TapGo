/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Util.DB.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.User;
import service.Mailing;

/**
 * FXML Controller class
 *
 * @author TECHNOPC
 */
public class RecoverController implements Initializable {

    @FXML
    private TextField TxtEmail;
    @FXML
    private Button Submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SubmitOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        Mailing ms= new Mailing();
         Connection cnx;
        PreparedStatement ste ;
        cnx = DBConnection.getInstance().getConnection();
     String sql = "Select * from user where email  =? ";
        ResultSet rs;
        ste=cnx.prepareStatement(sql);
        User u = new User ();
    ste=cnx.prepareStatement(sql);
        ste.setString(1, TxtEmail.getText());
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
   
                     
        }
         
        
        String p =  u.getPassword().replace("nisqpfdbn$hreb6b8e6","");
        ms.sendEmail(TxtEmail.getText(), "Password", p);
        
    }
    
}
