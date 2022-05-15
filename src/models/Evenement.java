/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import models.Restaurant;
import models.Artiste;

/**
 *
 * @author ASUS
 */
public class Evenement {
    private int Id_Evenement;
    private String Nom_Evenement;
    private Date Date_Evenement;
    private Artiste artiste;
    private Restaurant restaurant;

    public int getId_Evenement() {
        return Id_Evenement;
    }

    public void setId_Evenement(int Id_Evenement) {
        this.Id_Evenement = Id_Evenement;
    }

    public String getNom_Evenement() {
        return Nom_Evenement;
    }

    public void setNom_Evenement(String Nom_Evenement) {
        this.Nom_Evenement = Nom_Evenement;
    }

    public Date getDate_Evenement() {
        return Date_Evenement;
    }

    public void setDate_Evenement(Date Date_Evenement) {
        this.Date_Evenement = Date_Evenement;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Evenement() {
    }

    public Evenement(String Nom_Evenement, Date Date_Evenement) {
        this.Nom_Evenement = Nom_Evenement;
        this.Date_Evenement = Date_Evenement;
    }

    public Evenement(int Id_Evenement, String Nom_Evenement, Date Date_Evenement) {
        this.Id_Evenement = Id_Evenement;
        this.Nom_Evenement = Nom_Evenement;
        this.Date_Evenement = Date_Evenement;
    }
    
    public Evenement(String Nom_Evenement, Date Date_Evenement, Artiste artiste, Restaurant restaurant) {
        this.Nom_Evenement = Nom_Evenement;
        this.Date_Evenement = Date_Evenement;
        this.artiste = artiste;
        this.restaurant = restaurant;
    }

    public Evenement(int Id_Evenement, String Nom_Evenement, Date Date_Evenement, Artiste artiste, Restaurant restaurant) {
        this.Id_Evenement = Id_Evenement;
        this.Nom_Evenement = Nom_Evenement;
        this.Date_Evenement = Date_Evenement;
        this.artiste = artiste;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        String a, r;
        
        if (this.artiste == null) a = "Pas de Artiste Associé";
        else a = String.valueOf(artiste.getId_Artiste());
        
        if (this.restaurant == null) r = "Pas de Restaurant Associé";
        else r = String.valueOf(restaurant.getId_restaurant());
        
        return Nom_Evenement;
    }
    
}
