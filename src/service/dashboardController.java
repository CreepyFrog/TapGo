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
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class dashboardController implements Initializable {
    public Pane context;


    private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
          @FXML
    public void DashBoardOnAction() throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/InscriptionOwner.fxml")));
    }
            @FXML
    public void btnHomeOnAction() throws IOException {

        setUi("HomeAdmin");
    }
    
          @FXML
    void btnMailOnAction(ActionEvent event) throws IOException {
          setUi("Mail");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setUi("HomeAdmin");
        } catch (IOException ex) {
            Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    }
}



