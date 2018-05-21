/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Enums.*;
import desktoproject.Controller.Modal.*;
import desktoproject.Controller.Query.*;
import desktoproject.Controller.Panels.*;
import desktoproject.Model.Classes.Persons.Address;
import desktoproject.Model.Classes.Persons.Supplier;
import desktoproject.Model.Classes.Transactions.Brand;
import desktoproject.Model.Classes.Transactions.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ecaanchesjr
 */
public class GUIController {

    private Stage mainStage;
    private Stage modalStage;

    private Parent indexParent;

    private Scene nowScene;
    private Scene previousScene;

    private Stack<Scene> executionStack;

    AnchorPane dynamic;

    private GUIController() {
        executionStack = new Stack<>();
    }

    public static GUIController getInstance() {
        return GUIControllerHolder.INSTANCE;
    }

    private static class GUIControllerHolder {

        private static final GUIController INSTANCE = new GUIController();
    }

    public void startApp(Stage stage) {
        try {
            mainStage = stage;
            mainStage.setMinWidth(1280);
            mainStage.setMinHeight(720);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("desktoproject/View/Menu.fxml"));
            indexParent = loader.load();
            MenuController controller = loader.getController();
            this.dynamic = controller.getDynamic();

            callScreen(ScreenType.INDEX);

            nowScene = new Scene(indexParent);
            executionStack.push(nowScene);
            mainStage.setScene(nowScene);
            mainStage.show();
            
        } catch (IOException ex) {
            System.out.println("Error starting app: " + ex.getMessage());
        }
        setUpModalStage();
        testScreen();
    }

    private void setUpModalStage() {
        modalStage = new Stage();
        modalStage.initOwner(mainStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
    }

    private void setDynamicChild(Parent p) {
        this.dynamic.getChildren().clear();
        AnchorPane.setTopAnchor(p,32.00);
        AnchorPane.setLeftAnchor(p,32.00);
        AnchorPane.setBottomAnchor(p,32.00);
        AnchorPane.setRightAnchor(p,32.00);
        this.dynamic.getChildren().add(p);
    }
    
    //if no object is passed the callScreen is called with null
    //this method is used to call screens that don't need to display information of an already created object
    public void callScreen(ScreenType type){
        callScreen(type, null);
    }

    public void callScreen(ScreenType type, Object obj) {
        try {
            switch (type) {
                case INDEX: {
                    setDynamicChild(IndexController.call());
                    break;
                }
                case TRANSACTION_BUY_CREATE:{
                    setDynamicChild(TransactionController.call(TransactionType.BUY));
                    break;
                }
                case TRANSACTION_SALE_CREATE:{
                    setDynamicChild(TransactionController.call(TransactionType.SALE));
                    break;
                }
                case TRANSACTION_BUY_DISPLAY:{
                    setDynamicChild(TransactionController.call(TransactionType.BUY,obj));
                    break;
                }
                case TRANSACTION_SALE_DISPLAY:{
                    setDynamicChild(TransactionController.call(TransactionType.SALE,obj));
                    break;
                }
                case QUERY_TRANSACTION_BUY: {
                    setDynamicChild(GenericTransactionController.call(TransactionQueryType.BUY));
                    break;
                }
                case QUERY_TRANSACTION_SALE: {
                    setDynamicChild(GenericTransactionController.call(TransactionQueryType.SALE));
                    break;
                }
                case QUERY_TRANSACTION_ALL: {
                    setDynamicChild(GenericTransactionController.call(TransactionQueryType.ALL));
                    break;
                }
                case QUERY_BRAND: {
                    setDynamicChild(BrandController.call());
                    break;
                }
                case QUERY_PERSON_CUSTOMER: {
                    setDynamicChild(PersonController.call(PersonQueryType.CUSTOMER));
                    break;
                }
                case QUERY_PERSON_EMPLOYEE: {
                    setDynamicChild(PersonController.call(PersonQueryType.CUSTOMER));
                    break;
                }
                case QUERY_SERVICE: {
                    setDynamicChild(ServiceController.call());
                    break;
                }
                case QUERY_STOCK: {
                    setDynamicChild(StockController.call());
                    break;
                }
                case QUERY_SUPPLIER: {
                    setDynamicChild(QuerySupplierController.call());
                    break;
                }
                case CUSTOMER_CREATE: {
                    setDynamicChild(CustomerController.call());
                    break;
                }
                case CUSTOMER_DISPLAY: {
                    setDynamicChild(CustomerController.call(obj));
                    break;
                }
                case EMPLOYEE_CREATE: {
                    setDynamicChild(EmployeeController.call());
                    break;
                }
                case EMPLOYEE_DISPLAY: {
                    setDynamicChild(EmployeeController.call(obj));
                    break;
                }
                case PRODUCT_CREATE: {
                    setDynamicChild(ProductController.call());
                    break;
                }
                case PRODUCT_DISPLAY: {
                    setDynamicChild(ProductController.call(obj));
                    break;
                }
                case SUPPLIER_CREATE: {
                    setDynamicChild(SupplierController.call());
                    break;
                }
                case SUPPLIER_DISPLAY: {
                    setDynamicChild(SupplierController.call(obj));
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void callModal(ModalType type){
        callModal(type,null);
    }
    
    public void callModal(ModalType type, Object obj){
        try{
            switch(type){
                case PRODUCT_ADD:{
                    setUpModal(AddProductController.call());
                    break;
                }
                case BRAND_NEW:{
                    setUpModal(BrandModalController.call());
                    break;
                }
                case BRAND_UPDATE: {
                    setUpModal(BrandModalController.call(obj));
                    break;
                }
                case SERVICE_ADD:{
                    setUpModal(AddServiceController.call());
                    break;
                }
                case SERVICE_NEW:{
                    setUpModal(NewServiceController.call());
                    break;
                }
                case SERVICE_UPDATE:{
                    setUpModal(UpdateServiceController.call(obj));
                    break;
                }
            }
        }catch(IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setUpModal(Parent p){
        modalStage.setScene(new Scene(p));
        modalStage.show();
    }

    public void showInformationEraseAlert() {
        Alert aboutInfo = new Alert(Alert.AlertType.CONFIRMATION);

        aboutInfo.setTitle("Operação de remoção");
        aboutInfo.setHeaderText("Remoção bem sucedida!");
        aboutInfo.setContentText("Operação de remoção concluída!");

        DialogPane diagPanel = aboutInfo.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getResource("css/alert.css").toExternalForm());
        aboutInfo.showAndWait();
    }

    public void showInformationAlert(String msg) {
        Alert informationDiag;
        if (msg.isEmpty()) {
            informationDiag = new Alert(Alert.AlertType.CONFIRMATION);
            informationDiag.setTitle("Operação bem sucedida");
            informationDiag.setHeaderText("Confirmação de operação!");
            informationDiag.setContentText("Operação realizada com sucesso!");
        } else {
            informationDiag = new Alert(Alert.AlertType.ERROR);
            informationDiag.setTitle("Operação falhou");
            informationDiag.setHeaderText("Falha de operação");
            informationDiag.setContentText(msg);
        }
        DialogPane diagPanel = informationDiag.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getResource("css/alert.css").toExternalForm());
        informationDiag.showAndWait();
    }

    public void showAboutInformationAlert() {
        Alert aboutInfo = new Alert(Alert.AlertType.INFORMATION);

        aboutInfo.setTitle("Sobre o Software");
        aboutInfo.setHeaderText("Sistema de Gerênciamento para Lojas de Informática.");
        aboutInfo.setContentText("Software desenvolvido como trabalho prático para a \ndiscíplina de Programação Desktop.\n");

        DialogPane diagPanel = aboutInfo.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getResource("css/alert.css").toExternalForm());
        aboutInfo.showAndWait();
    }

    public void closeModal() {
        modalStage.close();
    }

    public void backToPrevious() {
        executionStack.pop();
        nowScene = executionStack.peek();
        mainStage.setScene(nowScene);
        mainStage.show();
    }
    
    public void testScreen() {
        Brand testBrand = new Brand("Batata");
        Brand testBrand2 = new Brand("Batata1");
        ArrayList<Brand> brands = new ArrayList<>();
        brands.add(testBrand); brands.add(testBrand2);
        Product testProduct = new Product("123456789", brands, 1100, "Escova de Dente");
        Address address = new Address("Rua Da batata quente", 13, "Seu cu", "Fodase", "E o caralho");
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("9955999599");
        telephones.add("6845465465465");
        //JuridicalPerson juridicalPerson = new JuridicalPerson("Pessoa juridica", address, telephones, "87745456454");
        Supplier supplierTest = new Supplier(brands, "Fornecedor de porra", address, telephones, "1");
        
        //callModal(ModalType.BRAND_UPDATE, testBrand);
        //callScreen(ScreenType.PRODUCT_DISPLAY, testProduct);
        callScreen(ScreenType.SUPPLIER_DISPLAY, supplierTest);
        
    }
}
