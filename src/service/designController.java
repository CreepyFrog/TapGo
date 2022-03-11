package service;



import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
import models.UserHolder;
//import Controller.java;
//import service.LoginFormController;

public class designController implements Initializable {
    public Pane context;
//    @FXML
//    private AnchorPane root1;
//    @FXML
//    private Button dtnDashBoard;
//    @FXML
//    private AnchorPane root1;
    @FXML
    private Label userlabel;
    private UserHolder ur=UserHolder.getInstance();
    @FXML
    private AnchorPane root1;

//    private void setUi(String location) throws IOException {
//        context.getChildren().clear();
//        context.getChildren().add(FXMLLoader.load(this.getClass().
//                getResource("/gui/" + location + ".fxml")));
//    }
//   
    @FXML
    private Button btnlodout11;
    @FXML
    private Button btnlodoutForum;
    @FXML
    private Button btnlodoutReservation;
   
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//      try {
//            setUi("design");
//            userlabel.setText(ur.getUser().getName());
//        } catch (IOException ex) {
//            Logger.getLogger(designController.class.getName()).log(Level.SEVERE, null, ex);
//        }
userlabel.setText(ur.getUser().getName());
    }
           @FXML
    private Button btnlodout;

           @FXML
    void btnLogOut(ActionEvent event) throws IOException {
      btnlodout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gui/LoginForm.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
         //  rememberMe();
    }
private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    @FXML
    private void btngestionformation(ActionEvent event) throws IOException {
                               setUi("Formations");

    }

    @FXML
    private void btngestionForum(ActionEvent event) throws IOException{
       setUi("ForumInterface"); 
    }

    @FXML
    private void btngestionReservation(ActionEvent event) throws IOException{
        setUi("HomeUser"); 
    }
}



