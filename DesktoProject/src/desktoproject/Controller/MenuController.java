/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class MenuController implements Initializable {

    @FXML
    AnchorPane dynamic;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public AnchorPane getDynamic(){
        return dynamic;
    }
    
    @FXML
    private void showMainActionM(ActionEvent evt) {
    }

    @FXML
    private void showGenericTransaction(ActionEvent evt) {
    }
    
    @FXML
    private void showServiceQuery(){
    }

    @FXML
    private void showBrandQuery() {
    }

    @FXML
    private void showStockQuery() {
    }

    @FXML
    private void showPersonQuery(ActionEvent evt) {
    }

    @FXML
    private void showSupplierQuery() {
    }

    @FXML
    private void showCustomerRegister() {
    }

    @FXML
    private void showEmployeeRegister() {
    }

    @FXML
    private void showProductRegister() {
    }

    @FXML
    private void showSupplierRegister() {
    }

    @FXML
    private void showModalRegisterBrand() {
    }

    @FXML
    private void showModalService() {
    }

    @FXML
    private void showAboutInformationAlert() {
    }
}
