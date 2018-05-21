/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class QuerySupplierController implements Initializable {

    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(QuerySupplierController.class.getClassLoader().getResource("desktoproject/View/Query/SupplierQueryController.fxml"));        
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void back(ActionEvent event) {
        
    }
    
    @FXML
    private void createNew() {
        
    }
    
    @FXML
    private void editSupplier() {
        
    }
}
