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
<<<<<<< HEAD
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entites.Reservation;
import tn.esprit.entites.Restaurant;
=======
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
import tn.esprit.entites.table;

/**
 *
 * @author asus
 */
public class ServiceTable  {
    Connection cnx = DataSource.getInstance().getConnection();
<<<<<<< HEAD
    ObservableList<table> list = FXCollections.observableArrayList();
=======
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765

    public void Ajouter(table t) {
        try{
        Statement st=cnx.createStatement();  
<<<<<<< HEAD
        String req="INSERT INTO table_restaurant(Type_Table,Id_Restaurant) VALUES ("+t.getType_Table()+","+t.getRest1().getId_restaurant()+")";;
=======
        String req="INSERT INTO table_restaurant(Type_Table,Id_Restaurant) VALUES ("+t.getType_Table()+","+t.getId_Restaurant()+")";;
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
        st.executeUpdate(req);
        System.out.println("table ajouté !");
        }catch(SQLException ex){    
        System.err.println(ex.getMessage());
        }
    }
    
    
       public int getNbrReserv(int id2) {
        String sql="SELECT COUNT(*) FROM table where Id_Restaurant='"+id2+"'";
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
        String requete = "DELETE FROM table_restaurant WHERE Id_Table=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("table Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
        
    public void Modifier(table t) {
       try{
        String requete = "UPDATE table_restaurant SET Type_Table=? WHERE Id_Table=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getType_Table());
        pst.setInt(2,t.getId_Table());
        pst.executeUpdate();
        System.out.println("table modifié !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

<<<<<<< HEAD
    public ObservableList<table> Afficher() {
         
=======
    public List<table> Afficher() {
         List<table> list = new ArrayList<>();
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
        try{
        String requete = "SELECT * FROM table_restaurant";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
<<<<<<< HEAD
            table b = new table();
            b.setId_Table(rs.getInt(1));
            b.setType_Table(rs.getInt(2));
            b.setRest1(new Restaurant(rs.getInt(3)));
            list.add(b);
=======
            list.add(new table(rs.getInt(1),rs.getInt(2)));
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
        }
        System.out.println("table affiché!");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }

   
    
}
