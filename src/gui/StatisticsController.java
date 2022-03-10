/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ServiceReservation;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatisticsController implements Initializable {

    @FXML
    private BarChart<?, ?> rt;
    @FXML
    private NumberAxis x;
    @FXML
    private CategoryAxis y;
    @FXML
    private Button back;
    @FXML
    private AnchorPane AnchorPaneStat;

    /**
     * Initializes the controller class.
     */
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceReservation o = new ServiceReservation();
       
        BarChart.Series set1 = new BarChart.Series<>();
        
       
        set1.getData().add(new BarChart.Data("le 22 Mars",o.getNbtu()));
        set1.getData().add(new BarChart.Data("le 21 Mars",o.getNbit()));
        set1.getData().add(new BarChart.Data("le 23 Mars",o.getNbch()));
        
        rt.getData().addAll(set1);
        // TODO
    }    
    
    
    
               private void setInterface(String location) throws IOException {
        AnchorPaneStat.getChildren().clear();
        AnchorPaneStat.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/gui/" + location + ".fxml")));
    }
    
    
               

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        setInterface("HomeReservation");
    }
    
}
