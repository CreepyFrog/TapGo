

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static javafx.application.Application.launch;
import models.Plat;
import models.Restaurant;

import service.ServicePlat;
import service.ServiceRestaurant;

import sun.security.pkcs11.P11Util;
/**
 *
 * @author Youssef
 */
public class PIDEV  {
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException {
        
       ServicePlat PS = new ServicePlat();
       Plat p = new Plat("pizza","thon , mozarilla","ddddd");
      PS.Ajouter(p);
      PS.deleteplat(6); // hedhi thot feha id ta plat li theb tfadkhou
      p.setElement("ddddd");
      p.setNom("dddd");
      p.setMenu("ddd");
      p.setId(3);// id taa plat l theb tfaskhou
      PS.Modifier(p);
      
      //  System.out.println(PS.afficher());
        
                     ServiceRestaurant RS = new ServiceRestaurant();
                      Restaurant R = new Restaurant("seif","gabes","itali","owner",6);
                      RS.Ajouter(R);
                          //   System.out.println(RS.Afficher());
                           RS.deleterestaurant(13); // hedhi thot feha id ta plat li theb tfadkhou
   /* R.setNom("ddddd");
      R.setAdresse("ddffffdd");
      R.setDomaine("ddd");
      R.setOwner("hhhhh");
       R.setNb(4);
              R.setId_restaurant(16);

      RS.Modifier(R);
                          */

                     

    }
    
}