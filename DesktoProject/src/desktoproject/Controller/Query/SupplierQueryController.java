/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Model.Classes.Persons.Supplier;
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
 * @author ecaanchesjr
 */
public class SupplierQueryController implements Initializable {

    @FXML
    private TableView<Supplier> Suppliers;
    @FXML
    private TableColumn<Supplier, String> Cnpj;
    @FXML
    private TableColumn<Supplier, String> Name;
    @FXML
    private TableColumn<Supplier,String> Brands;
    ObservableList<Supplier> suppliers;

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
