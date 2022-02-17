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

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        ArtisteService as = new ArtisteService();
        Artiste a = new Artiste("Freddy Mercury","Rock");
        as.ajouter(a);
        System.out.println( as.afficher() );
    }
}
