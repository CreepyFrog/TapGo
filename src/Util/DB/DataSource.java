/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import java.sql.*;


import models.Restaurant;
/**
 *
 * @author USER
 */
public class DataSource {

  
    private Connection cnx ;
    
    private static DataSource instance;
    
    private final String url= "jdbc:mysql://127.0.0.1/projet31";
    
    private final String username="root";
    
    private final String password="";
     public DataSource() {
        try {
            cnx=DriverManager.getConnection(url, username, password);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
             public static ObservableList<Restaurant> getDataRestaurant(){
        Connection conn = ConnectDb();
        ObservableList<Restaurant> listL = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from restaurant");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                listL.add(new Restaurant(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5))); 
            }
        } catch (Exception e) {
        }
        return listL;
    }
            
    public static DataSource getInstance() {
        if (instance == null)
            instance = new DataSource();
        return instance;
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/projet31","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
  
    
     

   
}
