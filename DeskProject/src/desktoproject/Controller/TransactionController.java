/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import Classes.Constants.RecordTypeConstants;
import Classes.Persons.Person;
import Classes.Transactions.Record;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.TransactionType;
import static desktoproject.Controller.Enums.TransactionType.BUY;
import static desktoproject.Controller.Enums.TransactionType.SALE;
import desktoproject.Globals;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Model.DAO.Transactions.RecordDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import desktoproject.Utils.Pairs.ScreenData;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class TransactionController extends ControllerEdit implements Initializable, TableScreen {
//    private static final String PATH = "desktoproject/View/Transaction.fxml";
//    private Stage stage;
//    //call to create a new transaction
//    public static Parent call(TransactionType type) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(TransactionController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        TransactionController controller = loader.getController();
//        controller.setTransactionType(type);
//        controller.setEdit(false);
//        controller.setComponents();
//        return p;
//    }
//
//    //call to display information about an existing transaction
//    public static Parent call(TransactionType type, Object obj) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(TransactionController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        TransactionController controller = loader.getController();
//        controller.setRecord((Record) obj);
//        controller.setTransactionType(type);
//        controller.setEdit(true);
//        controller.setComponents();
//        return p;
//    }

    public ScreenData call(TransactionType type) throws IOException {
        ScreenData callReturn = super.call();
        TransactionController controller = (TransactionController) callReturn.getController();
        controller.setTransactionType(type);
        controller.setTypeComponents();
        return new ScreenData(callReturn.getParent(), controller);
    }

    public ScreenData call(TransactionType type, Object obj) throws IOException {
        ScreenData callReturn = super.call(obj);
        TransactionController controller = (TransactionController) callReturn.getController();
        controller.setScreenObject(obj);
        controller.setTransactionType(type);
        controller.setTypeComponents();
        return new ScreenData(callReturn.getParent(), controller);
    }

    private TransactionType type;
    private Record record;
    private ArrayList<Transaction> transactions;
    private Stage stage;

    @FXML
    private Button primaryBtn;
    @FXML
    private Button addProductBtn;
    @FXML
    private Button addServiceBtn;
    @FXML
    private Button deleteEntry;
    @FXML
    private Button backBtn;

    @FXML
    private Label mainActionScreenTitle;
    @FXML
    private Label FinalPrice;
    @FXML
    private Label tableLabel;
    @FXML
    private TextField searchTextField;

    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;

    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> nameColumn;
    @FXML
    private TableColumn<Transaction, Float> priceColumn;
    @FXML
    private TableColumn<Transaction, Integer> quantityColumn;

    @FXML
    private TableView<Person> clientTable;
    @FXML
    private TableColumn<Person, String> clientDocumentColumn;
    @FXML
    private TableColumn<Person, String> clientNameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(primaryBtn);
        Animation.bindShadowAnimation(addProductBtn);
        Animation.bindShadowAnimation(addServiceBtn);
        Animation.bindShadowAnimation(deleteEntry);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindAnimation(searchTextField);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        clientDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("documentId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        transactions = new ArrayList<>();

        Platform.runLater(() -> {
            stage = (Stage) tableLabel.getScene().getWindow();
            setStageBreak();
            adjustComponents();
        });

        populateTable();
        setUpSearch();
    }

    @Override
    public void setUpSearch() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.trim();
            if (newValue.isEmpty()) {
                populatePersonTable();
            } else {
                try {
                    if (type == BUY) {
                        clientTable.setItems(FXCollections.observableArrayList(PersonDAO.searchSuppliers(newValue)));
                    } else {
                        clientTable.setItems(FXCollections.observableArrayList(PersonDAO.searchPersons(newValue)));
                    }
                } catch (RemoteException | DatabaseErrorException ex) {

                }
            }
        });
    }

    private void populatePersonTable() {
        try {
            if (type == BUY) {
                clientTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllSuppliers()));
            } else {
                clientTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllPersons()));
            }
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {

        }
    }

    @Override
    public void populateTable() {
        transactionsTable.setItems(FXCollections.observableArrayList(transactions));
    }

    @Override
    public void setTableAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setTypeComponents() {
        String mainLabelString = "", primaryBtnString = "Finalizar ";

        if (isEdit()) {
            mainLabelString += "Consultar ";
            primaryBtn.setVisible(false);
            clientTable.setDisable(true);
//            transactionsTable.setDisable(true);
            addProductBtn.setVisible(false);
            addServiceBtn.setVisible(false);
            deleteEntry.setVisible(false);
            searchTextField.setDisable(true);
            this.transactions = record.getTransations();
            fillScreen();
        } else {
            primaryBtn.setVisible(true);
            this.transactions = new ArrayList<>();
        }

        switch (type) {
            case BUY: {
                tableLabel.setText("Fornecedor");
                mainLabelString += "Compra ";
                primaryBtnString += "Compra ";
                searchTextField.setPromptText("Pesquisar fornecedor");
                break;
            }
            case SALE: {
                tableLabel.setText("Cliente");
                mainLabelString += "Venda ";
                primaryBtnString += "Venda ";
                searchTextField.setPromptText("Pesquisar cliente");
                break;
            }
        }

        mainActionScreenTitle.setText(mainLabelString);
        primaryBtn.setText(primaryBtnString);
        populatePersonTable();
    }

    private void setStageBreak() {
        stage.widthProperty().addListener((observable) -> {
            adjustComponents();
        });
    }

    private void adjustComponents() {
        if (stage.getWidth() > 1056) {
            ObservableList<Node> workingCollection = FXCollections.observableArrayList(vBox.getChildren());
            vBox.getChildren().clear();
            for (Node n : workingCollection) {
                hBox.getChildren().add(n);
                hBox.setHgrow(n, Priority.ALWAYS);
            }
            hBox.toFront();
        } else {
            ObservableList<Node> workingCollection = FXCollections.observableArrayList(hBox.getChildren());
            hBox.getChildren().clear();
            for (Node n : workingCollection) {
                vBox.getChildren().add(n);
            }
            vBox.toFront();
        }
    }

    @Override
    public void fillScreen() {
        FinalPrice.setText(String.valueOf(record.getTotalprice()));
//        customerOrSupplier.setText(record.getCustomer().getName());

        populateTable();
    }

    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    private void register() {
        if (validate()) {
            try {
                Record newRecord = new Record(Globals.getInstance().getEmployee(), clientTable.getSelectionModel().getSelectedItem(), transactions, type == BUY ? RecordTypeConstants.PURCHASE : RecordTypeConstants.SALE);
                RecordDAO.insertRecord(newRecord);
                GUIController.getInstance().showRegisterAlert("Transação");
                GUIController.getInstance().backToPrevious();
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de servidor", "Tente novamente!");
            } catch (OutOfStockException ex) {
                GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro na transação", "Erro no produto " + ex.getOutProduct().getName() + ", selecione uma quantidade menor");
            }
        }
    }

    @FXML
    private void showModalAddService() {
        Transaction service = GUIController.getInstance().callModalForResult(ModalType.SERVICE_NEW);
        if (service != null) {
            transactions.add(service);
            populateTable();
            updatePrice();
        }
    }

    @FXML
    private void showModalAddProduct() {
        Transaction product = GUIController.getInstance().callModalForResult(ModalType.PRODUCT_ADD);
        if (product != null) {
            transactions.add(product);
            populateTable();
            updatePrice();
        }
    }

    @FXML
    private void deleteEntry() {
        Transaction transaction = transactionsTable.getSelectionModel().getSelectedItem();
        if (transaction == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            transactions.remove(transaction);
            populateTable();
            updatePrice();
        }
    }

    private void setTransactionType(TransactionType type) {
        this.type = type;
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.emptyTableSelection(clientTable, type == BUY ? "Fornecedor" : "Cliente");
        valObj.emptyTransactionTable(transactionsTable);

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }

    private void updatePrice() {
        float total = 0.0f;
        for (Transaction t : transactions) {
            total += t.getPrice();
        }
        FinalPrice.setText(Misc.changeToComma(String.valueOf(total)));
    }

    @Override
    public void setUpComponents() {
        // nothing happen here
    }

    @Override
    public void setScreenObject(Object obj) {
        this.record = (Record) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.TRANSACTION_SCREEN;
    }
}
