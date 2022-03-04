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
public class table {
    private int Id_Table ;
    private int Type_Table;
    private Restaurant rest;

    public table() {
    }

    public table(int a) {
        this.Id_Table = a;
    }
    
    
    
    
    //modifier
     /*   public table(int Id_Table,int Type_Table) {
        this.Id_Table=Id_Table;
        this.Type_Table = Type_Table;
        
    } */
    
    //ajouter
        public table(int Type_Table, Restaurant rest) {
        this.Type_Table=Type_Table;
        this.rest = rest;
        
    }
    
    public table(int Id_Table, int Type_Table, Restaurant rest) {
        this.Id_Table = Id_Table;
        this.Type_Table = Type_Table;
        this.rest = rest;
    }

    


    public int getType_Table() {
        return Type_Table;
    }

    public void setType_Table(int Type_Table) {
        this.Type_Table = Type_Table;
    }


    public int getId_Table() {
        return Id_Table;
    }

    public void setId_Table(int Id_Table) {
        this.Id_Table = Id_Table;
    }

    public Restaurant getrest() {
        return rest;
    }

    public void setrest(Restaurant rest) {
        this.rest = rest;
    }


    

    @Override
    public String toString() {
        return  ""+Id_Table+"" ;
    }
   
    
}
