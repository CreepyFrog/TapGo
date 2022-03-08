/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Chikaaa
 */
public class Publication {
    
    private int Id_Publication;
    private String Libelle_Publication;
    private int Nb_Reaction;
    private User u;
    private List<Commentaire> LC;

   
//    public Publication(String Libelle_Publication, int Nb_Reaction, int id) {
//        this.Libelle_Publication = Libelle_Publication;
//        this.Nb_Reaction = Nb_Reaction;
//        this.u.setId(id);
//    }
     public Publication() {
    }

    public Publication(int a) {
        this.Id_Publication=a;
//        this.Libelle_Publication = Libelle_Publication;
//        this.Nb_Reaction = Nb_Reaction;
//        this.u=u;
        
                }

    public Publication(int Id_Publication, String Libelle_Publication, int Nb_Reaction) {
        this.Id_Publication = Id_Publication;
        this.Libelle_Publication = Libelle_Publication;
        this.Nb_Reaction = Nb_Reaction;
    }
    
    

    public Publication(String Libelle_Publication, int Nb_Reaction, User user) {
        this.Libelle_Publication = Libelle_Publication;
        this.Nb_Reaction = Nb_Reaction;
        this.u=user;
    }
    
    public Publication(int Id_Publication, String Libelle_Publication, int Nb_Reaction, User us) {
        this.Id_Publication = Id_Publication;
        this.Libelle_Publication = Libelle_Publication;
        this.Nb_Reaction = Nb_Reaction;
        this.u.setId(us.getId());
    }  

        
    
    
    public int getId_Publication() {
        return Id_Publication;
    }

    public void setId_Publication(int Id_Publication) {
        this.Id_Publication = Id_Publication;
    }

    public String getLibelle_Publication() {
        return Libelle_Publication;
    }

    public void setLibelle_Publication(String Libelle_Publication) {
        this.Libelle_Publication = Libelle_Publication;
    }

    public int getNb_Reaction() {
        return Nb_Reaction;
    }

    public void setNb_Reaction(int Nb_Reaction) {
        this.Nb_Reaction = Nb_Reaction;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

//    public Publication(int Id_Publication, String Libelle_Publication, int Nb_Reaction, User u, List<Commentaire> LC) {
//        this.Id_Publication = Id_Publication;
//        this.Libelle_Publication = Libelle_Publication;
//        this.Nb_Reaction = Nb_Reaction;
//        this.u = u;
//        this.LC = LC;
//    }

//    public List<Commentaire> getLC() {
//        return LC;
//    }
//
//    public void setLC(List<Commentaire> LC) {
//        this.LC = LC;
//    }
//
//    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Publication other = (Publication) obj;
//        if (this.Id_Publication != other.Id_Publication) {
//            return false;
//        }
//        if (this.Nb_Reaction != other.Nb_Reaction) {
//            return false;
//        }
//       
//        if (this.u.getId() != other.u.getId()) {
//            return false;
//        }
//        if (!Objects.equals(this.Libelle_Publication, other.Libelle_Publication)) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "Publication{" + "Id_Publication=" + Id_Publication + ", Libelle_Publication=" + Libelle_Publication + ", Nb_Reaction=" + Nb_Reaction + ", User=" + u.getId() +'}'+ "\n";
    }
    
    
    
}
