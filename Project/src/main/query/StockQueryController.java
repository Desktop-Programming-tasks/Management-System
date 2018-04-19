/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.query;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.GUIController;
import main.objects.persons.Brand;
import main.objects.transations.Product;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class StockQueryController implements Initializable {
    @FXML
    TableView StockTable;
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
        // TODO
        products=FXCollections.observableArrayList();
        
        Product p1= new Product("111", 3, new Brand(1,"Nvidia"), 3, "Placa de video");
        products.add(p1);
        Product p2= new Product("222", 5, new Brand(2,"Asus"), 100, "Placa mãe");
        products.add(p2);
        
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));
        BarCode.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        StockTable.setItems(products);
    }   
        
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
}
