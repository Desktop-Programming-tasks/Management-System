/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;


import Classes.Enums.EmployeeType;
import Classes.Persons.Address;
import Classes.Persons.Employee;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Classes.Transactions.Brand;
import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Transaction;
import desktoproject.Controller.Enums.TransactionType;
import static desktoproject.Controller.Enums.TransactionType.BUY;
import static desktoproject.Controller.Enums.TransactionType.SALE;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class TransactionController implements Initializable {
    private static final String transactionPath = "desktoproject/View/Transaction.fxml";
    private Stage stage;
    //call to create a new transaction
    public static Parent call(TransactionType type,Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TransactionController.class.getClassLoader().getResource(transactionPath));
        Parent p = loader.load();
        TransactionController controller = loader.getController();
        controller.stage = stage;
        controller.setTransactionType(type);
        controller.setEdit(false);
        controller.setComponents();
        return p;
    }

    //call to display information about an existing transaction
    public static Parent call(TransactionType type, Object obj,Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TransactionController.class.getClassLoader().getResource(transactionPath));
        Parent p = loader.load();
        TransactionController controller = loader.getController();
        controller.stage = stage;
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
        
        populatePersonTable();
    }
    
    private void populatePersonTable(){
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
        Person[] personArray = {juridicalPerson,supplierTest,legalPerson,employee};
//        ArrayList<Person> test = new ArrayList<>();
        
        
        clientTable.setItems(FXCollections.observableArrayList(personArray));
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
    
    private boolean validate(){
//        customerOrSupplier.getText();
        return true;
    }
}
