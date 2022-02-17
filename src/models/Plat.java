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
    private String Menu;

    public Plat(int id, String nom, String element, String Menu) {
        this.id = id;
        this.nom = nom;
        this.element = element;
        this.Menu = Menu;
    }

    public Plat() {
    }

    public Plat(String nom, String element, String Menu) {
        this.nom = nom;
        this.element = element;
        this.Menu = Menu;
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

    public String getMenu() {
        return Menu;
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

    public void setMenu(String Menu) {
        this.Menu = Menu;
    }
    
}
