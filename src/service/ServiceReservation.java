/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import tn.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entites.Reservation;
import tn.esprit.entites.Restaurant;
import tn.esprit.entites.User;
import tn.esprit.entites.table;



import static javax.swing.text.StyleConstants.Alignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


/**
 *
 * @author asus
 */
public class ServiceReservation  {
    Connection cnx = DataSource.getInstance().getConnection();
    ObservableList<Reservation> list = FXCollections.observableArrayList();

    public void Ajouter(Reservation t) {
        try{
        String requete = "INSERT INTO reservation(Heure,Date,Id_Table,Id_Restaurant,Id_User) VALUES ("+t.getHeure()+","+t.getDate()+","+t.getTbl().getId_Table()+","+t.getRest().getId_restaurant()+","+t.getUsr().getId()+")";
        
        PreparedStatement pst =new DataSource().cnx.prepareStatement(requete);
        
       /* pst.setInt(1, t.getHeure());
        pst.setString(2, t.getDate());
        pst.setInt(3, t.getId_Table());
        pst.setInt(4, t.getId_Restaurant());
        pst.setInt(5, t.getId_User());
        */
        
        
        pst.executeUpdate();
        System.out.println("Reservation ajouté !");
        }catch (SQLException ex) {
              Logger.getLogger(service.ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void AjouterParNom(Reservation t) {
        try{
        String requete = "INSERT INTO reservation(Heure,Date,Id_Table,Nom_Restaurant,Nom_User) VALUES ("+t.getHeure()+","+t.getDate()+","+t.getTbl().getId_Table()+","+t.getRest().getNom()+","+t.getUsr().getName()+")";
        
        PreparedStatement pst =new DataSource().cnx.prepareStatement(requete);
        
       /* pst.setInt(1, t.getHeure());
        pst.setString(2, t.getDate());
        pst.setInt(3, t.getId_Table());
        pst.setInt(4, t.getId_Restaurant());
        pst.setInt(5, t.getId_User());
        */
        
        
        pst.executeUpdate();
        System.out.println("Reservation ajouté !");
        }catch (SQLException ex) {
              Logger.getLogger(service.ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
             public int getNbit() {
        String sql="SELECT COUNT(*) FROM reservation where Date='21'";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
         
         public int getNbtu() {
        String sql="SELECT COUNT(*) FROM reservation where Date='22'";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
         
         public int getNbch() {
        String sql="SELECT COUNT(*) FROM reservation where Date='23'";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }

    
    
    
    
    
    
       public int getNbrReserv(int idl) {
        String sql="SELECT COUNT(*) FROM reservation where Id_Restaurant='"+idl+"'";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
            ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }

    public void Supprimer(int id) {
        try{
        String requete = "DELETE FROM reservation WHERE Id_Reservation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Reservation Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
        
    public void Modifier(Reservation t) {
       try{
        String requete = "UPDATE reservation SET Heure=? , Date=? WHERE Id_Reservation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);    
        pst.setInt(1, t.getHeure());
        pst.setString(2, t.getDate());
        pst.setInt(3, t.getId_Reservation());
        pst.executeUpdate();
        System.out.println("reservation modifié !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Reservation> Afficher() {
         
        try{
        String requete = "SELECT * FROM reservation";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
            Reservation a = new Reservation();
            a.setId_Reservation(rs.getInt(1));
            a.setHeure(rs.getInt(2));
            a.setDate(rs.getString(3));
            a.setTbl(new table(rs.getInt(4)));
            a.setRest(new Restaurant(rs.getInt(5)));
            a.setUsr(new User(rs.getInt(6)));
            list.add(a);
           // int Heure, Date Date, table tbl, Restaurant rest, User Usr
        }
        System.out.println("Reservation !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }
    
        public ObservableList<Reservation> AfficherNom() {
         
        try{
        String requete = "SELECT `Nom_Restaurant` FROM `restaurant`";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            
            Reservation a = new Reservation();
            a.setRest(new Restaurant(rs.getString(1)));
                    //(new Restaurant(rs.getString(5)));
           
            list.add(a);
           // int Heure, Date Date, table tbl, Restaurant rest, User Usr
        }
        System.out.println("Reservation !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }

   public void e_bookPDF() throws FileNotFoundException, DocumentException, MalformedURLException, IOException {
        Document document = new Document();
        ServiceReservation rm = new ServiceReservation();
        List<Reservation> res = rm.Afficher();
        PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\xampp\\htdocs\\tapgo\\document\\ReservationPDF.pdf")));
        document.open();

        for (Reservation r : res) {
            Paragraph reservation = new Paragraph("Reservation " + r.getId_Reservation());
            reservation.setAlignment(Element.ALIGN_CENTER);
            //document.add(reservation);
            Paragraph Heure = new Paragraph("Heure : " + r.getHeure());
            Heure.setAlignment(Element.ALIGN_LEFT);
            Paragraph Date = new Paragraph("Date : " + r.getClass());
            Date.setAlignment(Element.ALIGN_LEFT);
            Paragraph Table = new Paragraph("Table: " + r.getTbl());
            Table.setAlignment(Element.ALIGN_LEFT);
            Paragraph Restaurant = new Paragraph("Restaurant : " + r.getRest());
            Restaurant.setAlignment(Element.ALIGN_LEFT);
            Paragraph User = new Paragraph("User : " + r.getUsr());
            User.setAlignment(Element.ALIGN_LEFT);
            document.add(reservation);
            document.add(Heure);
            document.add(Date);
            document.add(Table);
            document.add(Restaurant);
            document.add(User);
        }
        document.close();
    }
    
}
