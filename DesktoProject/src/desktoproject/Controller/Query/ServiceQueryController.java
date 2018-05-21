/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Model.Classes.Transactions.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceQueryController implements Initializable {
    @FXML
    TableView<Service> ServiceTable;
    @FXML
    TableColumn<Service,Integer> Code;
    @FXML
    TableColumn<Service,String> Description;
    @FXML
    TableColumn<Service,Float> Price;
    
    ObservableList<Service> services;
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
