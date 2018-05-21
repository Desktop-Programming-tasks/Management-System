/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceQueryController implements Initializable {
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServiceQueryController.class.getClassLoader().getResource("desktoproject/View/Query/ServiceQueryController.fxml"));        
        return loader.load();
    }
    
    @FXML
    TableView ServiceTable;
    @FXML
    TableColumn Code;
    @FXML
    TableColumn Description;
    @FXML
    TableColumn Price;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void back() {
        
    }
    
    @FXML
    public void showModalUpdateService() {
        
    }    
    @FXML
    private void detailsProduct(){
        
    }
}
