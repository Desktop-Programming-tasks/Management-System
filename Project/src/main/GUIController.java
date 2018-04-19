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
import main.query.BrandQueryController;
import main.query.GenericTransactionQueryController;
import main.query.PersonQueryController;
import main.query.ServiceQueryController;
import main.query.StockQueryController;
import main.register.CustomerRegisterController;
import main.register.EmployeeRegisterController;
import main.register.ProductRegisterController;
import main.register.SupplierRegisterController;

/**
 *
 * @author ecaanchesjr
 */
public class GUIController {
    private Stage mainStage;
    
    private Parent indexParent;
    private Parent actionParent;
    private Parent queryService;
    private Parent queryGenericTransaction;
    private Parent queryCustomer;
    private Parent queryEmployee;
    private Parent queryBrand;
    private Parent queryStock;
    private Parent registerCustomer;
    private Parent registerEmployee;
    private Parent registerService;
    private Parent registerSupplier;
    private Parent registerProduct;
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
    
    public void showGenericTransationQuery(String transactionType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("query/GenericTransactionQuery.fxml"));
            queryGenericTransaction = loader.load();
            GenericTransactionQueryController controller = (GenericTransactionQueryController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(queryGenericTransaction);
            controller.setTransactionType(transactionType);
            controller.setInformation();
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showServiceQuery() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("query/ServiceQuery.fxml"));
            queryService = loader.load();
            ServiceQueryController controller = (ServiceQueryController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(queryService);
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showBrandQuery() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("query/BrandQuery.fxml"));
            queryBrand = loader.load();
            BrandQueryController controller = (BrandQueryController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(queryBrand);
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showStockQuery() {
        try {
            System.out.println("Entrou");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("query/StockQuery.fxml"));
            queryStock = loader.load();
            StockQueryController controller = (StockQueryController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(queryStock);
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPersonQuery(String personType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("query/PersonQuery.fxml"));
            queryGenericTransaction = loader.load();
            PersonQueryController controller = (PersonQueryController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(queryGenericTransaction);
            controller.setPersonType(personType);
            controller.setInformation();
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showCustomerRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register/CustomerRegister.fxml"));
            registerCustomer = loader.load();
            CustomerRegisterController controller = (CustomerRegisterController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(registerCustomer);
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showEmployeeRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register/EmployeeRegister.fxml"));
            registerEmployee = loader.load();
            EmployeeRegisterController controller = (EmployeeRegisterController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(registerEmployee);
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showProductRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register/ProductRegister.fxml"));
            registerProduct = loader.load();
            ProductRegisterController controller = (ProductRegisterController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(registerProduct);
            mainStage.setScene(nowScene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showSupplierRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register/SupplierRegister.fxml"));
            registerSupplier = loader.load();
            SupplierRegisterController controller = (SupplierRegisterController) loader.getController();
            previousScene = nowScene;
            nowScene = new Scene(registerSupplier);
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
