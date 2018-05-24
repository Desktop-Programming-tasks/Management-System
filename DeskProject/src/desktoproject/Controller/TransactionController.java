/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Enums.TransactionType;
import desktoproject.Model.Classes.Transactions.Record;
import desktoproject.Model.Classes.Transactions.Transaction;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.utils.TableProxyTransation;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class TransactionController implements Initializable {
    private static final String transactionPath = "desktoproject/View/Transaction.fxml";
    //call to create a new transaction
    public static Parent call(TransactionType type) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TransactionController.class.getClassLoader().getResource(transactionPath));
        Parent p = loader.load();
        TransactionController controller = loader.getController();
        controller.setTransactionType(type);
        controller.setEdit(false);
        controller.setComponents();
        return p;
    }

    //call to display information about an existing transaction
    public static Parent call(TransactionType type, Object obj) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TransactionController.class.getClassLoader().getResource(transactionPath));
        Parent p = loader.load();
        TransactionController controller = loader.getController();
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
    private TextField customerOrSupplier;

    @FXML
    private TableView transactionsTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn quantityColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory("quantity"));
    }

    private void populateTable() {
        transactionsTable.setItems(FXCollections.observableArrayList(transactions));
    }
    

    private void setComponents() {
        String mainLabelString = "",primaryBtnString = "Finalizar ";
        
        if (edit) {
            mainLabelString += "Consultar ";
            primaryBtn.setVisible(false);
            this.transactions = record.getTransations();
            fillScreen();
        }else{
            primaryBtn.setVisible(true);
            this.transactions = new ArrayList<>();
        }
        
        switch (type) {
            case BUY: {
                mainLabelString += "Compra ";
                primaryBtnString += "Compra ";
                break;
            }
            case SALE: {
                mainLabelString += "Venda ";
                primaryBtnString += "Venda ";
                break;
            }
        }
        
        mainActionScreenTitle.setText(mainLabelString);
        primaryBtn.setText(primaryBtnString);
    }
    
    private void fillScreen() {
        FinalPrice.setText(String.valueOf(record.getTotalprice()));
        customerOrSupplier.setText(record.getCustomer().getName());
        
        populateTable();
    }

    @FXML
    private void back() {

    }

    @FXML
    private void register() {

    }

    @FXML
    private void showModalAddService() {

    }

    @FXML
    private void showModalAddProduct() {

    }

    @FXML
    private void deleteEntry() {

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
}
