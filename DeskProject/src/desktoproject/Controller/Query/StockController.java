/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class StockController implements Initializable {
    
    private static final String stockControllerPath = "desktoproject/View/Query/StockQueryController.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StockController.class.getClassLoader().getResource(stockControllerPath));        
        return loader.load();
    }
    
    @FXML
    TableView StockTable;
    @FXML
    TableColumn Name;
    @FXML
    TableColumn Brand;
    @FXML
    TableColumn Quantity;
    @FXML
    TableColumn BarCode;
    
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
    private void detailsProduct(){
        
    }
}
