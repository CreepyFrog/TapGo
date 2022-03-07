/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import com.sun.xml.internal.bind.v2.TODO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.entities.Chefs;
import tn.esprit.entities.Cours;
import tn.esprit.services.Chef_Services;
import tn.esprit.services.Cours_Services;

/**
 *
 * @author macbook
 */

public class Main extends Application {
    
    Parent parent;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
        parent = FXMLLoader.load(getClass().getResource("/tn/esprit/GUI/Formations.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Gestion de formations TAP&GO");
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//         //TODO code application logic here
//// //crud Formations
////Chef_Services cs = new Chef_Services()  ;
////        Chefs c = new Chefs ("moi","Couscous ","TUNISIE");
//////        //Chefs ct = new Chefs ("Ahmed","Tajin","Tunis");
////        cs.ajouter_Chef(c);
//        //cs.update_Chef(c);
////       
//     // System.out.println( cs.afficher() );  
//      //System.out.println(cs.supprimer_Chef());
//        Cours_Services cs = new Cours_Services();
//        Cours c = new Cours ("Burger","Les ingrédients : Pure Meat",new Chefs(5));
//        cs.ajouter_Cours(c);
////        System.out.println(cs.afficher_Cours()); 
//    }
//}
//        //cs.update_Cours(c);
//         
//        //System.out.println(cs.supprimer_Cours());



  
