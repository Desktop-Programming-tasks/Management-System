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
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class IndexController implements Initializable {
    private static final String indexPath = "desktoproject/View/Index.fxml";
    public static Parent call(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IndexController.class.getClassLoader().getResource(indexPath));
        Parent p = loader.load();
        IndexController controller = loader.getController();
        controller.setStage(stage);
        controller.setIconsListeners();
        return p;
    }
    
    private Stage stage;
    
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
    private Button mainBtn;
    @FXML
    private MenuButton subBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void setIconsListeners(){
        stage.widthProperty().addListener((observable) -> {
            float main = 200,sub = 160;
            stage.getWidth();//dont ask
            imageSale.setFitWidth(mainBtn.getWidth()-main);
            imageBuy.setFitWidth(mainBtn.getWidth()-main);
            
            double subSize = subBtn.getWidth()-sub;
            if(subSize<30){
                subSize = 30;
            }else if(subSize>100){
                subSize = 100;
            }
            imageQuery.setFitWidth(subSize);
            imageStock.setFitWidth(subSize);
            imageRegister.setFitWidth(subSize);
        });
        
        stage.heightProperty().addListener((observable) -> {
            float main = 130,sub = 34;
            stage.getHeight();//dont ask
            imageSale.setFitHeight(mainBtn.getHeight()-main);
            imageBuy.setFitHeight(mainBtn.getHeight()-main);
            
            double subSize = subBtn.getHeight()-sub;
            if(subSize<30){
                subSize = 30;
            }else if(subSize>100){
                subSize = 100;
            }
            imageQuery.setFitHeight(subSize);
            imageStock.setFitHeight(subSize);
            imageRegister.setFitHeight(subSize);
        });
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
        GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_CREATE);
    }
    
    @FXML
    private void showQueryService(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_SERVICE);
    }
    
    @FXML
    private void showQueryServiceType(){
        GUIController.getInstance().callScreen(ScreenType.QUERY_SERVICE_TYPE);
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
