/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.IService;
import util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Restaurant;

/**
 *
 * @author asus
 */
public class ServiceRestaurant {
    Connection cnx = DataSource.getInstance().getCnx();

     private Statement ste;
    private PreparedStatement pst;
        public void Ajouter(Restaurant t) {
        try{
        String requete = "INSERT INTO restaurant  (nom,adresse,domaine,owner,nb) VALUES (?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getAdresse());
        pst.setString (3, t.getDomaine());
        pst.setString(4, t.getOwner());
        pst.setInt(5, t.getNb());
        pst.executeUpdate();
        System.out.println("restaurant ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    
           public int getNbrRest(int id) {
        String sql="SELECT nb FROM restaurant where id='"+id+"'";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
    public void Supprimer(Restaurant t) {
        try{
        String requete = "DELETE FROM restaurant WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId_restaurant());
        pst.executeUpdate();
        System.out.println("restaurant Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    public int deleterestaurant(int id_restaurant) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "delete from restaurant where id=" + id_restaurant;
            i = ste.executeUpdate(sql);
            System.out.println("La supression du resto est effectuée"); 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    
    public void Modifier(Restaurant t) {
       try{
        String requete = "UPDATE restaurant SET nom=?,adresse= ?,domaine=?,owner=? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getAdresse());
        pst.setString(3, t.getDomaine());
        pst.setString(4, t.getOwner());
        pst.executeUpdate();
        System.out.println("restaurant ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    
    public List<Restaurant> Afficher() {
         List<Restaurant> list = new ArrayList<>();
        try{
        String requete = "SELECT * FROM restaurant";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            list.add(new Restaurant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        System.out.println("restaurant !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }
    
}
