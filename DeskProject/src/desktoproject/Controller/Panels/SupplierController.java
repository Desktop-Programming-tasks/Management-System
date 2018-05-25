/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Model.Classes.Persons.Supplier;
import desktoproject.Model.Classes.Transactions.Brand;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierController implements Initializable {
    
    private static final String panelSupplierPath = "desktoproject/View/Panels/Supplier.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SupplierController.class.getClassLoader().getResource(panelSupplierPath));        
        Parent p = loader.load();
        SupplierController controller = loader.getController();
        controller.setUpComponents();
        return p;
    }
    
    public static Parent call(Object supplier) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(panelSupplierPath));        
        Parent p = loader.load();
        
        SupplierController controller = loader.getController();
        controller.setSupplier((Supplier) supplier);
        controller.setEdit(true);
        controller.setUpComponents();
         
        return p;
    }
    
    private Supplier supplier;
    private boolean edit;
    private ArrayList<Brand> brands;
    
    private void setUpComponents() {
        if(edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Fornecedor");
            
            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Fornecedor");
            brands = new ArrayList<>();
        }
    }
    
    private void fillScreen() {
        nameTextField.setText(supplier.getName());
        CNPJTextField.setText(supplier.getCNPJ());
        telTextField.setText(supplier.getTelephones().get(0));
        if(supplier.getTelephones().get(1) != null) {
            secTelTextField.setText(supplier.getTelephones().get(1));
        }
        streetTextField.setText(supplier.getAddress().getStreet());
        numberTextField.setText(String.valueOf(supplier.getAddress().getNumber()));
        districtTextField.setText(supplier.getAddress().getBlock());
        populateTable();
    }
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField CNPJTextField;
    @FXML
    private TextField telTextField;
    @FXML
    private TextField secTelTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField districtTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private TableView BrandsTable;
    
    @FXML
    private TableColumn BrandsColumn;
    
    @FXML
    private ComboBox<String> City;
    @FXML
    private ComboBox<String> State;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BrandsColumn.setCellValueFactory(new PropertyValueFactory("name"));
    }    
    
    //return all brands from database
    private void populateTable() {
        //BrandsTable.setItems(FXCollections.observableArrayList(brands));
    }
    
    @FXML 
    public void register(){
        
    }
 
    @FXML
    public void back() {
        
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
