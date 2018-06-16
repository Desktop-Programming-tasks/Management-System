/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.Interfaces.Controller;
import Classes.Enums.EmployeeType;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Globals;
import desktoproject.Utils.Animation;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class IndexController extends Controller implements Initializable {

//    private static final String indexPath = "desktoproject/View/Index.fxml";
//
//    public static ScreenData call(Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(IndexController.class.getClassLoader().getResource(indexPath));
//        Parent p = loader.load();
//        IndexController controller = loader.getController();
//
//        return new ScreenData(p, controller);
//    }
    @FXML
    private AnchorPane overPanel;
    @FXML
    private ImageView imageRegister;
    @FXML
    private ImageView imageStock;
    @FXML
    private ImageView imageQuery;
    @FXML
    private ImageView imageBuy;
    @FXML
    private ImageView imageSale;
    @FXML
    private Button saleBtn;
    @FXML
    private Button buyBtn;
    @FXML
    private MenuButton queryBtn;
    @FXML
    private MenuButton registerBtn;
    @FXML
    private Button stockBtn;
    @FXML
    private MenuItem queryEmployeeMenuItem;
    @FXML
    private MenuItem registerEmployeeMenuItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(saleBtn);
        Animation.bindShadowAnimation(buyBtn);
//        Animation.bindShadowAnimation(queryBtn);
//        Animation.bindShadowAnimation(registerBtn); 
        Animation.bindShadowAnimation(stockBtn);

        try {
            if (Globals.getInstance().getEmployee().getEmployeeType() == EmployeeType.COMMOM) {
                queryEmployeeMenuItem.setVisible(false);
                registerEmployeeMenuItem.setVisible(false);
            }
        } catch (RemoteException ex) {
            System.out.println("ops - inside index controller");
        }
    }

    @FXML
    private void callBuy() {
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_BUY_CREATE);
    }

    @FXML
    private void callSale() {
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_SALE_CREATE);
    }

    @FXML
    private void showQueryTransactionAll() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_ALL);
    }

    @FXML
    private void showQuerySell() {
        GUIController.getInstance().callScreen(ScreenType.QUERY_TRANSACTION_SALE);
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
    private void showQueryServiceType() {
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

    @FXML
    private void showPromoteLegalPersons() {
        GUIController.getInstance().callScreen(ScreenType.REGISTER_PROMOTE_LEGAL_PERSON);
    }

    @FXML
    private void showPromoteJuridicalPersons() {
        GUIController.getInstance().callScreen(ScreenType.REGISTER_PROMOTE_JURIDICAL_PERSON);
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.INDEX_SCREEN;
    }
}
