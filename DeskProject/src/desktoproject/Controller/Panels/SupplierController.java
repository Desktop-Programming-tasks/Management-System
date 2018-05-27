/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Persons.Address;
import Classes.Persons.Supplier;
import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Model.DAO.Transactions.BrandDAO;
import desktoproject.Utils.Pairs.ScreenObject;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
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

    private static final String PATH = "desktoproject/View/Panels/Supplier.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SupplierController.class.getClassLoader().getResource(PATH));
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
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(PATH));
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
    private ScreenObject addressComponent;
    private ScreenObject telephoneComponent;

    private void setUpComponents() {
        addressPane.getChildren().clear();
        addressPane.getChildren().add(addressComponent.getParent());
        telephonePane.getChildren().clear();
        telephonePane.getChildren().add(telephoneComponent.getParent());
        populateTable();
        if (edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Fornecedor");

            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Fornecedor");
        }
    }

    private void fillScreen() {
        nameTextField.setText(supplier.getName());
        CNPJTextField.setText(supplier.getCNPJ());
        
        selectSupplierBrands();
    }
    
    private void selectSupplierBrands(){
        ArrayList<Brand> brands = new ArrayList<>(brandsTable.getItems());
        
        for(Brand tableBrand : brands){
            for(Brand supplierBrand : supplier.getAvaliableBrands()){
                if(tableBrand.getName().equals(supplierBrand.getName())){
                    brandsTable.getSelectionModel().select(tableBrand);
                }
            }
        }
    }
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField CNPJTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private TableView<Brand> brandsTable;
    @FXML
    private AnchorPane addressPane;
    @FXML
    private TableColumn<Brand, String> nameColumn;
    @FXML
    private AnchorPane telephonePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        populateTable();
    }

    //return all brands from database
    private void populateTable() {
        try {
            brandsTable.setItems(FXCollections.observableArrayList(BrandDAO.queryAllBrands()));
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            Address address = ((AddressComponentController) addressComponent.getController()).getAddress();
            ArrayList<String> telephone = ((TelephoneComponentController) telephoneComponent.getController()).getTelephones();
            ArrayList<Brand> brands = new ArrayList<>(brandsTable.getSelectionModel().getSelectedItems());
            Supplier supplier = new Supplier(brands, nameTextField.getText(), address, telephone, CNPJTextField.getText());

            try {
                if (edit) {

                } else {
                    PersonDAO.insertPerson(supplier);
                    GUIController.getInstance().showRegisterAlert("Fornecedor");
                    GUIController.getInstance().backToIndex();
                }
            } catch (RemoteException|DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Fornecedor", "CNPJ");
            } catch (DuplicatedLoginException ex) {
                //no login inserting costumer
            }
        }
    }

    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    private void createNewBrand() {
        GUIController.getInstance().callModal(ModalType.BRAND_NEW);
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
        this.addressComponent = addressComponentObj;
    }

    private void setTelephoneComponent(ScreenObject telephoneComponent) {
        this.telephoneComponent = telephoneComponent;
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.validateName(nameTextField.getText());
        valObj.validateCNPJ(CNPJTextField.getText());

        valObj.appendErrorMessage(((TelephoneComponentController) telephoneComponent.getController()).validateFields());

        valObj.appendErrorMessage(((AddressComponentController) addressComponent.getController()).validateFields());

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
