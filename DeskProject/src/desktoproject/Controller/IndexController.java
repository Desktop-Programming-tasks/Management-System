/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.ScreenType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class IndexController implements Initializable {
    private static final String indexPath = "desktoproject/View/Index.fxml";
    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IndexController.class.getClassLoader().getResource(indexPath));
        return loader.load();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void callBuy(){
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_BUY_CREATE);
    }
    
    @FXML
    private void callSale(){
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_SALE_CREATE);
    }
    
    @FXML
    private void showQueryTransactionAll(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_ALL);
    }
    
    @FXML
    private void showQuerySell(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_SALE);
    }
    
    @FXML
    private void showQueryBuy(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_BUY);
    }
    
    @FXML
    private void showCreateClient(){
        GUIController.getInstance().callScreen(ScreenType.CUSTOMER_CREATE);
    }
    
    @FXML
    private void showQueryClient(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_PERSON_CUSTOMER);
    }

    @FXML
    private void showCreateSupplier(){
        GUIController.getInstance().callScreen(ScreenType.SUPPLIER_CREATE);
    }
    
    @FXML
    private void showQuerySupplier(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_SUPPLIER);
    }

    @FXML
    private void showCreateEmployee(){
        GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_CREATE);
    }
    
    @FXML
    private void showQueryEmployee(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_PERSON_EMPLOYEE);
    }
    
    @FXML
    private void showCreateService(){
        GUIController.getInstance().callModal(ModalType.SERVICE_NEW);
    }
    
    @FXML
    private void showQueryService(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_SERVICE);
    }
    
    @FXML
    private void showCreateProduct(){
        GUIController.getInstance().callScreen(ScreenType.PRODUCT_CREATE);
    }
    
    @FXML
    private void showQueryProduct(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_STOCK);
    }

    @FXML
    private void showCreateBrand(){
        GUIController.getInstance().callModal(ModalType.BRAND_NEW);
    }
    
    @FXML
    private void showQueryBrand(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_BRAND);
    }
}
