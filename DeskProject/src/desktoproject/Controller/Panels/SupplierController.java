/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Register;

import desktoproject.Controller.Query.ServiceQueryController;
import desktoproject.Model.Classes.Transactions.Brand;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierRegisterController implements Initializable {
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SupplierRegisterController.class.getClassLoader().getResource("desktoproject/View/Register/SupplierRegister.fxml"));        
        return loader.load();
    }
    
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
    private TableView brandTable;
    
    @FXML
    private TableColumn Brands;
    
    @FXML
    private ComboBox<String> City;
    @FXML
    private ComboBox<String> State;

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
