/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Data.Datasource;
import entities.Commentaire;
import entities.Publication;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class CommentairesController implements Initializable {
 Connection cnx = Datasource.getInstance().getCnx();
    @FXML
    private TableColumn<Commentaire, String> lib_comm_Interface;
    @FXML
    private TableView<Commentaire> ListCommentaire_Interface;
    @FXML
    private TextField text_Commenter;
    @FXML
    private Button Button_Commenter;
    @FXML
    private Label AddedLabel;
    private GridPane gridPane;
    @FXML
    private AnchorPane anchorPaneCommentairesBlock;
    @FXML
    private AnchorPane anchorPaneCommentaireInterface;
    @FXML
    private Button BackHome;
    @FXML
    private GridPane gridPaneComms;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllCommentaires();
     try {
         insertCommentaires();
     } catch (IOException ex) {
         Logger.getLogger(CommentairesController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    

    @FXML
    private void goToEspaceForum(ActionEvent event) throws IOException {
        anchorPaneCommentaireInterface.getChildren().clear();
        anchorPaneCommentaireInterface.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/GUI/ForumInterface.fxml")));
    }
    
    
    public void insertCommentaires() throws IOException
    {
        ObservableList<Commentaire> LC=getAllCommsFromDatabase();
        for (int i=0;i<LC.size();i++)
        {
//            String nameLabel="t"+i;
//            Label l=new Label();
//            l.setText(LC.get(i).getLibelle_commentaire());
//            gridPane.add(l, 0, i);
//            FXMLLoader fxmlLoader = new FXMLLoader();
            
//            CommfxmlController commController = fxmlLoader.getController();
//            fxmlLoader.setLocation(getClass().getResource("/GUI/CommentaireInterface.fxml"));  
//            commController.setBlock(LC.get(i));
//            anchorPaneCommentairesBlock.getChildren().add(FXMLLoader.load(this.getClass().
//                getResource("/GUI/CommentaireFXML.fxml")));

                FXMLLoader Loader1 = new FXMLLoader();
                Loader1.setLocation(getClass().getResource("/GUI/CommentaireFXML.fxml"));
                AnchorPane anchorPane = Loader1.load();
//                anchorPane.setMinHeight(50);
                CommfxmlController itemController = Loader1.getController();
                itemController.setBlock(LC.get(i));
                gridPaneComms.add(anchorPane, 0, i);
                gridPaneComms.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPaneComms.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPaneComms.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                gridPaneComms.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPaneComms.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPaneComms.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            
            
            
            
////            FXMLLoader fxmlLoader = new FXMLLoader();
////            fxmlLoader.setLocation(getClass().getResource("/GUI/CommentaireInterface.fxml"));    
//                CommfxmlController commController = fxmlLoader.getController();
//                commController.setBlock(LC.get(i));
////                anchorPaneCommentairesBlock.getChildren()
                        
                
                
            
        }
        
       
        
    }
    
    
//    public ObservableList<Commentaire> getListComms() {
//        ObservableList<Commentaire> LC=FXCollections.observableArrayList();
//         try {
//             Statement st = cnx.createStatement();
//             String req="select libelle_commentaire from commentaire";
//             ResultSet rs=st.executeQuery(req);
//             while (rs.next()) {
//                 Commentaire c1= new Commentaire();
////                c1.setId_Commentaire(rs.getInt(1));
//                 c1.setLibelle_commentaire(rs.getString(1));
////                c1.setU(new User(rs.getInt(3)));                
////                c1.setP(new Publication(rs.getInt(4)));  
//            LC.add(c1);
//                }    
//         } catch (SQLException ex) {
//             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
//         }
//         System.out.println(LC);
//        return LC;
//    }
public ObservableList<Commentaire> getAllCommsFromDatabase() {
        ObservableList<Commentaire> LC=FXCollections.observableArrayList();
         try {
             Statement st = cnx.createStatement();
             String req="select * from commentaire";
             ResultSet rs=st.executeQuery(req);
             while (rs.next()) {
                 Commentaire c1= new Commentaire();
                 c1.setId_Commentaire(rs.getInt(1));
                 c1.setLibelle_commentaire(rs.getString(2));
                 c1.setU(new User(rs.getInt(3)));
                 c1.setP(new Publication(rs.getInt(4)));  
            LC.add(c1);
                }    
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return LC;
    }

public void getAllCommentaires(){
        ObservableList<Commentaire> LC =getAllCommsFromDatabase();
        System.out.println(LC);
        lib_comm_Interface.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("libelle_commentaire"));
        ListCommentaire_Interface.setItems(LC);
        System.out.println("Chikaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
        } 


    public void addCommentaireToBase(Commentaire c) {
         try {
             Statement st = cnx.createStatement();
             String req = "INSERT INTO `commentaire` (`libelle_commentaire`, `id_user`, `id_publication`) VALUES ('"+c.getLibelle_commentaire()+"',"+c.getU().getId()+","+c.getP().getId_Publication()+")";
        st.executeUpdate(req);
        System.out.println("Commmentaire Added Successfully");
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }


   

    @FXML
    private void Verif(MouseEvent event) {
    }

    @FXML
    private void AddComment(ActionEvent event) throws IOException {
       
                 Boolean ok=false;
     if ((text_Commenter.getText().toString().compareTo("") == 0) ) {
         ok=true;
            JOptionPane.showMessageDialog(null, "champs vide");

} else {
        String libelle=text_Commenter.getText();
        Commentaire c =new Commentaire();
        c.setLibelle_commentaire(libelle);
        c.setP(new Publication(3));
        System.out.println("Lahné");
        c.setU(new User(35));//GetUserById() nedded here !!! integration ..
        addCommentaireToBase(c);
        text_Commenter.setText("");
        AddedLabel.setText("Commentaire Ajoutée !");
        getAllCommentaires();
        gridPaneComms.getChildren().clear();
        insertCommentaires();
        
        
        
    }
                }

    
    

    
    
    
    
}
