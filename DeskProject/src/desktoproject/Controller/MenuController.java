/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Utils.Pairs.ScreenObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    
    public static ScreenObject call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LoginController.class.getClassLoader().getResource(path));
        Parent p = loader.load();
        MenuController controller = loader.getController();
        return new ScreenObject(p, controller);
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

    public AnchorPane getDynamic() {
        return dynamic;
    }

    @FXML
    private void showIndex() {
        GUIController.getInstance().backToIndex();
    }

    @FXML
    private void showHelp() {
        GUIController.getInstance().showAboutAlert();
    }

    @FXML
    private void showQueryTransactionAll() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_ALL);
    }

    @FXML
    private void showCreateSell() {
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_SALE_CREATE);
    }

    @FXML
    private void showQuerySell() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_SALE);
    }

    @FXML
    private void showCreateBuy() {
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_BUY_CREATE);
    }

    @FXML
    private void showQueryBuy() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_BUY);
    }

    @FXML
    private void showCreateClient() {
        GUIController.getInstance().callScreen(ScreenType.CUSTOMER_CREATE);
    }

    @FXML
    private void showQueryClient() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_PERSON_CUSTOMER);
    }

    @FXML
    private void showCreateSupplier() {
        GUIController.getInstance().callScreen(ScreenType.SUPPLIER_CREATE);
    }

    @FXML
    private void showQuerySupplier() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_SUPPLIER);
    }

    @FXML
    private void showCreateEmployee() {
        GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_CREATE);
    }

    @FXML
    private void showQueryEmployee() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_PERSON_EMPLOYEE);
    }

    @FXML
    private void showCreateService() {
        GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_CREATE);
    }

    @FXML
    private void showQueryService() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_SERVICE);
    }
    
    @FXML
    private void showQueryServiceType(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_SERVICE_TYPE);
    }

    @FXML
    private void showCreateProduct() {
        GUIController.getInstance().callScreen(ScreenType.PRODUCT_CREATE);
    }

    @FXML
    private void showQueryProduct() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_STOCK);
    }

    @FXML
    private void showCreateBrand() {
        GUIController.getInstance().callModal(ModalType.BRAND_NEW);
    }

    @FXML
    private void showQueryBrand() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_BRAND);
    }
}
