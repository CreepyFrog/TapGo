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
<<<<<<< HEAD
    private table tbl;
    private Restaurant rest;
    private User Usr;
=======
    private int Id_Table;
    private int Id_Restaurant;
    private int Id_User;
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765

    public Reservation() {
    }

<<<<<<< HEAD
   

=======
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
    public Reservation(int Id_Reservation, int Heure, String Date) {
        this.Id_Reservation = Id_Reservation;
        this.Heure = Heure;
        this.Date = Date;
    }
    
    
<<<<<<< HEAD
    public Reservation(int Heure, String Date, table tbl, Restaurant rest, User Usr) {
        this.Heure = Heure;
        this.Date = Date;
        this.tbl = tbl;
        this.rest = rest;
        this.Usr = Usr;
    }

    public Reservation(int Id_Reservation, int Heure, String Date, table tbl, Restaurant rest, User Usr) {
        this.Id_Reservation = Id_Reservation;
        this.Heure = Heure;
        this.Date = Date;
        this.tbl = tbl;
        this.rest = rest;
        this.Usr = Usr;
=======
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
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
    }
    


  

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    

<<<<<<< HEAD
    public Reservation(Restaurant rest, int Heure) {
        this.rest = rest;
        this.Heure = Heure;
    }

    public Reservation(Restaurant rest) {
        this.rest = rest;
    }

    public User getUsr() {
        return Usr;
    }

    public void setUsr(User Usr) {
        this.Usr = Usr;
=======
    public Reservation(int Id_Restaurant, int Heure) {
        this.Id_Restaurant = Id_Restaurant;
        this.Heure = Heure;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
    }


    public int getId_Reservation() {
        return Id_Reservation;
    }

    public void setId_Reservation(int Id_Reservation) {
        this.Id_Reservation = Id_Reservation;
    }

<<<<<<< HEAD
    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
=======
    public int getId_Restaurant() {
        return Id_Restaurant;
    }

    public void setId_Restaurant(int Id_Restaurant) {
        this.Id_Restaurant = Id_Restaurant;
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
    }

    public int getHeure() {
        return Heure;
    }

    public void setHeure(int Heure) {
        this.Heure = Heure;
    }
    
<<<<<<< HEAD
        public table getTbl() {
        return tbl;
    }

    public void setTbl(table tbl) {
        this.tbl = tbl;
=======
        public int getId_Table() {
        return Id_Table;
    }

    public void setId_Table(int Id_Table) {
        this.Id_Table = Id_Table;
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "Reservation{" + "Id_Reservation=" + Id_Reservation + ", Heure=" + Heure + ", Date=" + Date + ", tbl=" + tbl.getId_Table() + ", rest=" + rest + ", Usr=" + Usr + '}';
    }

   

   
=======
        return "Reservation{" + "Id_Reservation=" + Id_Reservation + ", Id_Restaurant=" + Id_Restaurant + ", Id_Table=" + Id_Table + ", Heure=" + Heure + '}';
    }
>>>>>>> b3cc4a281f7d715182cabcc571100ee6eb7bf765
    
}
