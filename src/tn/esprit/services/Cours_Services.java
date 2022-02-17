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
import java.sql.SQLException;
import java.sql.Statement;
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
    
     public void ajouter_Cours(Cours c){
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
