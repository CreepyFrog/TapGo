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
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
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

public class designOwnerController implements Initializable {
    public Pane context;
//    @FXML
//    private AnchorPane root1;
//    @FXML
//    private Button dtnDashBoard;
    @FXML
//    private AnchorPane root1;
//    @FXML
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
   
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

            //setUi("HomeAdmin");
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
}



