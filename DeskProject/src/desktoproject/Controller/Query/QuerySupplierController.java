/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import Exceptions.OperationNotAllowed;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Animation;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class QuerySupplierController implements Initializable {

    private static final String querySupplierPath = "desktoproject/View/Query/QuerySupplier.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(QuerySupplierController.class.getClassLoader().getResource(querySupplierPath));
        return loader.load();
    }

    @FXML
    private TableView<Supplier> suppliersTable;
    @FXML
    private TableColumn<Supplier, String> cnpjColumn;
    @FXML
    private TableColumn<Supplier, String> nameColumn;
    @FXML
    private TableColumn<Supplier, String> brandsColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button detailsBtn;
    @FXML
    private Button newBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindAnimation(searchTextField);
        Animation.bindShadowAnimation(newBtn);
        Animation.bindShadowAnimation(detailsBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(deleteBtn);

        suppliersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cnpjColumn.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Supplier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Supplier, String> p) {
                List<String> brands = new LinkedList<>();
                p.getValue().getAvaliableBrands().forEach((b) -> {
                    brands.add(b.getName());
                });
                return new SimpleStringProperty(String.join(", ", brands));
            }
        });

        populateTable();
        setTableAction();
        setUpSearch();
    }

    private void setUpSearch() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.trim();
            if (newValue.isEmpty()) {
                populateTable();
            } else {
                try {
                    suppliersTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(PersonDAO.searchSuppliers(newValue))));

                } catch (RemoteException | DatabaseErrorException ex) {

                }
            }
        });
    }

    public void populateTable() {
        try {
            suppliersTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllSuppliers()));
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }

    }

    private void setTableAction() {
        suppliersTable.setRowFactory(tv -> {
            TableRow<Supplier> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Supplier supplier = row.getItem();
                    GUIController.getInstance().callScreen(ScreenType.SUPPLIER_DISPLAY, supplier);
                }
            });
            return row;
        });
    }

    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    private void createNew() {
        GUIController.getInstance().callScreen(ScreenType.SUPPLIER_CREATE);
    }

    @FXML
    private void editSupplier() {
        Supplier supplier = suppliersTable.getSelectionModel().getSelectedItem();
        if (supplier == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            GUIController.getInstance().callScreen(ScreenType.SUPPLIER_DISPLAY, supplier);
        }
    }

    @FXML
    private void delete() {
        Person person = suppliersTable.getSelectionModel().getSelectedItem();
        if (person == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            try {
                if (GUIController.getInstance().showEraseConfirmationAlert(person.getName())) {
                    PersonDAO.deletePerson(person);
                }
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (NoResultsException ex) {
                GUIController.getInstance().showDeleteErrorAlert();
            } catch (OperationNotAllowed ex) {
                GUIController.getInstance().showOperationNotAllowed();
            }
        }
    }
}
