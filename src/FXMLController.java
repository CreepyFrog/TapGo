/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.Datasource;
import entities.Publication;
import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.PublicationService;
import services.ReactionService;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class FXMLController implements Initializable {
PublicationService rs;
    
    @FXML
    private Button Pubs;
//    @FXML
//    private ListView<Publication> ListViewPubs;
    
//    @FXML
//    private AnchorPane Ex;
    @FXML
    private Label label;
    
   Connection cnx = Datasource.getInstance().getCnx();
    @FXML
    private Button Refresh;
    @FXML
    private Button del;
    @FXML
    private AnchorPane EspaceForum;
    @FXML
    private TableView<Publication> ListPubs2;
    @FXML
    private TableColumn<Publication, Integer> id;
    @FXML
    private TableColumn<Publication, String> libelle_Pubs;
    @FXML
    private TableColumn<Publication, Integer> Nbr_Reaction;
    @FXML
    private TableColumn<Publication, Integer> userId;
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
        getAllPublications();
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
 public void getAllPublications(){
        ObservableList<Publication> LP =getListPubs();
//        idPubs.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Id_Publication"));
//        libelle.setCellValueFactory(new PropertyValueFactory<Publication,String>("Libelle_Publication"));
//        reaction.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Nb_Réaction"));
//        user.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Id_User"));
//        tableView.setItems(LP);
        id.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Id_Publication"));
        libelle_Pubs.setCellValueFactory(new PropertyValueFactory<Publication,String>("Libelle_Publication"));
        Nbr_Reaction.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Nb_Reaction"));
        userId.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("u"));
        ListPubs2.setItems(LP);
        System.out.println("Chikaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //PropertyValueFactory<Publication,User> pvid=new PropertyValueFactory<Publication,User>("u");
        }  
 
     @FXML
    public void handlerAfficher(ActionEvent event){
       getAllPublications();
//System.out.println(rs.afficherPublication());
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
public void supprmierByIntegerPubication(int x)
    {
     PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from reaction where Id_Publication="+x);
            pt.executeUpdate();
            System.out.println("reaction id publication "+x+" Deleted Successfully");

        } catch (SQLException ex) {
            Logger.getLogger(ReactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Delete(ActionEvent event) {
//        ListPubs2.setEditable(true);
        supprmierByIntegerPubication(ListPubs2.getSelectionModel().getSelectedItem().getId_Publication());
        supprimerPublication(ListPubs2.getSelectionModel().getSelectedItem().getId_Publication());
        ListPubs2.getItems().removeAll(ListPubs2.getSelectionModel().getSelectedItem()); 
        
        getAllPublications();
//       System.out.println(rs.afficherPublication());
    }
    @FXML
    private void handlerPubs(ActionEvent event) {
       
        System.out.println("I See you !");
        label.setText("Chikaaa");
//        ObservableList<Publication> l =FXCollections.observableArrayList();
//        Publication p=new Publication("Chikaaa",56,new User(36));
//        rs.addPublication(p);
//        l.add(p);
//        tableView.;      
//        ObservableList<Publication> ListPubs = FXCollections.observableArrayList();
//        ListPubs.add(rs.afficherPublication()));

   
        
        
//        listView1.getItems().add(rs.afficherPublication());
    }

}
