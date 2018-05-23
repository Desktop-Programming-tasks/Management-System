/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Utils.Pairs.ControllerInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class MenuController implements Initializable {

    private static final String path = "desktoproject/View/Menu.fxml";
    
    public static ControllerInfo call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LoginController.class.getClassLoader().getResource(path));
        Parent p = loader.load();
        MenuController controller = loader.getController();
        return new ControllerInfo(p, controller);
    }
    
    @FXML
    private AnchorPane dynamic;
    
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
