/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewMain extends Application {
    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/gui/Table.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("/gui/Reserver.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("/gui/statistics.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/gui/EnvoyerMail.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("/gui/AfficherReservationAdmin.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/gui/AfficherTableAdmin.fxml"));
       
       // Interface Admin
       //  Parent root = FXMLLoader.load(getClass().getResource("/gui/HomeReservation.fxml"));
       // Interface User
        Parent root = FXMLLoader.load(getClass().getResource("/gui/HomeUser.fxml"));
       //   Parent root = FXMLLoader.load(getClass().getResource("/gui/Maps.fxml"));
       primaryStage.initStyle(StageStyle.TRANSPARENT);

        //grab your root here
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        //move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        //set transparent
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}



