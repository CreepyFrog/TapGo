/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    
    //standard statement declaration
    //private Statement ste;
    
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
    public void supprimer(Artiste a) {
        String req = "DELETE FROM `artiste` WHERE `Id_Artiste`=?";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, a.getId_Artiste());
            
            pst.executeUpdate();
            System.out.println("Artiste supprimé");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void modifier(Artiste a) {
        String req = "UPDATE Artiste SET Nom_Artiste=?, Type_De_Musique=? WHERE Id_Artiste=?";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, a.getNom_Artiste());
            pst.setString(2, a.getType_De_Musique());
            pst.setInt(3, a.getId_Artiste());
            
            pst.executeUpdate();
            System.out.println("Artiste met à jour");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
    
    @Override
    public Artiste findById(int id) {
        List<Artiste> aList = new ArrayList<>();
        
        aList = this.afficher();
        Artiste a = aList.stream().filter(artiste -> id == artiste.getId_Artiste()).findAny().orElse(null);
        
        return a;
    }
    
}
