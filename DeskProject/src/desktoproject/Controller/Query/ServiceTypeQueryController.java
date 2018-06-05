/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Controller.GUIController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author viniciusmn
 */
public class ServiceTypeQueryController implements Initializable {

    private static final String PATH = "desktoproject/View/Query/ServiceTypeQuery.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServiceController.class.getClassLoader().getResource(PATH));        
        return loader.load();
    }
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView ServiceTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TextField searchTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    
    
    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void newServiceType(){
    
    }
    
    @FXML
    private void editServiceType(){
    
    }
}
