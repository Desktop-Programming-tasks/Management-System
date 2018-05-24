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
import desktoproject.Model.Classes.Persons.Employee;
import desktoproject.Model.Classes.Persons.JuridicalPerson;
import desktoproject.Model.Classes.Persons.LegalPerson;
import desktoproject.Model.Classes.Persons.Supplier;
import desktoproject.Model.Classes.Transactions.Brand;
import desktoproject.Model.Classes.Transactions.Product;
import desktoproject.Model.Classes.Transactions.Record;
import desktoproject.Model.Classes.Transactions.Service;
import desktoproject.Model.Classes.Transactions.ServiceType;
import desktoproject.Model.Classes.Transactions.Transaction;
import desktoproject.Model.Enums.RecordType;
import desktoproject.Model.Enums.ServiceStatus;
import desktoproject.Utils.Pairs.ControllerInfo;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ecaanchesjr
 */
public class GUIController {

    private final String cssPath = "desktoproject/View/css/alert.css";
    
    private Stage mainStage;
    private Stage modalStage;

    private Parent indexParent;

    private Scene nowScene;
    private Scene previousScene;

    private Stack<Scene> executionStack;

    private AnchorPane dynamic;
    private boolean isMenu;

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
        mainStage = stage;
        mainStage.setMinWidth(1280);
        mainStage.setMinHeight(720);
        callLogin();
        setUpModalStage();
//        callScreen(ScreenType.EMPLOYEE_CREATE);
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
    
    public void callLogin(){
        try {
            mainStage.setScene(new Scene(LoginController.call()));
            mainStage.show();
            isMenu = false;
            this.dynamic = null;
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setMenuScreen(){
        try {
            ControllerInfo info = MenuController.call();
            this.dynamic = ((MenuController)info.getController()).getDynamic();
            mainStage.setScene(new Scene(info.getParent()));
            mainStage.show();
            isMenu = true;
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //if no object is passed the callScreen is called with null
    //this method is used to call screens that don't need to display information of an already created object
    public void callScreen(ScreenType type){
        callScreen(type, null);
    }

    public void callScreen(ScreenType type, Object obj) {
        if(!isMenu){
            setMenuScreen();
        }
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

    public boolean showEraseConfirmationAlert(String msg) {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Confirme a operação.");
        confirmDelete.setHeaderText("Deseja realmente deletar "+msg+"?");
        confirmDelete.setContentText(" ");
        
        DialogPane diagPanel = confirmDelete.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toExternalForm());
        
        return (((Optional<ButtonType>) confirmDelete.showAndWait()).get() == ButtonType.OK);
    }
    
    public void showEraseAlert() {
        Alert aboutInfo = new Alert(Alert.AlertType.INFORMATION);

        aboutInfo.setTitle("Operação de remoção");
        aboutInfo.setHeaderText("Remoção bem sucedida!");
        aboutInfo.setContentText("Operação de remoção concluída!");

        DialogPane diagPanel = aboutInfo.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toExternalForm());
        aboutInfo.showAndWait();
    }

    public void showAlert(Alert.AlertType type, String title, String header, String content){
        Alert informationDiag;
        
        informationDiag = new Alert(type);
        informationDiag.setTitle(title);
        informationDiag.setHeaderText(header);
        informationDiag.setContentText(content);
        
        DialogPane diagPanel = informationDiag.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toExternalForm());
        informationDiag.showAndWait();
    }

    public void showAboutAlert() {
        Alert aboutInfo = new Alert(Alert.AlertType.INFORMATION);

        aboutInfo.setTitle("Sobre o Software");
        aboutInfo.setHeaderText("Sistema de Gerênciamento para Lojas de Informática.");
        aboutInfo.setContentText("Software desenvolvido como trabalho prático para a \ndiscíplina de Programação Desktop.\n");

        DialogPane diagPanel = aboutInfo.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssPath).toExternalForm());
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
        Product testProduct = new Product("123456789", testBrand, 1100, "Escova de Dente");
        Address address = new Address("Rua Da batata quente", 13, "Seu cu", "Fodase", "E o caralho");
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("9955999599");
        telephones.add("6845465465465");

        JuridicalPerson juridicalPerson = new JuridicalPerson("Pessoa juridica", address, telephones, "87745456454");
        Supplier supplierTest = new Supplier(brands, "Fornecedor de porra", address, telephones, "1");
        LegalPerson legalPerson = new LegalPerson("88888", "Batata", address, telephones, "887456423");
        Employee employee = new Employee("login test", "password", "87854", "employee of the month", address, telephones, "498431");
        Service service = new Service(LocalDate.MAX, LocalDate.MAX, ServiceStatus.REFUSED, employee, new ServiceType("serviço test", 1564));
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(testProduct);
        transactions.add(service);
        
        Record record = new Record(0, employee, new Date(), (float) 4651.0, legalPerson, transactions, RecordType.BUY);
        
//        callScreen(ScreenType.TRANSACTION_BUY_DISPLAY,record);
        callScreen(ScreenType.TRANSACTION_BUY_CREATE);
//        callScreen(ScreenType.EMPLOYEE_DISPLAY,employee);
//        callScreen(ScreenType.CUSTOMER_DISPLAY, legalPerson);
//        callScreen(ScreenType.CUSTOMER_DISPLAY, juridicalPerson);
        //callModal(ModalType.BRAND_UPDATE, testBrand);
        //callScreen(ScreenType.PRODUCT_DISPLAY, testProduct);
//        callScreen(ScreenType.SUPPLIER_DISPLAY, supplierTest);
    }
}
