/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class AddProductController implements Initializable {
    
    private static final String addProductPath = "desktoproject/View/Modal/AddProduct.fxml";
    
    public static Parent call() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddProductController.class.getClassLoader().getResource(addProductPath));
            return loader.load();
    }
    
    @FXML
    private TextField ProductName;
    @FXML
    private TextField ProductPrice;
    @FXML
    private TextField ProductQuantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    @FXML
    public void addProduct() {
        
    }

    @FXML
    public void back() {
        
    }
}
