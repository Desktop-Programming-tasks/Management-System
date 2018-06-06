/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;


import Classes.Enums.RecordType;
import Classes.Persons.Person;
import Classes.Transactions.Record;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.TransactionType;
import static desktoproject.Controller.Enums.TransactionType.BUY;
import static desktoproject.Controller.Enums.TransactionType.SALE;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
public class TransactionController implements Initializable {
    private static final String PATH = "desktoproject/View/Transaction.fxml";
    private Stage stage;
    //call to create a new transaction
    public static Parent call(TransactionType type,Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TransactionController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        TransactionController controller = loader.getController();
        controller.setStage(stage);
        controller.setTransactionType(type);
        controller.setEdit(false);
        controller.setComponents();
        return p;
    }

    //call to display information about an existing transaction
    public static Parent call(TransactionType type, Object obj,Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TransactionController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        TransactionController controller = loader.getController();
        controller.setStage(stage);
        controller.setRecord((Record) obj);
        controller.setTransactionType(type);
        controller.setEdit(true);
        controller.setComponents();
        return p;
    }

    private TransactionType type;
    private boolean edit;
    private Record record;
    private ArrayList<Transaction> transactions;

    @FXML
    private Button primaryBtn;
    @FXML
    private Button addProductBtn;
    @FXML
    private Button addServiceBtn;
    @FXML
    private Button deleteEntry;

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
    private TableColumn<Transaction,String> nameColumn;
    @FXML
    private TableColumn<Transaction,Float> priceColumn;
    @FXML
    private TableColumn<Transaction,Integer> quantityColumn;
    
    @FXML
    private TableView<Person> clientTable;
    @FXML
    private TableColumn<Person,String> clientDocumentColumn;
    @FXML
    private TableColumn<Person,String> clientNameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        
        clientDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        transactions = new ArrayList<>();
        
        populatePersonTable();
        populateTable();
    }
    
    private void populatePersonTable(){
        try {
            clientTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllPersons()));
        } catch (RemoteException|DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            
        }
    }

    private void populateTable() {
        transactionsTable.setItems(FXCollections.observableArrayList(transactions));
    }
    

    private void setComponents() {
        String mainLabelString = "",primaryBtnString = "Finalizar ";
        
        if (edit) {
            mainLabelString += "Consultar ";
            primaryBtn.setVisible(false);
            clientTable.setDisable(true);
            transactionsTable.setDisable(true);
            addProductBtn.setVisible(false);
            addServiceBtn.setVisible(false);
            deleteEntry.setVisible(false);
            this.transactions = record.getTransations();
            fillScreen();
        }else{
            primaryBtn.setVisible(true);
            this.transactions = new ArrayList<>();
        }
        
        switch (type) {
            case BUY: {
                tableLabel.setText("Fornecedor");
                mainLabelString += "Compra ";
                primaryBtnString += "Compra ";
                searchTextField.setText("Pesquisar fornecedor");
                break;
            }
            case SALE: {
                tableLabel.setText("Cliente");
                mainLabelString += "Venda ";
                primaryBtnString += "Venda ";
                searchTextField.setText("Pesquisar cliente");
                break;
            }
        }
        
        mainActionScreenTitle.setText(mainLabelString);
        primaryBtn.setText(primaryBtnString);
        setStageBreak();
        adjustComponents();
    }
    
    private void setStageBreak(){
        stage.widthProperty().addListener((observable) -> {
            adjustComponents();
        });
    }
    
    private void adjustComponents(){
        if(stage.getWidth()>1056){
                ObservableList<Node> workingCollection = FXCollections.observableArrayList(vBox.getChildren());
                vBox.getChildren().clear();
                for(Node n : workingCollection){
                    hBox.getChildren().add(n);
                    hBox.setHgrow(n, Priority.ALWAYS);
                }
                hBox.toFront();
            }else{
                ObservableList<Node> workingCollection = FXCollections.observableArrayList(hBox.getChildren());
                hBox.getChildren().clear();
                for(Node n : workingCollection){
                    vBox.getChildren().add(n);
                }
                vBox.toFront();
            }
    }
    
    private void fillScreen() {
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
        if(validate()){
            
        }
    }

    @FXML
    private void showModalAddService() {
        Transaction service = GUIController.getInstance().callModalForResult(ModalType.SERVICE_NEW);
        if(service!=null){
            transactions.add(service);
            populateTable();
        }
    }

    @FXML
    private void showModalAddProduct() {
        Transaction product = GUIController.getInstance().callModalForResult(ModalType.PRODUCT_ADD);
        if(product!=null){
            transactions.add(product);
            populateTable();
        }
    }

    @FXML
    private void deleteEntry() {
        Transaction transaction = transactionsTable.getSelectionModel().getSelectedItem();
        if(transaction==null){
            GUIController.getInstance().showSelectionErrorAlert();
        }else{
            transactions.remove(transaction);
            populateTable();
        }
    }

    private void setRecord(Record record) {
        this.record = record;
    }

    private void setTransactionType(TransactionType type) {
        this.type = type;
    }

    private void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private boolean validate(){
        Validate valObj = new Validate();
        
        valObj.emptyTableSelection(clientTable, type==BUY?"Fornecedor":"Cliente");
        valObj.emptyTransactionTable(transactionsTable);
        
        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
