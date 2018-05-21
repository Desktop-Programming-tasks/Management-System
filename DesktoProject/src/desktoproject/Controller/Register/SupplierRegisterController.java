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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierRegisterController implements Initializable {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField CNPJTextField;
    @FXML
    private TextField telTextField;
    @FXML
    private TextField secTelTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField districtTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button actionBtn;
    @FXML
    private TableView<Brand> brandTable;
    
    @FXML
    private TableColumn<Brand,String> Brands;
    
    @FXML
    private ComboBox<String> City;
    @FXML
    private ComboBox<String> State;
    
    ObservableList<Brand> brands;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @FXML 
    public void register(){
        
    }
 
    @FXML
    public void back() {
        
    }
    
    @FXML
    private void createNewBrand() {
        
    }
    
    @FXML
    private void addBrand() {
        
    }
}
