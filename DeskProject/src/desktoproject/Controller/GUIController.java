/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import Classes.Enums.*;
import Classes.Persons.*;
import Classes.Transactions.*;
import desktoproject.Controller.Enums.*;
import desktoproject.Controller.Modal.*;
import desktoproject.Controller.Panels.*;
import desktoproject.Controller.Query.*;
import desktoproject.Utils.Pairs.ScreenCall;
import desktoproject.Utils.Pairs.ScreenObject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ecaanchesjr
 */
public class GUIController {

    private final String cssAlertPath = "desktoproject/View/css/alert.css";
    
    private Stage mainStage;
    private Stage modalStage;

    private Stack<ScreenCall> executionStack;

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
        mainStage.setMinWidth(640);
        mainStage.setMinHeight(500);
        mainStage.setTitle("Gerenciador");
        callLogin();
//        callScreen(ScreenType.INDEX);
        setUpModalStage();
//        callScreen(ScreenType.EMPLOYEE_CREATE);
//        callModalForResult(ModalType.PRODUCT_ADD);
//        callScreen(ScreenType.CUSTOMER_CREATE);
//        testScreen();
//        callModal(ModalType.BRAND_NEW);
    }

    private void setUpModalStage() {
        modalStage = new Stage();
        modalStage.initOwner(mainStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setResizable(false);
        modalStage.setTitle("Gerenciador");
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
            ScreenObject info = MenuController.call();
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
    
    public void callScreen(ScreenType type, Object obj){
        callScreen(type, obj,false);
    }

    private void callScreen(ScreenType type, Object obj, boolean back) {
        if(!back){
            executionStack.push(new ScreenCall(type, obj));
        }
        if(!isMenu){
            setMenuScreen();
        }
        try {
            dynamic.setMaxWidth(1280);
            switch (type) {
                case INDEX: {
                    dynamic.setMaxHeight(720);
                    setDynamicChild(IndexController.call(mainStage).getParent());
                    break;
                }
                case TRANSACTION_BUY_CREATE:{
                    setDynamicChild(TransactionController.call(TransactionType.BUY,mainStage));
                    break;
                }
                case TRANSACTION_SALE_CREATE:{
                    setDynamicChild(TransactionController.call(TransactionType.SALE,mainStage));
                    break;
                }
                case TRANSACTION_BUY_DISPLAY:{
                    setDynamicChild(TransactionController.call(TransactionType.BUY,obj,mainStage));
                    break;
                }
                case TRANSACTION_SALE_DISPLAY:{
                    setDynamicChild(TransactionController.call(TransactionType.SALE,obj,mainStage));
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
                    setDynamicChild(PersonController.call(PersonQueryType.EMPLOYEE));
                    break;
                }
                case QUERY_SERVICE: {
                    setDynamicChild(ServiceController.call());
                    break;
                }
                case QUERY_SERVICE_TYPE: {
                    setDynamicChild(ServiceTypeQueryController.call());
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
                    setDynamicChild(ProductController.call(mainStage));
                    break;
                }
                case PRODUCT_DISPLAY: {
                    setDynamicChild(ProductController.call(obj,mainStage));
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
    
    public Transaction callModalForResult(ModalType type){
        try{
            switch(type){
                case PRODUCT_ADD:{
                    ScreenObject so = AddProductController.call();
                    setUpModal(so.getParent());
                    return ((AddProductController)so.getController()).getSelectedProduct();
                }
                case SERVICE_NEW:{
                    ScreenObject so = CreateServiceController.call();
                    setUpModal(so.getParent());
                    return ((CreateServiceController)so.getController()).getNewServiceReturn();
                }
            }
        }catch(IOException ex){
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void callModal(ModalType type, Object obj){
        try{
            switch(type){
                case BRAND_NEW:{
                    setUpModal(BrandModalController.call());
                    break;
                }
                case BRAND_UPDATE: {
                    setUpModal(BrandModalController.call(obj));
                    break;
                }
                case SERVICE_TYPE_CREATE:{
                    setUpModal(ServiceTypeController.call());
                    break;
                }
                case SERVICE_TYPE_EDIT:{
                    setUpModal(ServiceTypeController.call(obj));
                    break;
                }
                case SERVICE_UPDATE:{
                    setUpModal(CreateServiceController.call(obj));
                    break;
                }
            }
        }catch(IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setUpModal(Parent p){
        modalStage.setScene(new Scene(p));
        modalStage.showAndWait();
    }

    public boolean showEraseConfirmationAlert(String msg) {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Confirme a operação.");
        confirmDelete.setHeaderText("Deseja realmente deletar "+msg+"?");
        confirmDelete.setContentText(" ");
        
        DialogPane diagPanel = confirmDelete.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssAlertPath).toExternalForm());
        
        return (((Optional<ButtonType>) confirmDelete.showAndWait()).get() == ButtonType.OK);
    }
    
    public void showAlert(Alert.AlertType type, String title, String header, String content){
        Alert informationDiag;
        
        informationDiag = new Alert(type);
        informationDiag.setTitle(title);
        informationDiag.setHeaderText(header);
        informationDiag.setContentText(content);
        
        DialogPane diagPanel = informationDiag.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssAlertPath).toExternalForm());
        informationDiag.showAndWait();
    }
    
    public void showEraseAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Operação de remoção", "Remoção bem sucedida!", "Operação de remoção concluída!");
    }
    
    public void showRegisterAlert(String item){
        showRegisterAlert(item,"");
    }
    
    public void showRegisterAlert(String item,String error){
        showAlert(error.isEmpty()?Alert.AlertType.INFORMATION:Alert.AlertType.ERROR, "Cadastro", "Cadastro de "+item+(error.isEmpty()?" bem sucedida":" não concluída"), error);
    }
    
    public void showConnectionErrorAlert(){
        showAlert(Alert.AlertType.ERROR, "Erro de conexão", "Erro na conexão com o servidor", "");
    }

    public void showAboutAlert() { 
        showAlert(Alert.AlertType.INFORMATION, "Sobre o Software", 
                "Sistema de Gerênciamento para Lojas de Informática.", 
                "Software desenvolvido como trabalho prático para a \ndiscíplina de Programação Desktop.\n");
    }
    
    public void showDupplicatedAlert(String type,String idName){
        showAlert(Alert.AlertType.ERROR, "Erro no cadastro", type+" já cadastrado", idName+" já utilizado");
//        showRegisterAlert(personType, idName+" já utilizado");
    }
    
    public void showSelectionErrorAlert(){
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de seleção", "Nenhuma entrada selecionada");
    }
    
    public void showDeleteErrorAlert(){
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de exclusão", "Entrada não encontrada");
    }
    
    public void showOperationNotAllowed(){
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de operação", "Operação não permitida");
    }
    
    public void showUpdateErrorAlert(){
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao alterar", "Pessoa não encontrada");
    }
    
    public void showUpdateAlert(){
        showAlert(Alert.AlertType.INFORMATION, "Alteração", "Alteração concluida","");
    }
    
    public void showBrandExceptioAlert(){
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de marca", "A marca selecionada não está mais disponivel");
    }
    
    public void closeModal() {
        modalStage.close();
    }
    
    public void backToPrevious() {
        executionStack.pop();
        ScreenCall obj = executionStack.peek();
        callScreen(obj.getScreen(),obj.getObj(),true);
    }
    
    public void backToIndex(){
        executionStack.clear();
        callScreen(ScreenType.INDEX);
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
        Employee employee = new Employee("login test", "password", EmployeeType.COMMOM, "87854", "employee of the month", address, telephones, "498431");
        Service service = new Service(LocalDate.MAX, LocalDate.MAX, ServiceStatus.REFUSED, employee, new ServiceType("serviço test", 1564));
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(testProduct);
        transactions.add(service);
        
        Record record = new Record(0, employee, new Date(), (float) 4651.0, legalPerson, transactions, RecordType.BUY);
        
//        callModal(ModalType.SERVICE_ADD);
//        callModal(ModalType.SERVICE_UPDATE,service);
//        callScreen(ScreenType.TRANSACTION_BUY_DISPLAY,record);
//        callScreen(ScreenType.TRANSACTION_BUY_CREATE);
//        callScreen(ScreenType.EMPLOYEE_CREATE);
//        callScreen(ScreenType.EMPLOYEE_DISPLAY,employee);
//        callScreen(ScreenType.CUSTOMER_CREATE);
//        callScreen(ScreenType.CUSTOMER_DISPLAY, legalPerson);
//        callScreen(ScreenType.CUSTOMER_DISPLAY, juridicalPerson);
//        callModal(ModalType.BRAND_UPDATE, testBrand);
//        callScreen(ScreenType.PRODUCT_DISPLAY, testProduct);
//        callScreen(ScreenType.SUPPLIER_CREATE);
//        callScreen(ScreenType.SUPPLIER_DISPLAY, supplierTest);
    }
}
