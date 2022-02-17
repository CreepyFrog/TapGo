/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.IService;
import tn.esprit.entities.Artiste;
import tn.esprit.utils.DataSource;

/**
 *
 * @author ASUS
 */
public class ArtisteService implements IService<Artiste>{
    
    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;
    
    public ArtisteService() {
        conn = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Artiste a) {
        String req = "INSERT INTO `artiste`(`Nom_Artiste`, `Type_De_Musique`) VALUES (?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, a.getNom_Artiste());
            pst.setString(2,a.getType_De_Musique());
            pst.executeUpdate();
            System.out.println("Artiste ajouté");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Artiste entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Artiste entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Artiste> afficher() {
        List<Artiste> artistes = new ArrayList<>();
        
        String req = "SELECT * FROM `artiste`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Artiste a = new Artiste();
                a.setId_Artiste(rs.getInt("Id_Artiste"));
                a.setNom_Artiste(rs.getString(2));
                a.setType_De_Musique(rs.getString(3));
                artistes.add(a);
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return artistes;
    }
    
}
