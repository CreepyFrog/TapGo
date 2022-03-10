/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextField;
import service.ServiceRestaurant;

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
    private TextField xd;
    @FXML
    private TextField fd;

    /**
     * Initializes the controller class.
     */
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceRestaurant o = new ServiceRestaurant();
       
        BarChart.Series set1 = new BarChart.Series<>();
        
       
        set1.getData().add(new BarChart.Data("tunisian",o.getNbtu()));
        set1.getData().add(new BarChart.Data("italien",o.getNbit()));
        set1.getData().add(new BarChart.Data("chinoix",o.getNbch()));
        
        rt.getData().addAll(set1);
        // TODO
    }    
    
}
