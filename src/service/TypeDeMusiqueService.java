/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Util.DB.DataSources;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Evenement;
import models.IService;
import models.Type_De_Musique;

/**
 *
 * @author ASUS
 */
public class TypeDeMusiqueService implements IService<Type_De_Musique>{
    
    private final Connection conn;
    private PreparedStatement pst;
    
    public TypeDeMusiqueService() {
        conn = DataSources.getInstance().getCnx();
    }

    @Override
    public void ajouter(Type_De_Musique m) {
        String req = "INSERT INTO `type_de_musique`(`genre`) VALUES (?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getGenre());
            
            pst.executeUpdate();
            System.out.println("Type De Musique ajouté");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Type_De_Musique m) {
        String req = "DELETE FROM `type_de_musique` WHERE `id` = ?";
        
        try{
            pst = conn.prepareStatement(req);
            pst.setInt(1, m.getId());
            
            pst.executeUpdate();
            System.out.println("Type de musique Supprimé");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Type_De_Musique m) {
        String req = "UPDATE type_de_musique SET genre=?"
                + "WHERE Id_Evenement=?";
        
        try {
            pst = conn.prepareStatement(req);
            
            pst.setString(1, m.getGenre());
            pst.setInt(2, m.getId());
            
            pst.executeUpdate();
            System.out.println("Type de musique mis à jour");
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Type_De_Musique> afficher() {
        ObservableList<Type_De_Musique> musiques = 
                FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `type_de_musique`";
        
        try {
            
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            musiques = fillTMList(rs);
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return musiques;
    }

    @Override
    public Type_De_Musique find(String genre) {
        List<Type_De_Musique> mList = this.afficher();
        Type_De_Musique m = mList.stream().filter(musique -> 
                genre.equals(musique.getGenre())).findAny().orElse(null);
        
        return m;
    }
    
    
    
    public Type_De_Musique find(int id) {
        List<Type_De_Musique> mList = this.afficher();
        Type_De_Musique m = mList.stream().filter(musique -> id == 
                musique.getId()).findAny().orElse(null);
        
        return m;
    }
    
//--------------------------------------------------------------------------
//  Fonctions utilisés par les services
//--------------------------------------------------------------------------
    private ObservableList<Type_De_Musique> fillTMList(ResultSet rs){
        ObservableList<Type_De_Musique> musiques = 
                FXCollections.observableArrayList();
        try {
            while(rs.next()){
                Type_De_Musique m = new Type_De_Musique();

                m.setId(rs.getInt("id"));
                m.setGenre(rs.getString(2));

                musiques.add(m);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return musiques;
    }
}
