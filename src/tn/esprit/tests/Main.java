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
import tn.esprit.entities.Chefs;
import tn.esprit.entities.Cours;
import tn.esprit.entities.Inscription;
import tn.esprit.services.Chef_Services;
import tn.esprit.services.Cours_Services;
import tn.esprit.services.Inscription_Service;
import tn.esprit.utils.DataSource;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
// crud Formations
        //Chef_Services cs = new Chef_Services()  ;
        //Chefs c = new Chefs ("Jamie Oliver","How to cook a Pasta ","United Kingdoms");
        //Chefs ct = new Chefs ("Ahmed","Tajin","Tunis");
        //cs.ajouter_Chef(ct);
        //cs.update_Chef(c);
       
        //System.out.println( cs.afficher() );  
       //System.out.println(cs.supprimer_Chef());
        //Cours_Services cour = new Cours_Services() {};
        //Cours cc = new Cours ("Pizza maker","pizza is an italien meal",4);
        //Cours ccc = new Cours ("Pastaa","Pasta is an italien meal",1);
        //cour.ajouter_Cours(ccc);
        //cour.update_Cours(ccc);
        //System.out.println( cour.afficher_Cours() );  
        //System.out.println(cour.supprimer_Cours()); 
        Inscription_Service Is= new Inscription_Service ();
        Inscription I= new Inscription (3,35);
        //Is.ajouter_Inscription(I);
    System.out.println( Is.afficher_Inscription() );  
    }
}
