/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import tn.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entites.Reservation;

/**
 *
 * @author asus
 */
public class ServiceReservation  {
    Connection cnx = DataSource.getInstance().getConnection();

    public void Ajouter(Reservation t) {
        try{
        String requete = "INSERT INTO reservation(Heure,Date,Id_Table,Id_Restaurant,Id_User) VALUES (?,?,?,?,?)";
        
        PreparedStatement pst =new DataSource().cnx.prepareStatement(requete);
        
        pst.setInt(1, t.getHeure());
        pst.setString(2, t.getDate());
        pst.setInt(3, t.getId_Table());
        pst.setInt(4, t.getId_Restaurant());
        pst.setInt(5, t.getId_User());
        
        
        
        pst.executeUpdate();
        System.out.println("Reservation ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
       public int getNbrReserv(int idl) {
        String sql="SELECT COUNT(*) FROM reservation where Id_Restaurant='"+idl+"'";
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

    public void Supprimer(int id) {
        try{
        String requete = "DELETE FROM reservation WHERE Id_Reservation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Reservation Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
        
    public void Modifier(Reservation t) {
       try{
        String requete = "UPDATE reservation SET Heure=? , Date=? WHERE Id_Reservation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);    
        pst.setInt(1, t.getHeure());
        pst.setString(2, t.getDate());
        pst.setInt(3, t.getId_Reservation());
        pst.executeUpdate();
        System.out.println("reservation modifié !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    public List<Reservation> Afficher() {
         List<Reservation> list = new ArrayList<>();
        try{
        String requete = "SELECT * FROM reservation";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            list.add(new Reservation(rs.getInt(1),rs.getInt(2)));
        }
        System.out.println("Reservation !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }

   
    
}
