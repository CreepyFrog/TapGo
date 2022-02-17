/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static javafx.application.Application.launch;
import models.User;
import service.UserServices;
/**
 *
 * @author TECHNOPC
 */
public class PIDEVAPP {
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException {
        
        UserServices us = new UserServices();
        User seif = new User("seif","amara","seifamara@esprit.tn","azerty","male","user",53834156  );
        us.AddUser(seif);
        User setty = new User("ahmed","setty","setty@esprit.tn","ffffff","male","owner",56464564 );
        us.AddUser(setty);
      us.Deleteuser(60);
        
        /*
         u.setEmail("setty@esprit.tn");
         u.setGender("male");
         u.setLastname("aaaa");
         u.setName("sssss");
         u.setrole("owner");
         u.setPhone(44444444);
         u.setId(47);
         us.UpdateUser(u);
         */
         System.out.println(us.afficher());
            //    System.out.println(us.SortListUsers());

               //   System.out.print(us.SearchUser("seif", "amara"));
    }
    
}