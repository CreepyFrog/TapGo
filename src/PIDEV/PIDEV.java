/*
<<<<<<< HEAD
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
=======
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PIDEV;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import tn.esprit.entites.Reservation;
import service.ServiceReservation;
import tn.esprit.entites.table;
import service.ServiceTable;
import tn.esprit.utils.DataSource;

/**
 *
 * @author remo
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        ServiceTable ts = new ServiceTable();
       table a = new table(6,69);
       ts.Modifier(a);
        //ServiceReservation rs = new ServiceReservation();
        //rs.Supprimer(112);
        
       
       
         
         System.out.print(ts.Afficher());
        
    }
    
}
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
