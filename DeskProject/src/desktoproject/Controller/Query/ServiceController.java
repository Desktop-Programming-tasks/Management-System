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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceController implements Initializable {
    
    private static final String serviceControllerPath = "desktoproject/View/Query/Service.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServiceController.class.getClassLoader().getResource(serviceControllerPath));        
        return loader.load();
    }
    
    @FXML
    private TableView ServiceTable;
    @FXML
    private TableColumn codeColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TextField searchTextField;
    
    
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
