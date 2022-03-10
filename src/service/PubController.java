/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Util.DB.DataSources;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Publication;
import models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class PubController implements Initializable {
Connection cnx = DataSources.getInstance().getCnx();
    @FXML
    private AnchorPane Pub1Block;
    @FXML
    private Pane PubBlock;
    @FXML
    private Label pubBlockLabel;
    @FXML
    private AnchorPane AnchorPaneBlockPub;
    @FXML
    private ImageView Commentaire;
    @FXML
    private Button Dislike;
    @FXML
    private Button Like;
    @FXML
    private Label labelReactions;
    @FXML
    private Button commenterButton;
    @FXML
    private Label id_id;
    @FXML
    private Label labelReactions_T;
    @FXML
    private Button Like1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
//        PreparedStatement pt;
//        Publication p=new Publication();
//        int id_pub1=Integer.parseInt(id_id.getText());
//        p.setId_Publication(13);
//        int nbr_Reactions_Label=Integer.parseInt(labelReactions.getText());
//        p.setNb_Reaction(3);
//        try {
//            pt = cnx.prepareStatement("UPDATE publication SET Nb_Réaction=? where `Id_Publication` = ?");
//            pt.setString(1, p.getLibelle_Publication());
//            pt.setInt(1,p.getNb_Reaction()+1);           
//            pt.setInt(2,p.getId_Publication());
//            pt.executeUpdate();
//            System.err.println("publication Updated Successfully");
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
//        }

            
    
    }  
    
    public void setBlock(Publication p){
//        +" \n " +p.getId_Publication()
        pubBlockLabel.setText(p.getLibelle_Publication());
//        pubBlockLabel.setText(p.getId_Publication());
        labelReactions.setText(p.getNb_Reaction()+"");
        id_id.setText(p.getId_Publication()+"");
        id_id.setVisible(false);
        labelReactions_T.setText("Réactions : ");
    }
    
    
    public ObservableList<Publication> getListPubsByLibelleFromBase(String s) { 
    ObservableList<Publication> LPL =FXCollections.observableArrayList();
            
        try {
            Statement st = cnx.createStatement();
            String req=("select * from publication where Libelle_Publication like '%"+s+"%'");
            ResultSet rs=st.executeQuery(req);
            while (rs.next()) {
                Publication p1=new Publication();
//               rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4);
                p1.setId_Publication(rs.getInt(1));
                p1.setLibelle_Publication(rs.getString(2));
                p1.setNb_Reaction(rs.getInt(3));
                p1.setU(new User(rs.getInt(4)));
                LPL.add(p1);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(LPL);
             return LPL;
}
public void modifierPublication(Publication p) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("UPDATE publication SET Nb_Réaction=? where `Id_Publication` = 22");
//            pt.setString(1, p.getLibelle_Publication());
            pt.setInt(1,p.getNb_Reaction());           
//            pt.setInt(2,p.getId_Publication());
            pt.executeUpdate();
            System.err.println("publication Updated Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
public void supprimerPublication(int id ) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from publication where Id_Publication="+id);
            pt.executeUpdate();
            System.out.println("publication Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void DislikeButton(ActionEvent event) {
        
        PreparedStatement pt;
        Publication p=new Publication();
        int id_pub1=Integer.parseInt(id_id.getText());
        p.setId_Publication(id_pub1);
        int nbr_Reactions_Label=Integer.parseInt(labelReactions.getText());
        p.setNb_Reaction(nbr_Reactions_Label);
        try {
            pt = cnx.prepareStatement("UPDATE publication SET Nb_Réaction=? where `Id_Publication` = ?");
//            pt.setString(1, p.getLibelle_Publication());
            pt.setInt(1,p.getNb_Reaction()-1);           
            pt.setInt(2,p.getId_Publication());
            pt.executeUpdate();
            System.err.println("publication Updated Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dislike.setDisable(true);
    }
        
    
    @FXML
    private void LikeButton(ActionEvent event) {
        PreparedStatement pt;
        Publication p=new Publication();
        int id_pub1=Integer.parseInt(id_id.getText());
        p.setId_Publication(id_pub1);
        int nbr_Reactions_Label=Integer.parseInt(labelReactions.getText());
        p.setNb_Reaction(nbr_Reactions_Label);
        try {
            pt = cnx.prepareStatement("UPDATE publication SET Nb_Réaction=? where `Id_Publication` = ?");
//            pt.setString(1, p.getLibelle_Publication());
            pt.setInt(1,p.getNb_Reaction()+1);           
            pt.setInt(2,p.getId_Publication());
            pt.executeUpdate();
            System.err.println("publication Updated Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Like.setDisable(true);

           
        
    }

    @FXML
    private void GoTo(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/gui/CommentairesInterface.fxml"));
             Parent root = loader.load();
          
            Scene scene = new Scene(root);
             Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
           public ObservableList<Publication> getListPubs() { 
    ObservableList<Publication> LP =FXCollections.observableArrayList();
            
        try {
            Statement st = cnx.createStatement();
            String req=("select * from publication");
            ResultSet rs=st.executeQuery(req);
            while (rs.next()) {
                Publication p1=new Publication();
//               rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4);
                p1.setId_Publication(rs.getInt(1));
                p1.setLibelle_Publication(rs.getString(2));
                p1.setNb_Reaction(rs.getInt(3));
                p1.setU(new User(rs.getInt(4)));
                LP.add(p1);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(LP);
             return LP;
}  

    @FXML
    private void DeleteButton(ActionEvent event) {
        PreparedStatement pt;
        int id=Integer.parseInt(id_id.getText());
        try {
            pt = cnx.prepareStatement("delete from publication where Id_Publication="+id);
            pt.executeUpdate();
            System.out.println("publication Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
           
}