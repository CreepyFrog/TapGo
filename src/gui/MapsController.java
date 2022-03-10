/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapReadyListener;
import com.lynden.gmapsfx.MapNotInitializedException;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class MapsController implements Initializable {

    @FXML
    private TextField addressTextField;
    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;
    
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    
    
    
    
    



    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
             // mapView.addMapInializedListener(this);
              
        address.bind(addressTextField.textProperty());
        
        // TODO
    }    
    

    
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(47.6097, 122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        
    }
    
    
*/
    
    
    
    
    
     String localisation;
    
       
   Stage stage1;
    FXMLLoader loader;
      public void showMap(Stage stage) {
       
            // create web engine and view
         WebEngine webEngine = new WebEngine(
                getClass().getResource("/tn.esprit.utils/googleMap.html").toString());
         WebView webView = new WebView();
         webView.getEngine().load("file:///C:\\xampp\\htdocs\\tapgo\\src\\tn\\esprit\\utils/googleMap.html");
        // create map type buttons
               
             stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(webView));
            stage.show();
        
        final ToggleGroup mapTypeGroup = new ToggleGroup();
        final ToggleButton road = new ToggleButton("Road");
        road.setSelected(true);
        road.setToggleGroup(mapTypeGroup);
        final ToggleButton satellite = new ToggleButton("Retour");
        
        satellite.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                stage1 = new Stage(StageStyle.UTILITY);
                   loader= new FXMLLoader(getClass().getResource("Evenement.fxml"));
                    loader.setController(new MapsController());
                 try {
                     stage1.setScene(new Scene(loader.load()));
                 } catch (IOException ex) {
                     Logger.getLogger(MapsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            stage1.show();
                 
             }
         });
    
        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>(){
             @Override
             public void handle(WindowEvent event) {
                 System.out.println("close");
                 String adresse =webView.getEngine().executeScript("fullName").toString();
                 
                 
                 
                 
                 
             }
            
        });
       
        
     
        

   }
    
    
    
}
