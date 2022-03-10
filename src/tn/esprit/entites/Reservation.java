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
    private table tbl;
    private Restaurant rest;
    private User Usr;

    public Reservation() {
    }

   

    public Reservation(int Id_Reservation, int Heure, String Date) {
        this.Id_Reservation = Id_Reservation;
        this.Heure = Heure;
        this.Date = Date;
    }
    
    
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
    }
    


  

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    

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
    }


    public int getId_Reservation() {
        return Id_Reservation;
    }

    public void setId_Reservation(int Id_Reservation) {
        this.Id_Reservation = Id_Reservation;
    }

    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }

    public int getHeure() {
        return Heure;
    }

    public void setHeure(int Heure) {
        this.Heure = Heure;
    }
    
        public table getTbl() {
        return tbl;
    }

    public void setTbl(table tbl) {
        this.tbl = tbl;
    }

    @Override
    public String toString() {
        return "Reservation{" + "Id_Reservation=" + Id_Reservation + ", Heure=" + Heure + ", Date=" + Date + ", tbl=" + tbl.getId_Table() + ", rest=" + rest + ", Usr=" + Usr + '}';
    }

   

   
    
}
