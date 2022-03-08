/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Data.Datasource;
import java.sql.Connection;
import java.util.List;
import entities.Commentaire;
import entities.Publication;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chikaaa
 */
public class CommentaireService implements ICommentaireService<Commentaire>{
    
     Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void addCommentaire(Commentaire c) {
         try {
             Statement st = cnx.createStatement();
             String req = "INSERT INTO `commentaire`(`libelle_commentaire`, `Id_User`, `Id_Publication`) VALUES ('"+c.getLibelle_commentaire()+"',"+c.getU().getId()+","+c.getP().getId_Publication()+")";
        st.executeUpdate(req);
        System.out.println("Commmentaire Added Successfully");
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void supprimerCommentaire(int id) {
        PreparedStatement ps;
         try {
             ps = cnx.prepareStatement("delete from commentaire where Id_Commentaire="+id);
             ps.executeUpdate();
            System.out.println("commentaire Deleted Successfully");
             
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifierCommentaire(Commentaire c) {
        PreparedStatement pt;
         try {
             pt = cnx.prepareStatement("UPDATE commentaire SET libelle_commentaire=? where `Id_Commentaire` = ?");
             pt.setString(1,c.getLibelle_commentaire());
             pt.setInt(2,c.getId_Commentaire());
             pt.executeUpdate();
             System.err.println("publication Updated Successfully");
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public List afficherCommentaire() {
        List<Commentaire> LC=new ArrayList<>();
         try {
             Statement st = cnx.createStatement();
             String req="select * from commentaire";
             ResultSet rs=st.executeQuery(req);
             while (rs.next()) {
                 Commentaire c1= new Commentaire();
                 c1.setId_Commentaire(rs.getInt(1));
                 c1.setLibelle_commentaire(rs.getString(2));
                 c1.setU(new User(rs.getInt(3)));
                 c1.setP(new Publication(rs.getInt(4)));  
            LC.add(c1);
                }    
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return LC;
    }

    
    
}
