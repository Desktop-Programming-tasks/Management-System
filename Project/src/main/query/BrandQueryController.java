/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.query;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.GUIController;
import main.objects.transations.Brand;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class BrandQueryController implements Initializable {
    @FXML
    TableView TableBrand;
    @FXML
    TableColumn<Brand,String> Name;
    
    ObservableList<Brand> brands;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        brands =FXCollections.observableArrayList();
        Brand b= new Brand(1, "Nvidia");
        brands.add(b);
        Brand b1= new Brand(1, "AMD");
        brands.add(b1);
        Brand b2= new Brand(1, "Intel");
        brands.add(b2);
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableBrand.setItems(brands);
        // TODO
    }    
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
}
