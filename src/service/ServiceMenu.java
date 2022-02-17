/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Menu;
import util.DataSource;

/**
 *
 * @author USER
 */
public class ServiceMenu {
    
    Connection cnx = DataSource.getInstance().getCnx();

    private Statement ste;
    private PreparedStatement pst;
 
    public void ajouter(Menu r) {
          try {
                       
            String req = "INSERT INTO `Menu` (`nom`, `type`, `restaurant`) values (?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, r.getNom());
            pre.setString(2, r.getType());
            pre.setString(3, r.getRestaurant());
           
            pre.executeUpdate();
            System.out.println("Menu Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }
     public int deletemenu(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `menu` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
}
