/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class Type_De_Musique {
    
    //****************************************
    //Attributs
    //****************************************
    private int id;
    private String genre;
    
    //****************************************
    //Getters & Setters
    //****************************************

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    //****************************************
    //Constructors
    //****************************************

    public Type_De_Musique() {
    }

    public Type_De_Musique(String genre) {
        this.genre = genre;
    }

    public Type_De_Musique(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }
    
    //****************************************
    //Base Functions
    //****************************************

    @Override
    public String toString() {
        return genre;
    }
    
}
