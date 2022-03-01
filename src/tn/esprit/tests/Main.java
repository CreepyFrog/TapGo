/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import tn.esprit.utils.DataSource;
import tn.esprit.services.*;
import tn.esprit.entities.*;
import java.util.*;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        ArtisteService as = new ArtisteService();
        Artiste a = new Artiste("Leonard Cohen","pop");
        //a.setId_Artiste(1);
        //a.setType_De_Musique("classic");
        
        //as.modifier(a);
        
        //as.supprimer(a);
        
        //as.ajouter(a);
        
        System.out.println(as.findArtisteById(11));
        
        
        /*
        EvenementService es = new EvenementService();
        Evenement e = new Evenement("Sahria","2022-11-11");
        //e.setId_Evenement(1);
        //e.setNom_Evenement("Sahria2");
        
        //es.modifier(e);
        
        //es.supprimer(e);
        
        //es.ajouter(e);
        
        //System.out.println(es.afficher());
        
        */
    }
}
