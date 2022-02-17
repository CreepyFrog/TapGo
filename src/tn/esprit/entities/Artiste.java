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
public class Artiste {
    
    private int Id_Artiste;
    private String Nom_Artiste;
    private String Type_De_Musique;

    public int getId_Artiste() {
        return Id_Artiste;
    }

    public void setId_Artiste(int Id_Artiste) {
        this.Id_Artiste = Id_Artiste;
    }

    public String getNom_Artiste() {
        return Nom_Artiste;
    }

    public void setNom_Artiste(String Nom_Artiste) {
        this.Nom_Artiste = Nom_Artiste;
    }

    public String getType_De_Musique() {
        return Type_De_Musique;
    }

    public void setType_De_Musique(String Type_De_Musique) {
        this.Type_De_Musique = Type_De_Musique;
    }

    public Artiste() {
    }

    public Artiste(String Nom_Artiste, String Type_De_Musique) {
        this.Nom_Artiste = Nom_Artiste;
        this.Type_De_Musique = Type_De_Musique;
    }

    @Override
    public String toString() {
        return "Artiste{" + "Id_Artiste=" + Id_Artiste + ", Nom_Artiste=" + Nom_Artiste + ", Type_De_Musique=" + Type_De_Musique + '}';
    }
    
}
