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
public class Inscription {
    
    private int Id_Inscription;
    private int Id_Cour;
    private int Id_User;

    public Inscription(int Id_Inscription, int Id_Cour, int Id_User) {
        this.Id_Inscription = Id_Inscription;
        this.Id_Cour = Id_Cour;
        this.Id_User = Id_User;
    }

    public Inscription() {
    }

    public Inscription(int Id_Cour, int Id_User) {
        this.Id_Cour = Id_Cour;
        this.Id_User = Id_User;
    }

    public int getId_Inscription() {
        return Id_Inscription;
    }

    public void setId_Inscription(int Id_Inscription) {
        this.Id_Inscription = Id_Inscription;
    }

    @Override
    public String toString() {
        return "Inscription{" + "Id_Inscription=" + Id_Inscription + ", Id_Cour=" + Id_Cour + ", Id_User=" + Id_User + '}';
    }

    
    public int getId_Cour() {
        return Id_Cour;
    }

    public void setId_Cour(int Id_Cour) {
        this.Id_Cour = Id_Cour;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }
    
    
    
    
    
}
