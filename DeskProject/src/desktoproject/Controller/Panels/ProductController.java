/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Transactions.Brand;
import Classes.Transactions.Product;
import desktoproject.Controller.GUIController;
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
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class ProductController implements Initializable {

    private static final String panelProductPath = "desktoproject/View/Panels/Product.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProductController.class.getClassLoader().getResource(panelProductPath));        
        Parent p = loader.load();
        ProductController controller = loader.getController();
        controller.setUpComponents();
        controller.setEdit(false);
        return p;
    }
    
    public static Parent call(Object product) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(panelProductPath));        
        Parent p = loader.load();
        
        ProductController controller = loader.getController();
        controller.setProduct((Product) product);
        controller.setEdit(true);
        controller.setUpComponents();
        
        return p;
    }
    
    private Product product;
    private boolean edit;
    private Brand brand;
    
    private void setUpComponents() {
        if(edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Produto");
            brand = product.getBrand();
            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Produto");
            brand = null;
        }
        
        populateTable();
    }
    
    private void fillScreen() {
        nameTextField.setText(product.getName());
        priceTextField.setText(String.valueOf(product.getPrice()));
        barCodeTextField.setText(product.getBarCode());
    }
    
    @FXML
    private Button mainBtn;
    @FXML 
    private Label mainLabel;
    @FXML
    private Label brandLabel;
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
    private TableView<Brand> brandsTable;
    
    @FXML
    private TableColumn<Brand,String> brandsColumn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        brandsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void populateTable() {
        //alterar para pegar as brands do banco
//        System.out.println(brands);
//        BrandsTable.setItems(FXCollections.observableArrayList(brands));
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void register(){
        
    }
    
    @FXML
    private void createNewBrand() {
        
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
