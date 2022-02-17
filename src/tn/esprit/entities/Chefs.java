/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author MSI
 */
public class Chefs {
    private int ID_Chef;
    private String Nom_Chef; 
    private String Adresse_Chef;
    private String Cour_Associé;

    public Chefs(int ID_Chef, String Nom_Chef, String Cour_associé) {
        this.ID_Chef = ID_Chef;
        this.Nom_Chef = Nom_Chef;
        this.Adresse_Chef = Adresse_Chef;
        this.Cour_Associé = Cour_associé;
    }

    public Chefs() {
    }

    public Chefs(String Nom_Chef, String Adresse_Chef, String Cour_associé) {
        this.Nom_Chef = Nom_Chef;
        this.Adresse_Chef = Adresse_Chef;
        this.Cour_Associé = Cour_associé;
    }

    public int getID_Chef() {
        return ID_Chef;
    }

    public void setID_Chef(int ID_Chef) {
        this.ID_Chef = ID_Chef;
    }

    @Override
    public String toString() {
        return "Chefs{" + "ID_Chef=" + ID_Chef + ", Nom_Chef=" + Nom_Chef + ", Adresse_Chef=" + Adresse_Chef + ", Cour_associ\u00e9=" + Cour_Associé + '}';
    }

    public String getNom_Chef() {
        return Nom_Chef;
    }

    public void setNom_Chef(String Nom_Chef) {
        this.Nom_Chef = Nom_Chef;
    }

    public String getAdresse_Chef() {
        return Adresse_Chef;
    }

    public void setAdresse_Chef(String Adresse_Chef) {
        this.Adresse_Chef = Adresse_Chef;
    }

    public String getCour_Associé() {
        return Cour_Associé;
    }

    public void setCour_Associé(String Cour_associé) {
        this.Cour_Associé = Cour_associé;
    }

 

    
   
    
    
}
