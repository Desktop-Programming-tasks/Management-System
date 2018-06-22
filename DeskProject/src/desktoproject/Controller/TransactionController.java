/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Interfaces.ControllerEdit;
import Classes.Constants.RecordTypeConstants;
import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Service;
import Classes.Transactions.ServiceType;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.TransactionType;
import static desktoproject.Controller.Enums.TransactionType.BUY;
import static desktoproject.Controller.Enums.TransactionType.SALE;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Globals;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Model.DAO.Transactions.RecordDAO;
import desktoproject.Reports.RecordReport;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import desktoproject.Utils.Pairs.ScreenData;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class TransactionController extends ControllerEdit implements Initializable, TableScreen, AppObserver {

    public ScreenData call(TransactionType type) throws IOException {
        ScreenData callReturn = super.call();
        TransactionController controller = (TransactionController) callReturn.getController();
        controller.setTransactionType(type);
        controller.setTypeComponents();
        controller.subscribe();
        return new ScreenData(callReturn.getParent(), controller);
    }

    public ScreenData call(TransactionType type, Object obj) throws IOException {
        ScreenData callReturn = super.call(obj);
        TransactionController controller = (TransactionController) callReturn.getController();
        controller.setScreenObject(obj);
        controller.setTransactionType(type);
        controller.setTypeComponents();
        controller.subscribe();
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
    private Button relatoryBtn;

    @FXML
    private Label mainActionScreenTitle;
    @FXML
    private Label FinalPrice;
    @FXML
    private Label tableLabel;
    @FXML
    private TextField searchTextField;
    @FXML
    private Label clientName;
    @FXML
    private Label clientDocument;
    @FXML
    private Label employeeName;
    @FXML
    private Label employeeDocument;

    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    @FXML
    private HBox hBoxContainer;
    @FXML
    private AnchorPane clientPane;
    @FXML
    private AnchorPane clientPaneDisplay;

    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> nameColumn;
    @FXML
    private TableColumn<Transaction, Float> priceColumn;
    @FXML
    private TableColumn<Transaction, String> quantityColumn;

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
        Animation.bindShadowAnimation(relatoryBtn);
        Animation.bindAnimation(searchTextField);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory((TableColumn.CellDataFeatures<Transaction, String> p) -> {
            return new SimpleStringProperty(p.getValue().getClass()==Service.class?("---"):(String.valueOf(p.getValue().getQuantity())));
        });

        clientDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("documentId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        transactions = new ArrayList<>();

        Platform.runLater(() -> {
            stage = (Stage) mainActionScreenTitle.getScene().getWindow();
            setStageBreak();
            adjustComponents();
        });

        populateTable();
        setTableAction();
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
            Person selectedPerson = clientTable.getSelectionModel().getSelectedItem();
            if (type == BUY) {
                clientTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllSuppliers()));
            } else {
                clientTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllPersons()));
            }
            selectTable(selectedPerson);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {

        }
    }

    @Override
    public void selectTable(Object o) {
        if (o != null) {
            Person cp = (Person) o;
            for (Person p : clientTable.getItems()) {
                if (p.getId() == cp.getId()) {
                    clientTable.getSelectionModel().select(p);
                }
            }
        }
    }

    @Override
    public void populateTable() {
        transactionsTable.setItems(FXCollections.observableArrayList(transactions));
    }

    @Override
    public void setTableAction() {
        transactionsTable.setRowFactory(tv -> {
            TableRow<Transaction> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    int position = row.getIndex();
                    Transaction transaction = transactions.get(position);
                    if (transaction.getClass() == Product.class) {
                        if (isEdit()) {
                            //do nothing
                        } else {
                            transaction = GUIController.getInstance().callModalForResult(ModalType.PRODUCT_ADD_EDIT, transaction);
                            if (transaction != null) {
                                transactions.remove(position);
                                transactions.add(transaction);
                            }
                        }
                    } else {
                        if (isEdit()) {
                            GUIController.getInstance().callModal(ModalType.SERVICE_UPDATE, transaction);
                        } else {
                            transaction = GUIController.getInstance().callModalForResult(ModalType.SERVICE_NEW_EDIT, transaction);
                            if (transaction != null) {
                                transactions.remove(position);
                                transactions.add(transaction);
                            }
                        }
                    }
                    populateTable();
                }
            });
            return row;
        });
    }

    private void setTypeComponents() {
        String mainLabelString = "", primaryBtnString = "Finalizar ";

        if (isEdit()) {
            mainLabelString += "Consultar ";
            primaryBtn.setVisible(false);
            relatoryBtn.setVisible(true);
            clientTable.setDisable(true);
//            transactionsTable.setDisable(true);
            addProductBtn.setVisible(false);
            addServiceBtn.setVisible(false);
            deleteEntry.setVisible(false);
            searchTextField.setDisable(true);
            this.transactions = record.getTransations();
            clientPane.setVisible(false);
            clientPaneDisplay.setVisible(true);
            fillScreen();
        } else { 
            primaryBtn.setVisible(true);
            relatoryBtn.setVisible(false);
            this.transactions = new ArrayList<>();
            clientPane.setVisible(true);
            clientPaneDisplay.setVisible(false);
        }

        switch (type) {
            case BUY: {
                tableLabel.setText("Fornecedor");
                mainLabelString += "Compra ";
                primaryBtnString += "Compra ";
                searchTextField.setPromptText("Pesquisar fornecedor");
                addServiceBtn.setVisible(false);
                hBoxContainer.getChildren().remove(addServiceBtn);
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
        FinalPrice.setText(Misc.changeToComma(String.valueOf(record.getTotalprice())));
        transactions = record.getTransations();
        populateTable();
        Employee assigned = record.getAssignedEmployee();
        Person person = record.getCustomer();
        clientName.setText(person.getName());
        clientDocument.setText(person.getDocumentId());
        employeeName.setText(assigned.getName());
        employeeDocument.setText(assigned.getDocumentId());
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
                System.out.println(newRecord.getTransations().toString());
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
        Transaction product = GUIController.getInstance().callModalForResult(ModalType.PRODUCT_ADD, type);
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
        // nothing happens here
    }

    @Override
    public void setScreenObject(Object obj) {
        this.record = (Record) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.TRANSACTION_SCREEN;
    }

    @Override
    public void update() {
        populatePersonTable();
    }

    @Override
    public void subscribe() {
        if (type == BUY) {
            ObservableServer.getClient().addObserver(this);
        } else {
            ObservableServer.getSupplier().addObserver(this);
        }
    }
    
    @FXML
    private void relatory(){
        try {
            new RecordReport(record).generateReport();
        } catch (JRException ex) {
        }
    }
}
