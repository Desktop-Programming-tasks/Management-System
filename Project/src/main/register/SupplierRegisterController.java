/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.register;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.GUIController;
import main.objects.persons.Address;
import main.objects.persons.Supplier;
import main.objects.transations.Brand;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierRegisterController implements Initializable {
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
    private Button actionBtn;
    @FXML
    private TableView<Brand> brandTable;
    
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
        brandTable.setItems(brands);
    }    
    
    @FXML 
    public void register(){
        if(Validate.validateEmpty("'Nome do Cliente'", nameTextField.getText())) {
            Validate.validateName(nameTextField.getText());
        }
        if(Validate.validateEmpty("'CNPJ'", CNPJTextField.getText())) {
            Validate.validateCNPJ(CNPJTextField.getText());
        }
        if(Validate.validateEmpty("'Telefone principal'", telTextField.getText())) {
            Validate.validateTelephone(telTextField.getText());
        }
        Validate.validateEmpty("'Rua'", streetTextField.getText());
        Validate.validateEmpty("'Bairro'", districtTextField.getText());
        if(Validate.validateEmpty("'Número da casa'", numberTextField.getText())) {
            Validate.validateAddressNumber(numberTextField.getText());   
        }
        Validate.emptyTable(brandTable);
        
        GUIController.getInstance().showInformationAlert(Validate.getErrorMessage());
        if(Validate.getErrorMessage().isEmpty()){
             Address address = new Address(streetTextField.getText(),
                    Integer.parseInt(numberTextField.getText()), districtTextField.getText(),
                    "dummy", "dummy");
            ArrayList<String> telephones = new ArrayList<>();
            telephones.add(telTextField.getText());
            if (!secTelTextField.getText().isEmpty()) {
                telephones.add(secTelTextField.getText());
            }
            ArrayList<Brand> avaliableBrands = new ArrayList<>();
            avaliableBrands.add(new Brand(1,"AMD"));
            Supplier supplier = new Supplier(avaliableBrands, nameTextField.getText(), address,
                    telephones, CNPJTextField.getText());
            System.out.println(supplier);
        }
    }
 
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void createNewBrand() {
        GUIController.getInstance().showModalRegisterBrand();
    }
    
    @FXML
    private void addBrand() {
        GUIController.getInstance().showModalAddBrand();
    }
    
    public void setEdit(){
        nameTextField.setText("Placeholder name 1");
        telTextField.setText("(43)98756-5498");
        streetTextField.setText("Placeholder street");
        numberTextField.setText("245");
        districtTextField.setText("District 13");
        
        mainLabel.setText("Detalhes de Fornecedor");
        actionBtn.setText("Salvar"); 
    }
}
