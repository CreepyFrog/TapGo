/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entities.IService;
import tn.esprit.entities.Evenement;
import tn.esprit.utils.DataSource;
/**
 *
 * @author ASUS
 */
public class EvenementService implements IService<Evenement> {
    private final Connection conn;
    private PreparedStatement pst;
    
    ArtisteService aServ = new ArtisteService();
    RestaurantService rServ = new RestaurantService();
    
    //standard statement declaration
    //private Statement ste;

    public EvenementService() {
        conn = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Evenement e) {
        String req = "INSERT INTO `evenement`(`Nom_Evenement`, `Date_Evenement`, `Id_Artiste`, `Id_Restaurant`) VALUES (?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, e.getNom_Evenement());
            pst.setDate(2,e.getDate_Evenement());
            pst.setInt(3, e.getArtiste().getId_Artiste());
            pst.setInt(4, e.getRestaurant().getId_Restaurant());
            
            pst.executeUpdate();
            System.out.println("Evenement ajouté");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Evenement e) {
        String req = "DELETE FROM `evenement` WHERE `Id_Evenement`=?";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, e.getId_Evenement());
            
            pst.executeUpdate();
            System.out.println("Evenement supprimé");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement e) {
        String req = "UPDATE evenement SET Nom_Evenement=?, Date_Evenement=? WHERE Id_Evenement=?";
        
        try {
            pst = conn.prepareStatement(req);
            
            pst.setString(1, e.getNom_Evenement());
            pst.setDate(2, e.getDate_Evenement());
            //Foreign keys not updatable
            //pst.setInt(3, e.getArtiste().getId_Artiste());
            //pst.setInt(4, e.getRestaurant().getId_Restaurant());
            pst.setInt(3, e.getId_Evenement());
            
            pst.executeUpdate();
            System.out.println("Evenement met à jour");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> afficher() {
        ObservableList<Evenement> evenements = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `evenement`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Evenement e = new Evenement();
                
                e.setId_Evenement(rs.getInt("Id_Evenement"));
                e.setNom_Evenement(rs.getString(2));
                e.setDate_Evenement(rs.getDate(3));
                
                int id_artiste = rs.getInt("Id_Artiste");
                if (rs.wasNull()) e.setArtiste(null);
                else e.setArtiste(aServ.findById(id_artiste));
                
                int id_restaurant = rs.getInt("Id_Restaurant");
                if (rs.wasNull()) e.setRestaurant(null);
                else e.setRestaurant(rServ.findById(id_restaurant));
                
                evenements.add(e);
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return evenements;
    }
    
    @Override
    public Evenement findById(int id) {
        List<Evenement> eList = new ArrayList<>();
        
        eList = this.afficher();
        Evenement e = eList.stream().filter(evenement -> id == evenement.getId_Evenement()).findAny().orElse(null);
        
        return e;
    }
}
