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
import tn.esprit.entities.Artiste;
import tn.esprit.entities.Evenement;
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

//--------------------------------------------------------------------------
//  CRUD
//--------------------------------------------------------------------------
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
    public ObservableList<Artiste> afficher() {
        ObservableList<Artiste> artistes = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `artiste`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            artistes = fillAList(rs);
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return artistes;
    }
    
//--------------------------------------------------------------------------
//  Find a specific Artiste
//--------------------------------------------------------------------------    
    @Override
    public Artiste find(int id) {
        ObservableList<Artiste> aList = FXCollections.observableArrayList();
        
        aList = this.afficher();
        Artiste a = aList.stream().filter(artiste -> id == artiste.getId_Artiste()).findAny().orElse(null);
        
        return a;
    }
    
    @Override
    public Artiste find(String name){
        List<Artiste> aList = new ArrayList<>();
        
        aList = this.afficher();
        Artiste a = aList.stream().filter(artiste -> name == artiste.getNom_Artiste()).findAny().orElse(null);
        
        return a;
    }
    
//--------------------------------------------------------------------------
//  Recherche
//--------------------------------------------------------------------------  
    public ObservableList<Artiste> rechercheMusique(String m){
        ObservableList<Artiste> artistes =
                FXCollections.observableArrayList();
        String req = "SELECT * FROM artiste WHERE Type_De_Musique = ?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m);
            pst.executeUpdate();
            ResultSet rs = pst.executeQuery();
            artistes = fillAList(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return artistes;
    }
    
//--------------------------------------------------------------------------
//  List of Types
//-------------------------------------------------------------------------- 
    public ObservableList<String> typesMusique(){
        ObservableList<String> types = FXCollections.observableArrayList();
        ObservableList<Artiste> artistes = rechercheUniqueMusique();
        for (Artiste a : artistes){
            types.add(a.getType_De_Musique());
        }
        return types;
    }
            
//--------------------------------------------------------------------------
//  Fonctions utilisés par les services
//--------------------------------------------------------------------------  
    private ObservableList<Artiste> fillAList(ResultSet rs){
        ObservableList<Artiste> artistes = 
                FXCollections.observableArrayList();
        try {
            while(rs.next()){
                Artiste a = new Artiste();

                a.setId_Artiste(rs.getInt("Id_Artiste"));
                a.setNom_Artiste(rs.getString(2));
                a.setType_De_Musique(rs.getString(3));

                artistes.add(a);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return artistes;
    }
    
    private ObservableList<Artiste> rechercheUniqueMusique(){
        ObservableList<Artiste> artistes =
                FXCollections.observableArrayList();
        String req = "SELECT DISTINCT Type_De_Musique FROM artiste";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            artistes = fillAList(rs);
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return artistes;
    }
    
    
    
}
