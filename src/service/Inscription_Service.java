/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cours;
import models.Inscription;
import models.User;
import Util.DB.DataSources;

/**
 *
 * @author MSI
 */
public class Inscription_Service  {

  private Connection conn;
    private PreparedStatement pst;
 //   private Statement ste;
    
    public Inscription_Service() {
        conn = DataSources.getInstance().getCnx();
    }
        
    
    public void ajouter_Inscription(Inscription I) {

      
  String req = "INSERT INTO `Inscription` (`Id_inscription`,`Id_Cour`,`id`) VALUES (?,?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, I.getId_Inscription());
            pst.setInt(2, I.getCours().getId_Cour());
            pst.setInt(3, I.getU().getId());
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
                I.setCours(new Cours (rs.getInt("Id_Cour")));
                I.setU(new User(rs.getInt("id")));
              
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
            pst.setInt(1, I.getCours().getId_Cour());
            pst.setInt(2, I.getU().getId());
            pst.executeUpdate();
            System.out.println("Inscription Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Inscription;
    }
    
    
    public List<Inscription> supprimer_Inscription() {
        
        List<Inscription> Inscription = new ArrayList<>();
        String sql="DELETE FROM Inscription WHERE Id_Inscription=?";
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
