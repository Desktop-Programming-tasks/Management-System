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
 * @author ecaanchesjr
 */
public class QuerySupplierController implements Initializable {

    private static final String querySupplierPath = "desktoproject/View/Query/QuerySupplier.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(QuerySupplierController.class.getClassLoader().getResource(querySupplierPath));        
        return loader.load();
    }
    
    @FXML
    private TableView Suppliers;
    @FXML
    private TableColumn Cnpj;
    @FXML
    private TableColumn Name;
    @FXML
    private TableColumn Brands;
    @FXML
    private TextField searchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void createNew() {
        
    }
    
    @FXML
    private void editSupplier() {
        
    }
}
