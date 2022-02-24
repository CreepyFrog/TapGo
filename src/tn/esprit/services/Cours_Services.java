/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entities.Cours;
import tn.esprit.entities.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Chefs;
import tn.esprit.utils.DataSource;

/**
 *
 * @author MSI
 */
public class Cours_Services {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    
    public Cours_Services() {
        conn = DataSource.getInstance().getCnx();
    }
    
    public void ajouter_Cours(Cours c) {
        
         String req = "INSERT INTO `Cours` (`Id_Cour`,`Nom_Cour`,`Libelle_Cour`,`ID_Chef`) VALUES (?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getId_Cour());
            pst.setString(2, c.getNom_cour());
            pst.setString(3, c.getLibelle_Cour());
            pst.setInt(4, c.getID_Chef());
            pst.executeUpdate();
            System.out.println("cour ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    public List<Cours> supprimer_Cours() {
       List<Cours> Cours = new ArrayList<>();
        String sql="DELETE FROM Cours WHERE Id_Cour=1";
        try {
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Cour Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Cours;
    }

    public List<Cours> update_Cours(Cours cc) {
         List<Cours> Cours = new ArrayList<>();
        String req;
        req = "UPDATE Cours SET Nom_Cour = ?, Libelle_Cour = ?, ID_Chef = ? WHERE Id_Cour = 1";
         try {
            
            pst = conn.prepareStatement(req);
            pst.setString(1, cc.getNom_cour());
            pst.setString(2, cc.getLibelle_Cour());
            pst.setInt(3, cc.getID_Chef());
            pst.executeUpdate();
            System.out.println("Cour Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Cours;
        
    }

    public List<Cours> afficher_Cours() {
         
       List<Cours> Cours = new ArrayList<>();
        
        String reqi = "SELECT * from `Cours`";
        
        try {
            pst = conn.prepareStatement(reqi);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Cours cc = new Cours();
                cc.setID_Chef( rs.getInt("Id_Cour") );
                cc.setNom_cour(rs.getString(2));
                cc.setLibelle_Cour(rs.getString(3));
                cc.setID_Chef(rs.getInt(4));
                Cours.add(cc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return Cours;
        
    }

    
        
        
    }
     
     
    
    
    
    
    
    
    
    
