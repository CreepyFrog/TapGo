/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.IService;
import tn.esprit.entities.Inscription;
import tn.esprit.utils.DataSource;

/**
 *
 * @author MSI
 */
public abstract class Inscription_Service implements IService {

  private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    
    public Inscription_Service() {
        conn = DataSource.getInstance().getCnx();
    }
        
    
    public void ajouter_Inscription(Inscription I) {
  String req = "INSERT INTO `Inscription` (`Id_inscription`,`Id_Cour`,`Id_User`) VALUES (?,?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, I.getId_Inscription());
            pst.setInt(2, I.getId_Cour());
            pst.setInt(3, I.getId_User());
            pst.executeUpdate();
            System.out.println("Inscription ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List afficher_Inscription() {
        List<Inscription> Inscription = new ArrayList<>();
        
        String req = "SELECT * from `Inscription`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Inscription I = new Inscription();
                I.setId_Inscription(rs.getInt("Id_Inscription") );
                I.setId_Cour(rs.getInt(2));
                I.setId_User(rs.getInt(3));
              
                Inscription.add(I);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return Inscription;
    }
    
    public List<Inscription> Update_Inscription(Inscription I) {
        
         List<Inscription> Inscription = new ArrayList<>();
        String req;
        req = "UPDATE Chefs SET Id_Cour=? , Id_User=? WHERE Id_Inscription=1";
         try {
            
            pst = conn.prepareStatement(req);
            pst.setInt(1, I.getId_Cour());
            pst.setInt(2, I.getId_User());
          
            pst.executeUpdate();
            System.out.println("Inscription Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Inscription;
    }
    
    
    public List<Inscription> supprimer_Inscription() {
        
        List<Inscription> Inscription = new ArrayList<>();
        String sql="DELETE FROM Inscription WHERE Id_Inscription=1";
        try {
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Inscription Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Inscription;
        
         
    }

   

}
