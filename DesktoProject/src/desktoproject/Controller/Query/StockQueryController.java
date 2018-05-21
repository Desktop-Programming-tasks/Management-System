/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Model.Classes.Transactions.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class StockQueryController implements Initializable {
    @FXML
    TableView<Product> StockTable;
    @FXML
    TableColumn<Product,String> Name;
    @FXML
    TableColumn<Product,String> Brand;
    @FXML
    TableColumn<Product,Integer> Quantity;
    @FXML
    TableColumn<Product,String> BarCode;
    
    ObservableList<Product> products;
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
