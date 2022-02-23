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
public class Cours {
    private int Id_Cour;
    private String Nom_cour; 
    private String Libelle_Cour;
    private int ID_Chef;


    public Cours(int Id_Cour, String Nom_cour, String Libelle_Cour, int ID_Chef) {
        this.Id_Cour = Id_Cour;
        this.Nom_cour = Nom_cour;
        this.Libelle_Cour = Libelle_Cour;
        this.ID_Chef = ID_Chef;
    }

   
    public Cours() {
    }

    public Cours(String Nom_cour, String Libelle, int ID_Chef) {
        this.Nom_cour = Nom_cour;
        this.Libelle_Cour = Libelle;
        this.ID_Chef = ID_Chef;
    }

    public Cours(String pizza_maker, String pizza_is_an_italien_meal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getId_Cour() {
        return Id_Cour;
    }

    public void setId_Cour(int Id_Cour) {
        this.Id_Cour = Id_Cour;
    }

    @Override
    public String toString() {
        return "Cours{" + "Id_Cour=" + Id_Cour + ", Nom_cour=" + Nom_cour + ", Libelle_Cour=" + Libelle_Cour + ", ID_Chef=" + ID_Chef + '}';
    }

    public String getNom_cour() {
        return Nom_cour;
    }

    public void setNom_cour(String Nom_cour) {
        this.Nom_cour = Nom_cour;
    }

    public String getLibelle_Cour() {
        return Libelle_Cour;
    }

    public void setLibelle_Cour(String Libelle) {
        this.Libelle_Cour = Libelle;
    }

    public int getID_Chef() {
        return ID_Chef;
    }

    public void setID_Chef(int ID_Chef) {
        this.ID_Chef = ID_Chef;
    }






    
}
