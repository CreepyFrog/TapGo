/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author USER
 */
public class Plat {
    private int id;
    private String  nom ;
    private String element ;
    private Menu Mn;

    

   
    public Plat() {
    }

    public Plat(String nom, String element, Menu Mn) {
        this.nom = nom;
        this.element = element;
        this.Mn = Mn;
    }

    public Plat(int id, String nom, String element, Menu Mn) {
        this.id = id;
        this.nom = nom;
        this.element = element;
        this.Mn = Mn;
    }

 
    
   


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getElement() {
        return element;
    }

    public void setMn(Menu Mn) {
        this.Mn = Mn;
    }

  

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setElement(String element) {
        this.element = element;
    }

  

    public Menu getMn() {
        return Mn;
    }
    
    @Override
    public String toString() {
        return "Plat{" + "id=" + id + ", nom=" + nom + ", element=" + element + ", Menu=" + Mn.getNom() + '}';
    }
    
}
