/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Data.Datasource;
import entities.Publication;
import entities.Reaction;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chikaaa
 */
public class ReactionService implements IReactionService<Reaction>{
      Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void addReaction(Reaction r) {
        PreparedStatement pt;
        
          try {
              Statement st = cnx.createStatement();
              st.executeUpdate("INSERT INTO `reaction`(`Type_Reaction`, `Id_Publication`, `Id_User`) VALUES ("+r.getType_Reaction()+","+r.getP().getId_Publication()+","+r.getU().getId()+")");              
              System.err.println("Reaction Added Successfully");
    
          } catch (SQLException ex) {
              Logger.getLogger(ReactionService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerReaction(int id) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from reaction where Id_Reaction="+id);
            pt.executeUpdate();
            System.out.println("reaction Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(ReactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierReaction(Reaction r) {
       PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("UPDATE reaction SET Type_Reaction=? where Id_Reaction = ?");
            pt.setInt(1, r.getType_Reaction());          
            pt.setInt(2, r.getId_Reaction());
            pt.executeUpdate();
            System.err.println("reaction Updated Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reaction> afficherReactions() {
         List<Reaction> LR=new ArrayList<>();
         
         Statement st;
          try {
              st = cnx.createStatement();
              String req=("select * from reaction");
              ResultSet rs=st.executeQuery(req);
                while (rs.next()) {
        
                    Reaction r1 = new Reaction();
                    r1.setId_Reaction(rs.getInt(1));
                    r1.setType_Reaction(rs.getInt(2));
                    r1.setP(new Publication(rs.getInt(3)));
                    r1.setU(new User(rs.getInt(4)));
                LR.add(r1);
                         }
          } catch (SQLException ex) {
              Logger.getLogger(ReactionService.class.getName()).log(Level.SEVERE, null, ex);
          }
            
         
        return LR;
    }
    
    public void supprmierByIntegerPubication(int x)
    {
     PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from reaction where Id_Publication="+x);
            pt.executeUpdate();
            System.out.println("reaction de publication"+x+" Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(ReactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
}
