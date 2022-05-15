/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author USER
 */
public class Menu {
   private  int id ;
   private String Nom ; 
   private String Type ;
   private String Restaurant ;

    public Menu(String Nom) {
        this.Nom = Nom;
    }

    public Menu(int id, String Nom, String Type, String Restaurant) {
        this.id = id;
        this.Nom = Nom;
        this.Type = Type;
        this.Restaurant = Restaurant;
    }

    public Menu(String Nom, String Type, String Restaurant) {
        this.Nom = Nom;
        this.Type = Type;
        this.Restaurant = Restaurant;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return Nom;
    }

    public String getType() {
        return Type;
    }

    public String getRestaurant() {
        return Restaurant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setRestaurant(String Restaurant) {
        this.Restaurant = Restaurant;
    }
    
}
