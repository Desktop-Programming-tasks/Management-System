/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.register;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.GUIController;
import main.objects.transations.Brand;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class ProductRegisterController implements Initializable {

    @FXML
    private Button actionBtn;
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
    private TableView TableBrands;
    
    @FXML
    private TableColumn<Brand,String> Brands;
    
    ObservableList<Brand> brands;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        brands= FXCollections.observableArrayList();
        brands.add(new Brand(1,"Nvidia"));
        brands.add(new Brand(2,"AMD"));
        brands.add(new Brand(3,"Intel"));
        brands.add(new Brand(4,"Corsair"));
        brands.add(new Brand(5,"XFX"));
        
        Brands.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableBrands.setItems(brands);
        
    }    
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void register(){
        if(Validate.validateEmpty("Código de barras", barCodeTextField.getText())){
            Validate.validateAddressNumber(barCodeTextField.getText());
        }
        if(Validate.validateEmpty("Nome", nameTextField.getText())){
            Validate.validateName(nameTextField.getText());
        }
        Validate.emptyTable(TableBrands);
        
        String errorMsg = Validate.getErrorMessage();
        System.out.println(errorMsg);
        if(errorMsg.isEmpty()){
            System.out.println("foi");
        }
    }
    
    public void setEdit(){
        //change labels and load data from object
        actionBtn.setText("Salvar");
        mainLabel.setText("Detalhes do Produto");
        
        quantityGroup.setVisible(true);
        quantityTextField.setText("99");
        
        barCodeTextField.setText("13249842313");
        nameTextField.setText("Produto 12346");
    }
}
