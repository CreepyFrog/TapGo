/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.entites.Reservation;
import tn.esprit.utils.DataSource;
import java.sql.Date;
import static java.sql.JDBCType.INTEGER;

import service.ServiceReservation;
import service.ServiceTable;
import tn.esprit.entites.Restaurant;
import tn.esprit.entites.User;
import tn.esprit.entites.table;


public class PIDEV {
     
         public static void main(String[] args){
             ServiceReservation sr= new ServiceReservation();
            

             //Reservation r = new Reservation(124,15,"22",new table(2),new Restaurant(17),new User(1));
             //sr.Ajouter(r);
             //c bon reservation
              //Reservation r2=new Reservation(115,55,"21/11/10");
        //sr.Modifier(r2); 
        //sr.Supprimer(118);
             
             System.out.println(sr.Afficher());
               
             ServiceTable tr = new ServiceTable();
             System.out.println(tr.Afficher());
             
    }
    
    }
