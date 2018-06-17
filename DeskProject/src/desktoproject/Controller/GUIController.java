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
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Controller.Panels.*;
import desktoproject.Controller.Query.*;
import desktoproject.Utils.Pairs.ScreenCall;
import desktoproject.Utils.Pairs.ScreenData;
import java.io.IOException;
import java.util.ArrayList;
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
        AnchorPane.setTopAnchor(p, 32.00);
        AnchorPane.setLeftAnchor(p, 32.00);
        AnchorPane.setBottomAnchor(p, 32.00);
        AnchorPane.setRightAnchor(p, 32.00);
        this.dynamic.getChildren().add(p);
    }

    public void callLogin() {
        try {
            mainStage.setScene(new Scene(new LoginController().call().getParent()));
            mainStage.show();
            isMenu = false;
            this.dynamic = null;
            ObservableServer.clearAll();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setMenuScreen() {
        try {
            ScreenData info = new MenuController().call();
            this.dynamic = ((MenuController) info.getController()).getDynamic();
            mainStage.setScene(new Scene(info.getParent()));
            mainStage.show();
            isMenu = true;
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //if no object is passed the callScreen is called with null
    //this method is used to call screens that don't need to display information of an already created object
    public void callScreen(ScreenType type) {
        callScreen(type, null);
    }

    public void callScreen(ScreenType type, Object obj) {
        callScreen(type, obj, false);
    }

    private void callScreen(ScreenType type, Object obj, boolean back) {
        if (!back) {
            executionStack.push(new ScreenCall(type, obj));
        }
        if (!isMenu) {
            setMenuScreen();
        }
        ScreenData screenData = null;
        ObservableServer.clearAll();

        try {
            dynamic.setMaxWidth(1280);
            switch (type) {
                case INDEX: {
                    dynamic.setMaxHeight(720);
                    screenData = new IndexController().call();
                    break;
                }
                case TRANSACTION_BUY_CREATE: {
                    screenData = new TransactionController().call(TransactionType.BUY);
                    break;
                }
                case TRANSACTION_SALE_CREATE: {
                    screenData = new TransactionController().call(TransactionType.SALE);
                    break;
                }
                case TRANSACTION_BUY_DISPLAY: {
                    screenData = new TransactionController().call(TransactionType.BUY, obj);
                    break;
                }
                case TRANSACTION_SALE_DISPLAY: {
                    screenData = new TransactionController().call(TransactionType.SALE, obj);
                    break;
                }
                case QUERY_TRANSACTION: {
                    screenData = new GenericTransactionController().call();
                    break;
                }
                case QUERY_BRAND: {
                    screenData = new BrandController().call();
                    break;
                }
                case QUERY_PERSON_CUSTOMER: {
                    screenData = new PersonController().call(PersonQueryType.CUSTOMER);
                    break;
                }
                case QUERY_PERSON_EMPLOYEE: {
                    screenData = new PersonController().call(PersonQueryType.EMPLOYEE);
                    break;
                }
                case QUERY_SERVICE: {
                    screenData = new ServiceController().call();
                    break;
                }
                case QUERY_SERVICE_TYPE: {
                    screenData = new ServiceTypeQueryController().call();
                    break;
                }
                case QUERY_STOCK: {
                    screenData = new StockController().call();
                    break;
                }
                case QUERY_SUPPLIER: {
                    screenData = new QuerySupplierController().call();
                    break;
                }
                case REGISTER_PROMOTE_LEGAL_PERSON: {
                    screenData = new PromotionPersonController().call(PersonPromotion.LEGAL_PERSON);
                    break;
                }
                case REGISTER_PROMOTE_JURIDICAL_PERSON: {
                    screenData = new PromotionPersonController().call(PersonPromotion.JURIDICAL_PERSON);
                    break;
                }
                case CUSTOMER_CREATE: {
                    screenData = new CustomerController().call();
                    break;
                }
                case CUSTOMER_DISPLAY: {
                    screenData = new CustomerController().call(obj);
                    break;
                }
                case EMPLOYEE_CREATE: {
                    screenData = new EmployeeController().call();
                    break;
                }
                case EMPLOYEE_DISPLAY: {
                    screenData = new EmployeeController().call(obj);
                    break;
                }
                case PRODUCT_CREATE: {
                    screenData = new ProductController().call();
                    break;
                }
                case PRODUCT_DISPLAY: {
                    screenData = new ProductController().call(obj);
                    break;
                }
                case SUPPLIER_CREATE: {
                    screenData = new SupplierController().call();
                    break;
                }
                case SUPPLIER_DISPLAY: {
                    screenData = new SupplierController().call(obj);
                    break;
                }
            }
            setDynamicChild(screenData.getParent());
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callModal(ModalType type) {
        callModal(type, null);
    }

    public Transaction callModalForResult(ModalType type) {
        return callModalForResult(type, null);
    }

    public Transaction callModalForResult(ModalType type, Object o) {
        return callModalForResult(type, o, null);
    }

    public Transaction callModalForResult(ModalType type, TransactionType transactionType) {
        return callModalForResult(type, null, transactionType);
    }

    public Transaction callModalForResult(ModalType type, Object o, TransactionType transactionType) {
        try {
            switch (type) {
                case PRODUCT_ADD: {
                    ScreenData screenData = new AddProductController().call(transactionType);
                    setUpModal(screenData.getParent());
                    return ((AddProductController) screenData.getController()).getSelectedProduct();
                }
                case SERVICE_NEW: {
                    ScreenData screenData = new CreateServiceController().call();
                    setUpModal(screenData.getParent());
                    return ((CreateServiceController) screenData.getController()).getNewServiceReturn();
                }
                case PRODUCT_ADD_EDIT: {
                    ScreenData screenData = new AddProductController().call(transactionType, o);
                    setUpModal(screenData.getParent());
                    return ((AddProductController) screenData.getController()).getSelectedProduct();
                }
                case SERVICE_NEW_EDIT: {
                    ScreenData screenData = new CreateServiceController().call(o, true);
                    setUpModal(screenData.getParent());
                    return ((CreateServiceController) screenData.getController()).getNewServiceReturn();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void callModal(ModalType type, Object obj) {
        try {
            ScreenData screenData = null;
            switch (type) {
                case BRAND_NEW: {
                    screenData = new BrandModalController().call();
                    break;
                }
                case BRAND_UPDATE: {
                    screenData = new BrandModalController().call(obj);
                    break;
                }
                case SERVICE_TYPE_CREATE: {
                    screenData = new ServiceTypeController().call();
                    break;
                }
                case SERVICE_TYPE_EDIT: {
                    screenData = new ServiceTypeController().call(obj);
                    break;
                }
                case SERVICE_UPDATE: {
                    screenData = new CreateServiceController().call(obj, false);
                    break;
                }
            }
            setUpModal(screenData.getParent());
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setUpModal(Parent p) {
        modalStage.setScene(new Scene(p));
        modalStage.showAndWait();
    }

    public boolean showEraseConfirmationAlert(String msg) {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Confirme a operação.");
        confirmDelete.setHeaderText("Deseja realmente deletar " + msg + "?");
        confirmDelete.setContentText(" ");

        DialogPane diagPanel = confirmDelete.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssAlertPath).toExternalForm());

        return (((Optional<ButtonType>) confirmDelete.showAndWait()).get() == ButtonType.OK);
    }

    public boolean showUmpromoteConfirmationAlert(String msg) {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Confirme a operação.");
        confirmDelete.setHeaderText("Deseja realmente desabilitar " + msg + "?");
        confirmDelete.setContentText(" ");

        DialogPane diagPanel = confirmDelete.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getClassLoader().getResource(cssAlertPath).toExternalForm());

        return (((Optional<ButtonType>) confirmDelete.showAndWait()).get() == ButtonType.OK);
    }

    public void showAlert(Alert.AlertType type, String title, String header, String content) {
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

    public void showRegisterAlert(String item) {
        showRegisterAlert(item, "");
    }

    public void showRegisterAlert(String item, String error) {
        showAlert(error.isEmpty() ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR, "Cadastro", "Cadastro de " + item + (error.isEmpty() ? " bem sucedida" : " não concluída"), error);
    }

    public void showConnectionErrorAlert() {
        showAlert(Alert.AlertType.ERROR, "Erro de conexão", "Erro na conexão com o servidor", "");
    }

    public void showAboutAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Sobre o Software",
                "Sistema de Gerênciamento para Lojas de Informática.",
                "Software desenvolvido como trabalho prático para a \ndiscíplina de Programação Desktop.\n");
    }

    public void showDupplicatedAlert(String type, String idName) {
        showAlert(Alert.AlertType.ERROR, "Erro no cadastro", type + " já cadastrado", idName + " já utilizado");
    }

    public void showSelectionErrorAlert() {
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de seleção", "Nenhuma entrada selecionada");
    }

    public void showDeleteErrorAlert() {
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de exclusão", "Entrada não encontrada");
    }

    public void showOperationNotAllowed() {
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de operação", "Operação não permitida");
    }

    public void showUpdateErrorAlert() {
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao alterar", "Pessoa não encontrada");
    }

    public void showUpdateAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Alteração", "Alteração concluida", "");
    }

    public void showBrandExceptioAlert() {
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro de marca", "A marca selecionada não está mais disponivel");
    }

    public void showPromoteAlert(String promote) {
        showAlert(Alert.AlertType.INFORMATION, "Promoção de " + promote, "Pessoa promovida com sucesso", "");
    }

    public void closeModal() {
        modalStage.close();
    }

    public void backToPrevious() {
        executionStack.pop();
        ScreenCall obj = executionStack.peek();
        callScreen(obj.getScreen(), obj.getObj(), true);
    }

    public void backToIndex() {
        executionStack.clear();
        callScreen(ScreenType.INDEX);
    }

    public void testScreen() {
        Brand testBrand = new Brand("Batata");
        Brand testBrand2 = new Brand("Batata1");
        ArrayList<Brand> brands = new ArrayList<>();
        brands.add(testBrand);
        brands.add(testBrand2);
        Product testProduct = new Product("123456789", testBrand, 1100, "Escova de Dente");
        Address address = new Address("Rua Da batata quente", 13, "Seu cu", "Fodase", "E o caralho");
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("9955999599");
        telephones.add("6845465465465");

        JuridicalPerson juridicalPerson = new JuridicalPerson("Pessoa juridica", address, telephones, "87745456454");
        Supplier supplierTest = new Supplier(brands, "Fornecedor de porra", address, telephones, "1");
        LegalPerson legalPerson = new LegalPerson("88888", "Batata", address, telephones, "887456423");
        Employee employee = new Employee("login test", "password", EmployeeType.COMMOM, "87854", "employee of the month", address, telephones, "498431");
        //Service service = new Service(LocalDate.MAX, LocalDate.MAX, ServiceStatus.REFUSED, employee, new ServiceType("serviço test", 1564));
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(testProduct);
        //transactions.add(service);

        //Record record = new Record(0, employee, new Date(), (float) 4651.0, legalPerson, transactions, RecordType.BUY);
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
