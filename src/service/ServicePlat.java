

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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Plat;


import util.DataSource;

/**
 *
 * @author mohamed hichri
 */
public class ServicePlat  {
    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    
   
    public void Ajouter (Plat e){
        try{
        String requete = "INSERT INTO plat(nom,element,menu) VALUES (?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
     
        pst.setString(1, e.getNom());
        pst.setString(2, e.getElement());
        pst.setString(3, e.getMenu());
        pst.executeUpdate();
        System.out.println("plats ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    public List<Plat> afficher() {
        List<Plat> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM plat";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(  new Plat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
           
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
    
     public int deletere(int id) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "delete from restaurant where id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
      public int deleteplat(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM plat WHERE id=" + id;
            i = ste.executeUpdate(sql);
           System.out.println("La supression du plat est effectuée"); 
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }

    public void Supprimer(int id) {
        try {
            String req = " DELETE FROM plat WHERE id = " + id + "";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.executeUpdate();
            System.out.println("La supression du plat est effectuée");
            
           } catch (SQLException ex) {
            System.out.println("Echec de supression");
        }
    }

    public void Modifier(Plat e) {
        try {
            String req = "UPDATE plat SET  nom=?,element=?,menu=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getNom());
        ps.setString(2, e.getElement());
        ps.setString(3, e.getMenu());;
        ps.setInt(4, e.getId());;
            ps.executeUpdate();
            System.out.println("Modification effectuée");
            
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
            
        }

    }
    

    
     

   
    
   
    
    
}
