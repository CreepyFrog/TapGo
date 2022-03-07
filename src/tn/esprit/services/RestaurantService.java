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
import tn.esprit.entities.Restaurant;
import tn.esprit.utils.DataSource;

/**
 *
 * @author ASUS
 */
public class RestaurantService implements IService<Restaurant>{
    
    private Connection conn = DataSource.getInstance().getConnection();;
    private PreparedStatement pst;
    
    public RestaurantService(){
        conn = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Restaurant entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Restaurant entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Restaurant entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Restaurant> afficher() {
        ObservableList<Restaurant> restaurants = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `restaurant`";
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Restaurant r = new Restaurant();
                
                r.setId_Restaurant(rs.getInt("Id_Restaurant"));
                r.setNom_Restaurant(rs.getString(2));
                
                //find Artiste and Restaurant by id and set them as objects in Evenement
                //e.setArtiste(rs.);
                //e.setRestaurant(rs.);
                
                restaurants.add(r);
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return restaurants;
    }

    @Override
    public Restaurant find(int id) {
        List<Restaurant> rList = new ArrayList<>();
        
        rList = this.afficher();
        Restaurant r = rList.stream().filter(restaurant -> id == restaurant.getId_Restaurant()).findAny().orElse(null);
        
        return r;
    }

    @Override
    public Restaurant find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
