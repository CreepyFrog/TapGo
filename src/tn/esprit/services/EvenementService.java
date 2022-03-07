/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.*;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entities.IService;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.Restaurant;
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
    
    public EvenementService() {
        conn = DataSource.getInstance().getConnection();
    }

//--------------------------------------------------------------------------
//  CRUD
//--------------------------------------------------------------------------
    @Override
    public void ajouter(Evenement e) {
        String req = "INSERT INTO `evenement`(`Nom_Evenement`, `Date_Evenement`,"
                + " `Id_Artiste`, `Id_Restaurant`) VALUES (?,?,?,?)";
        
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
        String req = "UPDATE evenement SET Nom_Evenement=?, Date_Evenement=? "
                + "WHERE Id_Evenement=?";
        
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
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `evenement`";
        
        try {
            
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            evenements = fillEList(rs);
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return evenements;
    }
    
//--------------------------------------------------------------------------
//  Find a specific Event
//--------------------------------------------------------------------------
    @Override
    public Evenement find(int id) {
        List<Evenement> eList = this.afficher();
        Evenement e = eList.stream().filter(evenement -> id == 
                evenement.getId_Evenement()).findAny().orElse(null);
        
        return e;
    }
    
    @Override
    public Evenement find(String name){
        List<Evenement> eList = this.afficher();
        Evenement e = eList.stream().filter(evenement -> 
                name.equals(evenement.getNom_Evenement())).findAny().orElse(null);
        
        return e;
    }
    
//--------------------------------------------------------------------------
//  Rechechrche parametré
//--------------------------------------------------------------------------
    public ObservableList<Evenement> recherche(Date d){
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `evenement` WHERE Date_Evenement = ?";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, d);
            pst.executeUpdate();
            ResultSet rs = pst.executeQuery();
            evenements = fillEList(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return evenements;
    }
    
    public ObservableList<Evenement> recherche(boolean isName, String element){
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        String req;
        if (isName){
            req = "SELECT * FROM `evenement` e WHERE EXISTS (\n" +
                "SELECT * FROM `artiste` a\n" +
                "WHERE e.Id_Artiste = a.Id_Artiste)\n" +
                "AND a.Nom_Artiste = ?";
        }
        else{
            req = "SELECT * FROM `evenement` e WHERE EXISTS (\n" +
                "SELECT * FROM `artiste` a\n" +
                "WHERE e.Id_Artiste = a.Id_Artiste)\n" +
                "AND a.Type_De_Musique = ?";
        }
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, element);
            pst.executeUpdate();
            ResultSet rs = pst.executeQuery();
            evenements = fillEList(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }
    
    public ObservableList<Evenement> recherche(Date debut, Date fin){
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        String req = "SELECT * FROM `evenement` "
                + "WHERE `Date_Evenement` "
                + "BETWEEN ? AND ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, debut);
            pst.setDate(2, fin);
            pst.executeUpdate();
            ResultSet rs = pst.executeQuery();
            evenements = fillEList(rs);
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }
    
    public ObservableList<Evenement> recherche(Date d, boolean isName,
            String element){
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        String req;
        if (isName){
            req = "SELECT * FROM `evenement` e WHERE EXISTS (" +
                "SELECT * FROM `artiste` a " +
                "WHERE e.Id_Artiste = a.Id_Artiste" +
                "AND e.Date_Evenement = ?" +
                "AND a.Nom_Artiste = ?)";
        }
        else {
            req = "SELECT * FROM `evenement` e WHERE EXISTS (" +
                "SELECT * FROM `artiste` a " +
                "WHERE e.Id_Artiste = a.Id_Artiste" +
                "AND e.Date_Evenement = ?" +
                "AND a.Type_De_Musique = ?)";
        }
        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, d);
            pst.setString(2, element);
            pst.executeUpdate();
            ResultSet rs = pst.executeQuery();
            evenements = fillEList(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }
    
    public ObservableList<Evenement> recherche(Date debut, Date fin,
            boolean isName, String element){
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        String req;
        if (isName){
            req = "SELECT * FROM `evenement` e WHERE EXISTS (" +
                "SELECT * FROM `artiste` a " +
                "WHERE e.Id_Artiste = a.Id_Artiste" +
                "AND e.Date_Evenement BETWEEN ? AND ?" +
                "AND a.Nom_Artiste = ?)";
        }
        else {
            req = "SELECT * FROM `evenement` e WHERE EXISTS (" +
                "SELECT * FROM `artiste` a " +
                "WHERE e.Id_Artiste = a.Id_Artiste" +
                "AND e.Date_Evenement BETWEEN ? AND ?" +
                "AND a.Type_De_Musique = ?)";
        }
        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, debut);
            pst.setDate(2, fin);
            pst.setString(3, element);
            pst.executeUpdate();
            ResultSet rs = pst.executeQuery();
            evenements = fillEList(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }
    
    public ObservableList<Evenement> rechercheParRest(Restaurant r){
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        String req = "SELECT * FROM evenement e WHERE EXISTS("
                + "SELECT * FROM restaurant r "
                + "WHERE e.Id_Restaurant = r.Id_Restaurant"
                + "AND r.Id_Restaurant = ?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getId_Restaurant());
            pst.executeUpdate();
            ResultSet rs = pst.executeQuery();
            evenements = fillEList(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }
    
//--------------------------------------------------------------------------
//  Compteur d'Evenements
//--------------------------------------------------------------------------
    public int numberByType(String type){
        ObservableList<Evenement> evenements = recherche(false, type);
        return evenements.size();
    }
//--------------------------------------------------------------------------
//  Fonctions utilisés par les services
//--------------------------------------------------------------------------
    private ObservableList<Evenement> fillEList(ResultSet rs){
        ObservableList<Evenement> evenements = 
                FXCollections.observableArrayList();
        try {
            while(rs.next()){
                Evenement e = new Evenement();

                e.setId_Evenement(rs.getInt("Id_Evenement"));
                e.setNom_Evenement(rs.getString(2));
                e.setDate_Evenement(rs.getDate(3));

                int id_artiste = rs.getInt("Id_Artiste");
                if (rs.wasNull()) e.setArtiste(null);
                else e.setArtiste(aServ.find(id_artiste));

                int id_restaurant = rs.getInt("Id_Restaurant");
                if (rs.wasNull()) e.setRestaurant(null);
                else e.setRestaurant(rServ.find(id_restaurant));

                evenements.add(e);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return evenements;
    }
}
