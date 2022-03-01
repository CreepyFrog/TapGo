/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author ASUS
 */
public class Restaurant {
    
    private int Id_Restaurant;
    private String Nom_Restaurant;
    //private String Adresse_Restaurant;
    //private String Domaine_Restaurant;
    //private int Nb_Tables;

    public int getId_Restaurant() {
        return Id_Restaurant;
    }

    public void setId_Restaurant(int Id_Restaurant) {
        this.Id_Restaurant = Id_Restaurant;
    }

    public String getNom_Restaurant() {
        return Nom_Restaurant;
    }

    public void setNom_Restaurant(String Nom_Restaurant) {
        this.Nom_Restaurant = Nom_Restaurant;
    }

    public Restaurant() {
    }

    public Restaurant(String Nom_Restaurant) {
        this.Nom_Restaurant = Nom_Restaurant;
    }

    public Restaurant(int Id_Restaurant, String Nom_Restaurant) {
        this.Id_Restaurant = Id_Restaurant;
        this.Nom_Restaurant = Nom_Restaurant;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "Id_Restaurant=" + Id_Restaurant + ", Nom_Restaurant=" + Nom_Restaurant + '}';
    }
    
}
