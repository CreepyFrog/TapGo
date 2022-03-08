/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Chikaaa
 */
public class Commentaire {
    
    private int Id_Commentaire;
    private String libelle_commentaire;
    private User u;
    private Publication p;

    public Commentaire() {
    }

    public Commentaire(int Id_Commentaire, String libelle_commentaire) {
        this.Id_Commentaire = Id_Commentaire;
        this.libelle_commentaire = libelle_commentaire;
    }
    
    

    public Commentaire(String libelle_commentaire, User u1, Publication p1) {
        this.libelle_commentaire = libelle_commentaire;
        this.u=u1;
        this.p=p1;
    }
    public Commentaire(String libelle_commentaire, int x, int a) {
        this.libelle_commentaire = libelle_commentaire;
        this.u.setId(x);
        this.p.setId_Publication(a);
    }
    
    

    public Commentaire(int Id_Commentaire, String libelle_commentaire, int u1 ,int p1) {
        this.Id_Commentaire = Id_Commentaire;
        this.libelle_commentaire = libelle_commentaire;
        this.u.setId(u1);
        this.p.setId_Publication(p1);
    }
    
    

    public Commentaire(int Id_Commentaire, String libelle_commentaire, User u, Publication p) {
        this.Id_Commentaire = Id_Commentaire;
        this.libelle_commentaire = libelle_commentaire;
        this.u = u;
        this.p = p;
    }

    public int getId_Commentaire() {
        return Id_Commentaire;
    }

    public void setId_Commentaire(int Id_Commentaire) {
        this.Id_Commentaire = Id_Commentaire;
    }

    public String getLibelle_commentaire() {
        return libelle_commentaire;
    }

    public void setLibelle_commentaire(String libelle_commentaire) {
        this.libelle_commentaire = libelle_commentaire;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Publication getP() {
        return p;
    }

    public void setP(Publication p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "Id_Commentaire=" + Id_Commentaire + ", libelle_commentaire=" + libelle_commentaire + ", u=" + u.getId() + ", p=" + p.getId_Publication() +'}'+ "\n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commentaire other = (Commentaire) obj;
        if (this.Id_Commentaire != other.Id_Commentaire) {
            return false;
        }
        if (!Objects.equals(this.libelle_commentaire, other.libelle_commentaire)) {
            return false;
        }
        if (!Objects.equals(this.u, other.u)) {
            return false;
        }
        if (!Objects.equals(this.p, other.p)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
