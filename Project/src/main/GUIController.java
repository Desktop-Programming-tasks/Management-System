/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ecaanchesjr
 */
public class GUIController {
    private Stage mainStage;
    
    private Parent indexParent;
    private Parent actionParent;
    private Parent queryPurchase;
    private Parent querySell;
    private Parent queryService;
    private Parent queryTransaction;
    private Parent queryCustomer;
    private Parent queryEmployee;
    private Parent registerCustomer;
    private Parent registerEmployee;
    private Parent registerService;
    private Parent registerSupplier;
    private Parent modalAddService;
    private Parent modalAddProduct;
    private Parent modalRegisterService;
    private Parent modalUpdateService;
    
    private Scene nowScene;
    private Scene previousScene;
    
    
    private GUIController() {
    }
    
    public static GUIController getInstance() {
        return GUIControllerHolder.INSTANCE;
    }
    
    private static class GUIControllerHolder {

        private static final GUIController INSTANCE = new GUIController();
    }
    
    public void startApp(Stage stage) {
        mainStage = stage;
        try {
            indexParent = FXMLLoader.load(getClass().getResource("Index.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nowScene = new Scene(indexParent);
        mainStage.setScene(nowScene);
        mainStage.show();
    }
    
    public void showMainActionScreen(String actionType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Template.fxml"));
            actionParent = loader.load();
            TemplateController controller = (TemplateController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(actionParent);
            controller.setActionType(actionType);
            controller.setInformation();
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void backToPrevious() {
        nowScene = previousScene;
        previousScene = null;
        mainStage.setScene(nowScene);
        mainStage.show();
    }
}
