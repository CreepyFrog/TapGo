/*
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
