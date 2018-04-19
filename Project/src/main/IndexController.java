/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showMainAction(ActionEvent evt) {
        Button btn = (Button)(evt.getSource());
        String actionType;
        
        System.out.println(btn.getText());
        
        if(btn.getText().equals("VENDA")) {
            actionType = "Venda";
        } else {
            actionType = "Compra";
        }
        
        GUIController.getInstance().showMainActionScreen(actionType);
    }
    
    @FXML
    private void showGenericTransaction(ActionEvent evt) {
        MenuItem itemName = (MenuItem) (evt.getSource());
        
        GUIController.getInstance().showGenericTransationQuery(itemName.getText());
    }
    
    @FXML
    private void showServiceQuery() {
        GUIController.getInstance().showServiceQuery();
    }
    
    @FXML
    private void showBrandQuery() {
        GUIController.getInstance().showBrandQuery();
    }
    
    @FXML
    private void showStockQuery() {
        GUIController.getInstance().showStockQuery();
    }
    
    @FXML
    private void showPersonQuery(ActionEvent evt) {
        MenuItem itemName = (MenuItem) (evt.getSource());
        
        GUIController.getInstance().showPersonQuery(itemName.getText());
    }
    
    @FXML 
    private void showSupplierQuery() {
        GUIController.getInstance().showSupplierQuery();
    }
    
    @FXML
    private void showCustomerRegister() {
        GUIController.getInstance().showCustomerRegister(false);
    }
    
    @FXML
    private void showEmployeeRegister() {
        GUIController.getInstance().showEmployeeRegister(false);
    }
    
    @FXML
    private void showProductRegister() {
        GUIController.getInstance().showProductRegister(false);
    }
    
    @FXML
    private void showSupplierRegister() {
        GUIController.getInstance().showSupplierRegister();
    }
    
    @FXML
    private void showModalService() {
        GUIController.getInstance().showModalService();
    }
}
