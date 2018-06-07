/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Controller.GUIController;
import desktoproject.Utils.Animation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceController implements Initializable {
    
    private static final String PATH = "desktoproject/View/Query/Service.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServiceController.class.getClassLoader().getResource(PATH));        
        return loader.load();
    }
    
    @FXML
    private TableView ServiceTable;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button backBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(updateBtn);
        Animation.bindShadowAnimation(cancelBtn);
        Animation.bindShadowAnimation(backBtn);
        
        Animation.bindAnimation(searchTextField);
        ServiceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    public void showModalUpdateService() {
        
    }    
    @FXML
    private void detailsProduct(){
        
    }
}
