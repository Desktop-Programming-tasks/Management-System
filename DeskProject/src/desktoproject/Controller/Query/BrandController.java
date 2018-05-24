/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Model.Classes.Transactions.Brand;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author noda
 */
public class BrandController implements Initializable {
    
    private static final String brandControllerPath = "desktoproject/View/Query/Brand.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BrandController.class.getClassLoader().getResource(brandControllerPath));
        return loader.load();
    }
    
    @FXML
    TableView<Brand> TableBrand;
    @FXML
    TableColumn<Brand,String> Name;
    
    ObservableList<Brand> brands;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void createNewBrand() {
        
    }
    
    @FXML
    public void back() {
        
    }
}
