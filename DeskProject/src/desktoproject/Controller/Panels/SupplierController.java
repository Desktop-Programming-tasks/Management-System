/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Persons.Address;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.Supplier;
import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Interfaces.ControllerPromotion;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Model.DAO.Transactions.BrandDAO;
import desktoproject.Utils.Animation;
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
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierController extends ControllerPromotion implements Initializable,TableScreen,AppObserver {
//
//    private static final String PATH = "desktoproject/View/Panels/Supplier.fxml";
//
//    public static Parent call() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(SupplierController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        SupplierController controller = loader.getController();
//        controller.setAddressComponentObj(AddressComponentController.call());
//        controller.setTelephoneComponent(TelephoneComponentController.call());
//        controller.setAnchors(p);
//        controller.setEdit(false);
//        controller.setUpComponents();
//        return p;
//    }
//
//    public static Parent call(Object supplier, boolean promote) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(EmployeeController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//
//        SupplierController controller = loader.getController();
//        if (supplier == null) {
//            System.out.println("mas pq?");
//        }
//        controller.setSupplier((Supplier) supplier);
//        controller.setAddressComponentObj(AddressComponentController.call(controller.getSupplier().getAddress()));
//        controller.setTelephoneComponent(TelephoneComponentController.call(controller.getSupplier().getTelephones()));
//        controller.setAnchors(p);
//        controller.setEdit(true);
//        controller.setPromote(promote);
//        controller.setUpComponents();
//        return p;
//    }
//
//    private void setAnchors(Parent p) {
//        AnchorPane.setTopAnchor(p, 0.0);
//        AnchorPane.setLeftAnchor(p, 0.0);
//        AnchorPane.setBottomAnchor(p, 0.0);
//        AnchorPane.setRightAnchor(p, 0.0);
//    }

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField CNPJTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button createBrand;
    @FXML
    private TableView<Brand> brandsTable;
    @FXML
    private AnchorPane addressPane;
    @FXML
    private TableColumn<Brand, String> nameColumn;
    @FXML
    private AnchorPane telephonePane;

    private Supplier supplier;

    @Override
    public void setUpComponents() {
        populateTable();
        if (isEdit()) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Fornecedor");

            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Fornecedor");
        }
        if (isPromote()) {
            mainLabel.setText("Promover a fornecedor");
            mainBtn.setText("Salvar");
        }
    }

    @Override
    public void fillScreen() {
        nameTextField.setText(supplier.getName());
        CNPJTextField.setText(supplier.getCNPJ());

        selectSupplierBrands();
    }

    private void selectSupplierBrands() {
        for(Brand b : supplier.getAvaliableBrands()){
            selectTable(b);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Animation.bindAnimation(nameTextField);
        Animation.bindAnimation(CNPJTextField);
        Animation.bindShadowAnimation(mainBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(createBrand);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        populateTable();
        subscribe();
    }

    //return all brands from database
    @Override
    public void populateTable() {
        try {
            Brand selectedBrand = brandsTable.getSelectionModel().getSelectedItem();
            brandsTable.setItems(FXCollections.observableArrayList(BrandDAO.queryAllBrands()));
            selectTable(selectedBrand);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
    }
    
    @Override
    public void selectTable(Object o) {
        if (o != null) {
            Brand cb = (Brand)o;
            for (Brand b : brandsTable.getItems()) {
                if (b.getId() == cb.getId()) {
                    brandsTable.getSelectionModel().select(b);
                }
            }
        }
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            Address address = ((AddressComponentController) getAddressComponent().getController()).getAddress();
            ArrayList<String> telephone = ((TelephoneComponentController) getTelephoneComponent().getController()).getTelephones();
            ArrayList<Brand> brands = new ArrayList<>(brandsTable.getSelectionModel().getSelectedItems());
            Supplier newSupplier = new Supplier(brands, nameTextField.getText(), address, telephone, CNPJTextField.getText());

            try {
                if (isPromote()) {
                    newSupplier.setId(supplier.getId());
                    newSupplier.setActive(supplier.isActive());
                    PersonDAO.promoteSupplier(supplier);
                    GUIController.getInstance().showPromoteAlert("Fornecedor");
                    GUIController.getInstance().backToPrevious();
                } else {
                    if (isEdit()) {
                        newSupplier.setId(supplier.getId());
                        newSupplier.setActive(supplier.isActive());
                        PersonDAO.updatePerson(newSupplier);
                        GUIController.getInstance().showUpdateAlert();
                        GUIController.getInstance().backToPrevious();
                    } else {
                        PersonDAO.insertPerson(newSupplier);
                        GUIController.getInstance().showRegisterAlert("Fornecedor");
                        GUIController.getInstance().backToPrevious();
                    }
                }
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Fornecedor", "CNPJ");
            } catch (DuplicatedLoginException ex) {
                //no login inserting costumer
            } catch (NoResultsException ex) {
                GUIController.getInstance().showUpdateErrorAlert();
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
        populateTable();
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.validateName(nameTextField.getText());
        valObj.validateCNPJ(CNPJTextField.getText());

        valObj.appendErrorMessage(((TelephoneComponentController) getTelephoneComponent().getController()).validateFields());

        valObj.appendErrorMessage(((AddressComponentController) getAddressComponent().getController()).validateFields());

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }

    @Override
    public void setDynamicSecondary() {
        addressPane.getChildren().clear();
        addressPane.getChildren().add(getAddressComponent().getParent());
        telephonePane.getChildren().clear();
        telephonePane.getChildren().add(getTelephoneComponent().getParent());
    }

    @Override
    public void setScreenObject(Object obj) {
        if(obj instanceof Supplier) {
            this.supplier = (Supplier) obj;
        } else {
            this.supplier = new Supplier((JuridicalPerson) obj);
            setPromote(true);
        }
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.SUPPLIER_SCREEN;
    }

    @Override
    public void setTableAction() {
        //
    }

    @Override
    public void setUpSearch() {
        //
    }

    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.getBrand().addObserver(this);
    }
}
