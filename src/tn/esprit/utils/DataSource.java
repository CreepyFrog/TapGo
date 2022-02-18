/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.utils;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class DataSource {
    private String url = "jdbc:mysql://localhost:3306/tapngo";
    private String user = "root";
    private String password = "";
    
    public Connection cnx;
    private static DataSource instance;
    
    public DataSource(){
        
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected");
            
        }catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
}
