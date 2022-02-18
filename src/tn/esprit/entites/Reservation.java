/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entites;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Reservation {
    private int Id_Reservation ;
    private int Heure;
    private String Date;     
    private int Id_Table;
    private int Id_Restaurant;
    private int Id_User;

    public Reservation() {
    }

    public Reservation(int Id_Reservation, int Heure, String Date) {
        this.Id_Reservation = Id_Reservation;
        this.Heure = Heure;
        this.Date = Date;
    }
    
    
    public Reservation(int Heure, String Date, int Id_Table, int Id_Restaurant, int Id_User) {
        this.Heure = Heure;
        this.Date = Date;
        this.Id_Table = Id_Table;
        this.Id_Restaurant = Id_Restaurant;
        this.Id_User = Id_User;
    }

    public Reservation(int Id_Reservation, int Heure, String Date, int Id_Table, int Id_Restaurant, int Id_User) {
        this.Id_Reservation = Id_Reservation;
        this.Heure = Heure;
        this.Date = Date;
        this.Id_Table = Id_Table;
        this.Id_Restaurant = Id_Restaurant;
        this.Id_User = Id_User;
    }
    


  

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    

    public Reservation(int Id_Restaurant, int Heure) {
        this.Id_Restaurant = Id_Restaurant;
        this.Heure = Heure;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }


    public int getId_Reservation() {
        return Id_Reservation;
    }

    public void setId_Reservation(int Id_Reservation) {
        this.Id_Reservation = Id_Reservation;
    }

    public int getId_Restaurant() {
        return Id_Restaurant;
    }

    public void setId_Restaurant(int Id_Restaurant) {
        this.Id_Restaurant = Id_Restaurant;
    }

    public int getHeure() {
        return Heure;
    }

    public void setHeure(int Heure) {
        this.Heure = Heure;
    }
    
        public int getId_Table() {
        return Id_Table;
    }

    public void setId_Table(int Id_Table) {
        this.Id_Table = Id_Table;
    }

    @Override
    public String toString() {
        return "Reservation{" + "Id_Reservation=" + Id_Reservation + ", Id_Restaurant=" + Id_Restaurant + ", Id_Table=" + Id_Table + ", Heure=" + Heure + '}';
    }
    
}
