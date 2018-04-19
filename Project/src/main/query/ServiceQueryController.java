/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.query;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.objects.transations.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.GUIController;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceQueryController implements Initializable {
    @FXML
    TableView ServiceTable;
    @FXML
    TableColumn<Service,Integer> Code;
    @FXML
    TableColumn<Service,String> Description;
    @FXML
    TableColumn<Service,Float> Price;
    
    ObservableList<Service> services;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Dummies
        Service s1 = new Service("Batata", 0);
        s1.setPrice(300);
        services.add(s1);
        //Dummies end
        Code.setCellValueFactory(new PropertyValueFactory<>("codServ"));
        Description.setCellValueFactory(new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ServiceTable.setItems(services);
    }    
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
}