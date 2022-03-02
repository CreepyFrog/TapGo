/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Main extends Application {
    Parent parent;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/ArtisteBackFXML.fxml"));
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Artistes");
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        launch(args);
        // TODO code application logic here
        
        /*
        ArtisteService as = new ArtisteService();
        Artiste a = new Artiste("Leonard Cohen","pop");
        //a.setId_Artiste(1);
        //a.setType_De_Musique("classic");
        
        a = as.findById(10);
        a.setType_De_Musique("rock");
        
        as.modifier(a);
        
        //as.supprimer(a);
        
        //as.ajouter(a);
        
        //System.out.println(as.findById(11));
        System.out.println(as.afficher());
        
        
        
        EvenementService es = new EvenementService();
        Evenement e = new Evenement("Sahria","2022-11-11");
        
        //e.setId_Evenement(1);
        e = es.findById(2);
        e.setNom_Evenement("Sahria2");
        
        es.modifier(e);
        
        //es.supprimer(e);
        
        //es.ajouter(e);
        
        System.out.println(es.findById(2));
        System.out.println(es.afficher());
        */
    }
}
