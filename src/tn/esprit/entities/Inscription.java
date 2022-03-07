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
    private Cours c;
    private User u;

    public Inscription(int Id_Inscription, Cours c, User u) {
        this.Id_Inscription = Id_Inscription;
        this.c = c;
        this.u = u;
    }

 
 
    

    public Inscription() {
    }

    public Inscription(Cours c, User u) {
        this.c = c;
        this.u = u;
    }


    public int getId_Inscription() {
        return Id_Inscription;
    }

    public void setId_Inscription(int Id_Inscription) {
        this.Id_Inscription = Id_Inscription;
    }

    @Override
    public String toString() {
        return "Inscription{" + "Id_Inscription=" + Id_Inscription + ", Id_Cour=" + c.getId_Cour() + ", Id_User=" + u.getId() + '}';
    }

    
    public Cours getCours() {
        return this.c;
    }

    public void setCours(Cours c) {
        this.c=c;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

   
   
    
    
    
    
}
