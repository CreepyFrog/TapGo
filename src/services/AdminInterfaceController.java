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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Chikaaa
 */
public class AdminInterfaceController implements Initializable {

    @FXML
    private TableView<Publication> ListPubs2;
    @FXML
    private TableColumn<Publication, String> libelle_Pubs;
    @FXML
    private TableColumn<Publication, Integer> Nbr_Reaction;
    @FXML
    private TextField SearchString;
    @FXML
    private Button Button_Delete_Search;
    @FXML
    private Button Button_Search;
    Connection cnx = Datasource.getInstance().getCnx();
    @FXML
    private TableColumn<Commentaire, String> lib_comm_Interface;
    @FXML
    private TableView<Commentaire> ListCommentaire_Interface;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ListPubs2.getSelectionModel().setCellSelectionEnabled(true);
        ListPubs2.setEditable(true);
        libelle_Pubs.setCellFactory(TextFieldTableCell.forTableColumn());
        getAllPublications();
        addButtonDeleteToTable();
//        addButtonUpdateToTable();
//        addButtonReactToTable();
//        addButtonDislikeToTable();
        addButtonGoToCommentaireToTable(); 
//        getAllCommentaires();
        
        // TODO
    }    

    @FXML
    private void DeleteTextFromTextField(ActionEvent event) {
        SearchString.setText("");
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
public void getAllListPubsByLibelleToTableView(){
         
        ObservableList<Publication> LPL =getListPubsByLibelleFromBase(SearchString.getText());
//        idPubs.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Id_Publication"));
//        libelle.setCellValueFactory(new PropertyValueFactory<Publication,String>("Libelle_Publication"));
//        reaction.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Nb_Réaction"));
//        user.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Id_User"));
//        tableView.setItems(LP);
//        id.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Id_Publication"));
        libelle_Pubs.setCellValueFactory(new PropertyValueFactory<Publication,String>("Libelle_Publication"));
        Nbr_Reaction.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Nb_Reaction"));
//        userId.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("u"));
        ListPubs2.setItems(LPL);
        System.out.println("Chikaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //PropertyValueFactory<Publication,User> pvid=new PropertyValueFactory<Publication,User>("u");
         }
    @FXML
    private void FindByLibelle(ActionEvent event) {
        getAllListPubsByLibelleToTableView();
        ObservableList<Publication> LP =getListPubsByLibelleFromBase(SearchString.getText());
        libelle_Pubs.setCellValueFactory(new PropertyValueFactory<Publication,String>("Libelle_Publication"));
        Nbr_Reaction.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Nb_Reaction"));
        ListPubs2.setItems(LP);
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
//        id.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Id_Publication"));
        libelle_Pubs.setCellValueFactory(new PropertyValueFactory<Publication,String>("Libelle_Publication"));
        Nbr_Reaction.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Nb_Reaction"));
//        userId.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("u"));
        ListPubs2.setItems(LP);
        System.out.println("Chikaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //PropertyValueFactory<Publication,User> pvid=new PropertyValueFactory<Publication,User>("u");
        } 
    public ObservableList<Commentaire> getAllCommsFromDatabase(int a) {
        ObservableList<Commentaire> LC=FXCollections.observableArrayList();
         try {
             Statement st = cnx.createStatement();
             String req=("select * from commentaire where id_publication="+a);
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
        
        ObservableList<Commentaire> LC =getAllCommsFromDatabase(1);
        System.out.println(LC);
        lib_comm_Interface.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("libelle_commentaire"));
        ListCommentaire_Interface.setItems(LC);
        System.out.println("Chikaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
    
    private void addButtonDeleteToTable() {
        TableColumn<Publication, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>> cellFactory = new Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>>() {
            @Override
            public TableCell<Publication, Void> call(final TableColumn<Publication, Void> param) {
                final TableCell<Publication, Void> cell = new TableCell<Publication, Void>() {

                    private final Button btn = new Button("X");
//                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Publication p1 = ListPubs2.getItems().get(getIndex());
                            System.out.println("selectedData: " + p1);
                            supprimerPublication(p1.getId_Publication());
                            getAllPublications();
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        ListPubs2.getColumns().add(colBtn);

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

    private void addButtonUpdateToTable() {
        TableColumn<Publication, Void> colBtnUpdate = new TableColumn("");

        Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>> cellFactory = new Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>>() {
            @Override
            public TableCell<Publication, Void> call(final TableColumn<Publication, Void> param) {
                final TableCell<Publication, Void> cell = new TableCell<Publication, Void>() {

                    private final Button btnUpdate = new Button("Edit");
//                    

                    {
                        btnUpdate.setOnAction((ActionEvent event) -> {
                            Publication p1 = ListPubs2.getItems().get(getIndex());
                            System.out.println("selectedData: " + p1);
                            
                            modifierPublication(p1);
                            getAllPublications();
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnUpdate);
                        }
                    }
                };
                return cell;
            }
        };
        colBtnUpdate.setCellFactory(cellFactory);
        ListPubs2.getColumns().add(colBtnUpdate);
    }
    
    private void addButtonReactToTable() {
        TableColumn<Publication, Void> colBtnReact = new TableColumn("Like");

        Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>> cellFactory = new Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>>() {
            @Override
            public TableCell<Publication, Void> call(final TableColumn<Publication, Void> param) {
                final TableCell<Publication, Void> cell = new TableCell<Publication, Void>() {

                    private final Button btnReact = new Button("+1");
//                    

                    {
                        btnReact.setOnAction((ActionEvent event) -> {
                            Publication p1 = ListPubs2.getItems().get(getIndex());
                            System.out.println("selectedData: " + p1);
                            p1.setNb_Reaction(p1.getNb_Reaction()+1);
                            modifierPublication(p1);
                            getAllPublications();
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnReact);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnReact.setCellFactory(cellFactory);

        ListPubs2.getColumns().add(colBtnReact);

    }
    
    private void addButtonDislikeToTable() {
        TableColumn<Publication, Void> colBtnDislike = new TableColumn("Dislike");

        Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>> cellFactory = new Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>>() {
            @Override
            public TableCell<Publication, Void> call(final TableColumn<Publication, Void> param) {
                final TableCell<Publication, Void> cell = new TableCell<Publication, Void>() {

                    private final Button btnDislike = new Button("-1");
//                    

                    {
                        btnDislike.setOnAction((ActionEvent event) -> {
                            Publication p1 = ListPubs2.getItems().get(getIndex());
                            System.out.println("selectedData: " + p1);
                            p1.setNb_Reaction(p1.getNb_Reaction()-1);
                            modifierPublication(p1);
                            getAllPublications();
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnDislike);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnDislike.setCellFactory(cellFactory);

        ListPubs2.getColumns().add(colBtnDislike);

    }
    
    private void addButtonGoToCommentaireToTable() {
        TableColumn<Publication, Void> colBtnComm = new TableColumn("Commentaire");

        Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>> cellFactory = new Callback<TableColumn<Publication, Void>, TableCell<Publication, Void>>() {
            @Override
            public TableCell<Publication, Void> call(final TableColumn<Publication, Void> param) {
                final TableCell<Publication, Void> cell = new TableCell<Publication, Void>() {

                    private final Button btnComm = new Button("Commentaires");
//                    

                    {
                        btnComm.setOnAction((ActionEvent event) -> {
                            Publication p1 = ListPubs2.getItems().get(getIndex());
                            System.out.println("selectedData: " + p1);
                            p1.setNb_Reaction(p1.getNb_Reaction()-1);
                            int a=p1.getId_Publication();
                            
                            ObservableList<Commentaire> LC =getAllCommsFromDatabase(a);
                            System.out.println(LC);
                            lib_comm_Interface.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("libelle_commentaire"));
                            ListCommentaire_Interface.setItems(LC);
//                            getAllCommentaires(a);
                            
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnComm);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnComm.setCellFactory(cellFactory);

        ListPubs2.getColumns().add(colBtnComm);

    }
    
//    public void setListPubs2()
//    { 
//        
//       
////                       
//// 
////        TableColumn firstNameCol = new TableColumn("First Name");
////        firstNameCol.setMinWidth(100);
////        libelle_Pubs.setCellValueFactory(new PropertyValueFactory<Publication,String>("Libelle_Publication"));
////        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
////        libelle_Pubs.setOnEditCommit(
////            new EventHandler<CellEditEvent<Publication, String>>() {
////                @Override
////                public void handle(CellEditEvent<Publication, String> t) {
////                    ((Publication) t.getTableView().getItems().get(
////                            t.getTablePosition().getRow())
////                            ).setLibelle_Publication(t.getNewValue());
////                }
////            }
////        );
//// 
//// 
////        
////        
////        Nbr_Reaction.setCellValueFactory(new PropertyValueFactory<Publication,Integer>("Nb_Reaction"));
////        Nbr_Reaction.setOnEditCommit(
////            new EventHandler<CellEditEvent<Publication, Integer>>() {
////                @Override
////                public void handle(CellEditEvent<Publication, Integer> t) {
////                    ((Publication) t.getTableView().getItems().get(
////                        t.getTablePosition().getRow())
////                        ).setNb_Reaction(t.getNewValue());
////                }
////            }
////        );
//    }

    //Find Section

}
