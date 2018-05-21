/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Model.Classes.Transactions.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class ProductController implements Initializable {

    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProductController.class.getClassLoader().getResource("desktoproject/View/Register/ProductRegister.fxml"));        
        return loader.load();
    }
    
    public static Parent call(Object product) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource("desktoproject/View/Register/ProductRegister.fxml"));        
        Parent p = loader.load();
        
        ProductController controller = loader.getController();
        controller.setProduct((Product) product);
        controller.setEdit(true);
        controller.setUpComponents();
        
        return p;
    }
    
    private Product product;
    private boolean edit;
    
    private void setUpComponents() {
        if(edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Produto");
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Produto");
        }
    }
    
    @FXML
    private Button mainBtn;
    @FXML 
    private Label mainLabel;
    @FXML
    private TextField barCodeTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Group quantityGroup;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    
    @FXML
    private TableView TableBrands;
    
    @FXML
    private TableColumn Brands;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
    @FXML
    public void back() {
        
    }
    
    @FXML
    private void register(){
        
    }
    
    @FXML
    private void createNewBrand() {
        
    }
    
    @FXML
    private void addBrand() {
        
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
