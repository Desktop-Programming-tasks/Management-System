/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Model.Classes.Persons.Supplier;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierController implements Initializable {
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SupplierController.class.getClassLoader().getResource("desktoproject/View/Register/SupplierRegister.fxml"));        
        return loader.load();
    }
    
    public static Parent call(Object supplier) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource("desktoproject/View/Register/SupplierRegister.fxml"));        
        Parent p = loader.load();
        
        SupplierController controller = loader.getController();
        controller.setSupplier((Supplier) supplier);
        controller.setEdit(true);
        controller.setUpComponents();
         
        return p;
    }
    
    private Supplier supplier;
    private boolean edit;
    
    private void setUpComponents() {
        if(edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Fornecedor");
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Fornecedor");
        }
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
    private TableView brandTable;
    
    @FXML
    private TableColumn Brands;
    
    @FXML
    private ComboBox<String> City;
    @FXML
    private ComboBox<String> State;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @FXML 
    public void register(){
        
    }
 
    @FXML
    public void back() {
        
    }
    
    @FXML
    private void createNewBrand() {
        
    }
    
    @FXML
    private void addBrand() {
        
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
