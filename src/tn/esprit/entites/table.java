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
    private int Id_Restaurant;

    public table() {
    }
    
    
    
    public table(int Id_Table,int Type_Table) {
        this.Id_Table=Id_Table;
        this.Type_Table = Type_Table;
        
    }

    
    
    public table(int Id_Table, int Type_Table, int Id_Restaurant) {
        this.Id_Table = Id_Table;
        this.Type_Table = Type_Table;
        this.Id_Restaurant = Id_Restaurant;
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

    public int getId_Restaurant() {
        return Id_Restaurant;
    }

    public void setId_Restaurant(int Id_Restaurant) {
        this.Id_Restaurant = Id_Restaurant;
    }


    @Override
    public String toString() {
        return "table{" + "Id_Table=" + Id_Table + ", Type_Tablet=" + Type_Table + ", Id_Restaurant=" + Id_Restaurant +'}';
    }
    
}
