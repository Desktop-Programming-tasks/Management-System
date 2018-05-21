/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Register;

import desktoproject.Model.Classes.Transactions.Brand;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class ProductRegisterController implements Initializable {

    @FXML
    private Button actionBtn;
    @FXML 
    private Label mainLabel;
    @FXML
    private TextField barCodeTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Group quantityGroup;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    
    @FXML
    private TableView<Brand> TableBrands;
    
    @FXML
    private TableColumn<Brand,String> Brands;
    
    ObservableList<Brand> brands;
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
    private void register(){
        
    }
    
    @FXML
    private void createNewBrand() {
        
    }
    
    @FXML
    private void addBrand() {
        
    }
}
