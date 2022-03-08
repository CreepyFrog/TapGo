/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Chikaaa
 */
public class Reaction {
    private int Id_Reaction ;
    private int Type_Reaction;
    private User u;
    private Publication p;

    public Reaction() {
    }

    public Reaction(int Id_Reaction, int Type_Reaction) {
        this.Id_Reaction = Id_Reaction;
        this.Type_Reaction = Type_Reaction;
    }

    public Reaction(int Type_Reaction, User u, Publication p) {
        this.Type_Reaction = Type_Reaction;
        this.u = u;
        this.p = p;
    }

    public Reaction(int Id_Reaction, int Type_Reaction, User u, Publication p) {
        this.Id_Reaction = Id_Reaction;
        this.Type_Reaction = Type_Reaction;
        this.u = u;
        this.p = p;
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
    
    public int getId_Reaction() {
        return Id_Reaction;
    }

    public void setId_Reaction(int Id_Reaction) {
        this.Id_Reaction = Id_Reaction;
    }

    public int getType_Reaction() {
        return Type_Reaction;
    }

    public void setType_Reaction(int Type_Reaction) {
        this.Type_Reaction = Type_Reaction;
    }

    @Override
    public String toString() {
        return "Reaction{" + "Id_Reaction=" + Id_Reaction + ", Type_Reaction=" + Type_Reaction + ", Id_publication=" + p.getId_Publication() + ", Id_user=" + u.getId() + '}'+"\n";
    }
  
    
}
