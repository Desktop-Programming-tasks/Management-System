/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Controller.Query.ServiceController;
import desktoproject.Model.Classes.Transactions.Brand;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class ProductController implements Initializable {

    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProductController.class.getClassLoader().getResource("desktoproject/View/Register/ProductRegisterController.fxml"));        
        return loader.load();
    }
    
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
    private TableView TableBrands;
    
    @FXML
    private TableColumn Brands;
    
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
