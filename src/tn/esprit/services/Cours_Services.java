/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entities.Chefs;
import tn.esprit.entities.Cours;
import tn.esprit.entities.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.utils.DataSource;

/**
 *
 * @author MSI
 */
public class Cours_Services implements IService<Cours>{

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    
    public Cours_Services() {
        conn = DataSource.getInstance().getCnx();
    }
    
     public List<Cours> ajouter_Cours(Cours c){
        String req = "INSERT INTO `Cours` (`ID_Cour `,`Nom_Cour`,`Libelle_Cour`,`ID_Chef `) VALUES (?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getID_Cour());
            pst.setString(2, c.getNom_cour());
            pst.setString(3, c.getLibelle_Cour());
            pst.setInt(4, c.getID_Chef());
            pst.executeUpdate();
            System.out.println("cour ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
         
    @Override
     public List<Cours> afficher_Cours(){
         
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
                cc.setId_Chef(rs.getInt(4));
                Cours.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return Cours;
    }
    
        public List<Cours> update_Chef(Cours cc ){
        List<Chefs> Chefs = new ArrayList<>();
        String req;
        req = "UPDATE Cours SET Nom_Cour = ?, Libelle_Chef = ?, Id_Chef = ? WHERE Id_Cour = 1";
         try {
            
            pst = conn.prepareStatement(req);
            pst.setString(1, cc.getNom_Cour());
            pst.setString(2, cc.getLibelle_Cour());
            pst.setString(3, cc.getId_Chef());
            pst.executeUpdate();
            System.out.println("Cour Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        List<Cours> Cours = null;
        
        return Cours;
        
        
      }
    
    public List<Cours> supprimer_Cour(){
        List<Cours> Cours = new ArrayList<>();
        String sql="DELETE FROM Cours WHERE Id_Cour=1";
        try {
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Chef Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Cours;
    }     

    @Override
    public void ajouter(Cours entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Cours entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Cours entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cours> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
    }
     
     
    
    
    
    
    
    
    
    
