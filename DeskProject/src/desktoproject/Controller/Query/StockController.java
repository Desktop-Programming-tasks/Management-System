/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Controller.GUIController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class StockController implements Initializable {
    
    private static final String stockPath = "desktoproject/View/Query/Stock.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StockController.class.getClassLoader().getResource(stockPath));        
        return loader.load();
    }
    
    @FXML
    private TableView StockTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn brandColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn codeColumn;
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
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void detailsProduct(){
        
    }
}
