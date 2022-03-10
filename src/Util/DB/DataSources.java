/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.DB;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class DataSources {
    private String url = "jdbc:mysql://localhost:3306/projet31";
    private String user = "root";
    private String password = "";
    
    private Connection cnx;
    private static DataSources instance;
    
    private DataSources(){
        
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected");
            
        }catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static DataSources getInstance() {
        if (instance == null) {
            instance = new DataSources();
        }
        return instance;
    }
    
    public Connection getCnx(){
        return cnx;
    }

    //public Connection getCnx() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    

  

}