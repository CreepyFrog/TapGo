/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import Data.Datasource;
import entities.Publication;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class PublierInterfaceController implements Initializable {
Connection cnx = Datasource.getInstance().getCnx();
    @FXML
    private Label id_user_Publier;
//    @FXML
//    private Label libelle_TopPub;
    @FXML
    private Button Button_Publier;
    @FXML
    private TextField text_Publier;
    @FXML
    private TextField text_Publier1;
    @FXML
    private Label id_user_Publier1;
    @FXML
    private Label libelle_TopPub1;
    @FXML
    private Button Button_Publier1;
    @FXML
    private Label libelle_TopPub;
    @FXML
    private Label NB_Reaction;
    @FXML
    private Label Remplir_Labelle;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private AnchorPane Publier_Interface_Pane;
    @FXML
    private AnchorPane AnchorPanePublier;
    @FXML
    private Button Like;
    @FXML
    private Button Dislike;
    @FXML
    private Button BackHome;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int a =getMaxPubIDByNbrReact();
        NB_Reaction.setText("Nombre de réactions: "+a);
//        libelle_TopPub1.setText("Chikaaaaa");
        String s=getTopLibellePub();
        libelle_TopPub1.setText(""+s);
         
        
        
//         ss
        
    
//        {
////            Statement st;
////    try {
////        st = cnx.createStatement();
//////        SELECT colonne1, MAX(colonne2)
//////        FROM table
//////        GROUP BY colonne1
////         String req=("SELECT Libelle_Publication,Nb_Réaction FROM publication GROUP BY Nb_Réaction");
////            ResultSet rs=st.executeQuery(req);
////            
//////            libelle_TopPub1.setText(""+rs.getString(1)+"");
//////            NB_Reaction.setText(""+rs.getInt(2));
////    } catch (SQLException ex) {
////        Logger.getLogger(PublierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
////    }
//           
//           
//        }
        
        
        
//    try {
//        
//        libelle_TopPub1.setText(getMaxPubByNbrReact().getLibelle_Publication());
//    } catch (SQLException ex) {
//        Logger.getLogger(PublierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        
  
    }    
    
    public void addPublicationToBase(Publication p) {
        
        try{
        Statement st = cnx.createStatement();
        String req = "INSERT INTO `publication`(`Libelle_Publication`, `Nb_Réaction`, `Id_User`) VALUES ('"+p.getLibelle_Publication()+"',"+p.getNb_Reaction()+","+p.getU().getId()+")";
        st.executeUpdate(req);
        System.out.println("Publication Added Successfully");
        
        }   catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void validation(){
//    if(text_Publier.getText()!=""){
//        Alert fail= new Alert(Alert.AlertType.INFORMATION);
//        fail.setHeaderText("failure");
//        fail.setContentText("you havent typed something");
//        fail.showAndWait();
//    } 
//    }
//    public boolean verifEmpty(){
//    return (text_Publier.getText().trim().isEmpty());
//    }
    @FXML
    private void AddPublication(ActionEvent event) {
                      
//    validation();
       Boolean ok=false;
        if ((text_Publier1.getText().toString().compareTo("") == 0) ) {
         ok=true;
            JOptionPane.showMessageDialog(null, "champs vide");
        }
        else {
        String libelle=text_Publier1.getText();
        Publication p =new Publication();
        p.setLibelle_Publication(libelle);
        p.setNb_Reaction(0);
        p.setU(new User(35));
//GetUserById() nedded here !!! integration ..
        addPublicationToBase(p);
        text_Publier1.setText("");
        Remplir_Labelle.setText("Publication Ajoutée !");
        }
        
    
    }
    
    public int getMaxPubIDByNbrReact()
    {    
        int NbrMax=0;
            Statement st;
            
            
    
    try {
        st = cnx.createStatement();
        //        SELECT colonne1, MAX(colonne2)
//        FROM table
//        GROUP BY colonne1

         String req=("SELECT Libelle_Publication,Nb_Réaction FROM publication GROUP BY Nb_Réaction ORDER BY Nb_Réaction ASC");
                          ResultSet rs=st.executeQuery(req);
                          while(rs.next())
                          {
                              
                              NbrMax=rs.getInt("Nb_Réaction");
                          }
        
    } catch (SQLException ex) {
        Logger.getLogger(PublierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return NbrMax;
    }
    
    public String getTopLibellePub()
    {    
        String TopLibelle="";
            Statement st;
            
            
    
    try {
        st = cnx.createStatement();
        //        SELECT colonne1, MAX(colonne2)
//        FROM table
//        GROUP BY colonne1

         String req=("SELECT Libelle_Publication,Nb_Réaction FROM publication GROUP BY Nb_Réaction ORDER BY Nb_Réaction ASC");
                          ResultSet rs=st.executeQuery(req);
                          while(rs.next())
                          {
                              
                              TopLibelle=rs.getString("Libelle_Publication");
                          }
        
    } catch (SQLException ex) {
        Logger.getLogger(PublierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return TopLibelle;
    }
    
   
       
//       public String monday(List<Publication> l)
//       {
//           List<Publication> Lp =getListPubs_Publier();
//           System.out.println("Chikaaaaaaaaaaaaaaaaaaaaaaa");
//           return ""; 
//       }

    @FXML
    private void Verif(MouseEvent event) {
//        if(verifEmpty()){
//            Remplir_Labelle.setText("Publication incorrecte !!");}
//            else{
//            Remplir_Labelle.setText("Publication ajoutée !");
//            // Reload Page impossible either .
////            exit();
//                    }
        }

    @FXML
    private void GetPanePubEmty(MouseEvent event) {
        Remplir_Labelle.setText("");
    }
    
    public void modifierPublication(Publication p) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("UPDATE publication SET Libelle_Publication=?,Nb_Réaction=? where `Id_Publication` = ?");
            pt.setString(1, p.getLibelle_Publication());
            pt.setInt(2,p.getNb_Reaction());           
            pt.setInt(3,p.getId_Publication());
            pt.executeUpdate();
            System.err.println("publication Updated Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    
  public List<Publication> getListPubs_TopPublisher(String a) { 
    List<Publication> LP =new ArrayList<Publication>();
            
        try {
            Statement st = cnx.createStatement();
            String req=("SELECT * FROM `publication` ORDER BY `publication`.`Nb_Réaction` DESC");
            ResultSet rs=st.executeQuery(req);
            
                Publication p1=new Publication();
//               rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4);
                p1.setId_Publication(rs.getInt(1));
                p1.setLibelle_Publication(rs.getString(2));
                p1.setNb_Reaction(rs.getInt(3));
                LP.add(p1);  
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(LP);
             return LP;
}

    @FXML
    private void like_Reaction(ActionEvent event) {
        
        
        
//        List<Publication> L=getListPubs_TopPublisher("Chikkkaaa FTW");
//        Publication p=new Publication();
//        p.setId_Publication(L.get(0).getId_Publication());
//        p.setNb_Reaction(L.get(0).getNb_Reaction()+1);  
//        p.setLibelle_Publication(L.get(0).getLibelle_Publication());
////        modifierPublication(p);
//        Like.setDisable(true);
//        
//        NB_Reaction.setText(""+getMaxPubIDByNbrReact());
////        libelle_TopPub1.setText("Chikaaaaa");
//        String s=getTopLibellePub();
//        libelle_TopPub1.setText(""+s);
        
    }

    @FXML
    private void Dislike_Reaction(ActionEvent event) {
        
        
        
         Dislike.setDisable(true);
    }

    @FXML
    private void goToEspaceForum(ActionEvent event) throws IOException {
    
       AnchorPanePublier.getChildren().clear();
        AnchorPanePublier.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/GUI/ForumInterface.fxml")));
    }
    
    public Boolean checkTextField()
    { Boolean ok=false;
     if ((text_Publier1.getText().toString().compareTo("") == 0) ) {
         ok=true;
            JOptionPane.showMessageDialog(null, "champs vide");

} else {  JOptionPane.showMessageDialog(null, "Enregistrement a été  ajouter"); 
            }
        
        return ok;
    }
    }