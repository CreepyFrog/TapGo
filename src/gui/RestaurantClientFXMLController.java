/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Util.DB.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Restaurant;

/**
 * FXML Controller class
 *
 * @author YOUSSEF RIAHI
 */
public class RestaurantClientFXMLController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneRest;
    @FXML
    private AnchorPane anchorPanePubBlock;
    @FXML
    private GridPane gridPaneRest;
 Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Button menus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            insertPublication();
        } catch (IOException ex) {
            Logger.getLogger(RestaurantClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public ObservableList<Restaurant> getListRest() throws SQLException { 
    ObservableList<Restaurant> LP =FXCollections.observableArrayList();
            
        
            Statement st = cnx.createStatement();
            String req=("select * from restaurant");
            ResultSet rs=st.executeQuery(req);
            while (rs.next()) {
                Restaurant r1=new Restaurant();
//               rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4);
                r1.setNom(rs.getString(2));
                r1.setAdresse(rs.getString(3));
                r1.setDomaine(rs.getString(4));
//                p1.setLibelle_Publication(rs.getString(2));
//                p1.setNb_Reaction(rs.getInt(3));
//                p1.setU(new User(rs.getInt(4)));
                LP.add(r1);  
            }
       
        System.out.println(LP);
             return LP;
}  
    
    public void insertPublication() throws IOException, SQLException
    {
        ObservableList<Restaurant> LC=getListRest();
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
                Loader1.setLocation(getClass().getResource("/gui/RestFXML.fxml"));
                AnchorPane anchorPane = Loader1.load();
//                anchorPane.setMinHeight(50);
                RestFXMLController pController = Loader1.getController();
                pController.setBlock(LC.get(i));
                gridPaneRest.add(anchorPane, 0, i);
                gridPaneRest.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPaneRest.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPaneRest.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                gridPaneRest.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPaneRest.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPaneRest.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
        }}

    @FXML
    private void menuOnAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/MenuClientFXML.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
    }

    

    
}
