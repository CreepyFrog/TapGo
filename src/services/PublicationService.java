/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Data.Datasource;
import entities.Publication;
import entities.User;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Chikaaa
 */
public class PublicationService implements IPublicationService<Publication>{
    Connection cnx = Datasource.getInstance().getCnx();
    @Override
    public void addPublication(Publication p) {
        
        try{
        Statement st = cnx.createStatement();
        String req = "INSERT INTO `publication`(`Libelle_Publication`, `Nb_Réaction`, `Id_User`) VALUES ('"+p.getLibelle_Publication()+"',"+p.getNb_Reaction()+","+p.getU().getId()+")";
        st.executeUpdate(req);
        System.out.println("Publication Added Successfully");
        
        }   catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void supprimerPublication(int id ) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from publication where Id_Publication="+id);
            pt.executeUpdate();
            System.out.println("publication Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierPublication(Publication p) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("UPDATE publication SET Libelle_Publication=?,Nb_Réaction=? where `Id_Publication` = ?");
            pt.setString(1, p.getLibelle_Publication());
            pt.setInt(2,p.getNb_Reaction());           
            pt.setInt(3,p.getId_Publication());
            pt.executeUpdate();
            System.err.println("publication Updated Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }

    @Override
    public ObservableList<Publication> afficherPublication() {
        ObservableList<Publication> LP =FXCollections.observableArrayList();
            
        try {
            Statement st = cnx.createStatement();
            String req=("select * from publication");
            ResultSet rs=st.executeQuery(req);
            while (rs.next()) {
                Publication p1=new Publication();
//                rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4);
                p1.setId_Publication(rs.getInt(1));
                p1.setLibelle_Publication(rs.getString(2));
                p1.setNb_Reaction(rs.getInt(3));
                p1.setU(new User(rs.getInt(4)));
                LP.add(p1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return LP;
    }
    
//    public Publication getMaxPubByNbrReact(){
//        try {
//            Statement st = cnx.createStatement();
//            String req=("SELECT * FROM `publication` ORDER BY `Nb_Réaction` DESC");
//            ResultSet rs=st.executeQuery(req);
//            
//                Publication p1=new Publication();
////                rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4);
//                p1.setId_Publication(rs.getInt(1));
//                p1.setLibelle_Publication(rs.getString(2));
//                p1.setNb_Reaction(rs.getInt(3));
//                p1.setU(new User(rs.getInt(4)));//Select userByID to show the NAme to put in the label of the Top Reacted pub
//                System.out.println(p1);
//            return p1;
//        } catch (SQLException ex) {
//            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    
//    }
    

    

  
    
}
