/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Model.Classes.Persons.Supplier;
import desktoproject.Model.Classes.Transactions.Brand;
import desktoproject.Utils.Pairs.ScreenObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierController implements Initializable {

    private static final String panelSupplierPath = "desktoproject/View/Panels/Supplier.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SupplierController.class.getClassLoader().getResource(panelSupplierPath));
        Parent p = loader.load();
        SupplierController controller = loader.getController();
        controller.setAddressComponentObj(AddressComponentController.call());
        controller.setTelephoneComponent(TelephoneComponentController.call());
        controller.setAnchors(p);
        controller.setEdit(false);
        controller.setUpComponents();
        return p;
    }

    public static Parent call(Object supplier) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(panelSupplierPath));
        Parent p = loader.load();

        SupplierController controller = loader.getController();
        controller.setSupplier((Supplier) supplier);
        controller.setAddressComponentObj(AddressComponentController.call(controller.getSupplier().getAddress()));
        controller.setTelephoneComponent(TelephoneComponentController.call(controller.getSupplier().getTelephones()));
        controller.setAnchors(p);
        controller.setEdit(true);
        controller.setUpComponents();
        return p;
    }

    private void setAnchors(Parent p) {
        AnchorPane.setTopAnchor(p, 0.0);
        AnchorPane.setLeftAnchor(p, 0.0);
        AnchorPane.setBottomAnchor(p, 0.0);
        AnchorPane.setRightAnchor(p, 0.0);
    }

    private Supplier supplier;
    private boolean edit;
    private ArrayList<Brand> brands;
    private ScreenObject addressComponentObj;
    private ScreenObject telephoneComponent;

    private void setUpComponents() {
        addressPane.getChildren().clear();
        addressPane.getChildren().add(addressComponentObj.getParent());
        telephonePane.getChildren().clear();
        telephonePane.getChildren().add(telephoneComponent.getParent());
        if (edit) {
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
        if (supplier.getTelephones().get(1) != null) {
            secTelTextField.setText(supplier.getTelephones().get(1));
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
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private TableView<Brand> BrandsTable;
    @FXML
    private AnchorPane addressPane;
    @FXML
    private TableColumn<Brand, String> BrandsColumn;
    @FXML
    private AnchorPane telephonePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BrandsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    //return all brands from database
    private void populateTable() {
        //BrandsTable.setItems(FXCollections.observableArrayList(brands));
    }

    @FXML
    private void register() {

    }

    @FXML
    private void back() {

    }
    
    @FXML
    private void createNewBrand(){
    
    }

    private void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    private Supplier getSupplier() {
        return supplier;
    }
    
    private void setEdit(boolean edit) {
        this.edit = edit;
    }

    private void setAddressComponentObj(ScreenObject addressComponentObj) {
        this.addressComponentObj = addressComponentObj;
    }
    
    public void setTelephoneComponent(ScreenObject telephoneComponent) {
        this.telephoneComponent = telephoneComponent;
    }
}
