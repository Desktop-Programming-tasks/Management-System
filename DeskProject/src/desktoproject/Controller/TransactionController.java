/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Enums.TransactionType;
import desktoproject.Model.Classes.Transactions.Record;
import java.io.IOException;
import java.net.URL;
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
        controller.setTransactionType(type);
        controller.setEdit(true);
        controller.setComponents();
        return p;
    }

    private TransactionType type;
    private boolean edit;
    private Record register;

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
    private TableView Transations;
    @FXML
    private TableColumn Name;
    @FXML
    private TableColumn Price;
    @FXML
    private TableColumn Quantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //BrandsColumn.setCellValueFactory(new PropertyValueFactory("name"));
        
    }

    private void populateTable() {
        //BrandsTable.setItems(FXCollections.observableArrayList(brands));
    }
    

    private void setComponents() {
        switch (type) {
            case BUY: {
                mainActionScreenTitle.setText("Compra");
                primaryBtn.setText("Finalizar compra");
                break;
            }
            case SALE: {
                mainActionScreenTitle.setText("Venda");
                primaryBtn.setText("Finalizar venda");
                break;
            }
        }
        if (edit) {
            primaryBtn.setDisable(true);
            fillScreen();
        }
    }
    
    private void fillScreen() {
        FinalPrice.setText(register.getTotalprice());
        customerOrSupplier.setText(register.getCustomer().getName());
    
        
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

    private void setRegister(Record register) {
        this.register = register;
    }

    private void setTransactionType(TransactionType type) {
        this.type = type;
    }

    private void setEdit(boolean edit) {
        this.edit = edit;
    }
}
