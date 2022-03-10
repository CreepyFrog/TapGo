/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entites;

/**
 *
 * @author USER
 */
public class Restaurant {
        private int id_restaurant ;
    private String nom;
    private String adresse ;
    private String domaine;
    private String owner ;
    private int nb;

    public Restaurant() {
    }

    public Restaurant(String nom) {
        this.nom = nom;
    }

    public Restaurant(int id_restaurant, String nom) {
        this.id_restaurant = id_restaurant;
        this.nom = nom;
    }

    public Restaurant(int b) {
        this.id_restaurant = b;
    }
    

    public Restaurant(int id_restaurant, String nom, String adresse, String domaine, String owner, int nb) {
        this.id_restaurant = id_restaurant;
        this.nom = nom;
        this.adresse = adresse;
        this.domaine = domaine;
        this.owner = owner;
        this.nb = nb;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getOwner() {
        return owner;
    }

    public Restaurant(int id_restaurant, String nom, String adresse, String domaine, String owner) {
        this.id_restaurant = id_restaurant;
        this.nom = nom;
        this.adresse = adresse;
        this.domaine = domaine;
        this.owner = owner;
    }

    public Restaurant(String nom, String adresse, String domaine, String owner, int nb) {
        this.nom = nom;
        this.adresse = adresse;
        this.domaine = domaine;
        this.owner = owner;
        this.nb=nb;
    }

    @Override
    public String toString() {
        return  ""+id_restaurant+"" ;
    }

    
    
}
