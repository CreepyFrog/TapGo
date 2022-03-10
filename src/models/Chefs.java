/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author MSI
 */
public class Chefs {
    private int ID_Chef;
    private String Nom_Chef; 
    private String Cours_Associe;
    private String Adresse_Chef;

  
    public Chefs(int ID_Chef, String Nom_Chef, String Cours_Associe, String Adresse_Chef) {
        this.ID_Chef = ID_Chef;
        this.Nom_Chef = Nom_Chef;
        this.Cours_Associe = Cours_Associe;
        this.Adresse_Chef = Adresse_Chef;
    }

    public Chefs() {
    }

    public Chefs(String Nom_Chef, String Cours_Associe, String Adresse_Chef) {
        this.Nom_Chef = Nom_Chef;
        this.Cours_Associe = Cours_Associe;
        this.Adresse_Chef = Adresse_Chef;
    }

    public Chefs(int i) {
        this.ID_Chef=i;
    }

    
    public int getID_Chef() {
        return ID_Chef;
    }

    public void setID_Chef(int ID_Chef) {
        this.ID_Chef = ID_Chef;
    }

    @Override
    public String toString() {
        return "Chefs{" + "ID_Chef=" + ID_Chef + ", Nom_Chef=" + Nom_Chef + ", Cours_Associe=" + Cours_Associe + ", Adresse_Chef=" + Adresse_Chef + '}';
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

    public String getCours_Associe() {
        return Cours_Associe;
    }

    public void setCours_Associe(String Cours_associe) {
        this.Cours_Associe = Cours_associe;
    }

    
   
    
    
}
