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
import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.TextChangeListener;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class QuerySupplierController extends Controller implements Initializable, TableScreen, AppObserver {

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
        brandsColumn.setCellValueFactory((TableColumn.CellDataFeatures<Supplier, String> p) -> {
            List<String> brands = new LinkedList<>();
            p.getValue().getAvaliableBrands().forEach((b) -> {
                brands.add(b.getName());
            });
            return new SimpleStringProperty(String.join(", ", brands));
        });

        populateTable();
        setTableAction();
        setUpSearch();
        subscribe();
    }

    @Override
    public void setUpSearch() {
        searchTextField.textProperty().addListener(new TextChangeListener() {
            @Override
            public void runLogic(ObservableValue observable, Object oldValue, Object newValue) {
                newValue = ((String) newValue).trim();
                if (((String) newValue).isEmpty()) {
                    populateTable();
                } else {
                    try {
                        suppliersTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(PersonDAO.searchSuppliers((String) newValue))));

                    } catch (RemoteException | DatabaseErrorException ex) {

                    }
                }
            }
        });
//        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            newValue = newValue.trim();
//            if (newValue.isEmpty()) {
//                populateTable();
//            } else {
//                try {
//                    suppliersTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(PersonDAO.searchSuppliers(newValue))));
//
//                } catch (RemoteException | DatabaseErrorException ex) {
//
//                }
//            }
//        });
    }

    @Override
    public void populateTable() {
        try {
            Supplier p = suppliersTable.getSelectionModel().getSelectedItem();
            suppliersTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllSuppliers()));
            selectTable(p);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
    }

    @Override
    public void selectTable(Object o) {
        if (o != null) {
            Supplier cp = (Supplier) o;
            for (Supplier p : suppliersTable.getItems()) {
                if (p.getId() == cp.getId()) {
                    suppliersTable.getSelectionModel().select(p);
                }
            }
        }
    }

    @Override
    public void setTableAction() {
        suppliersTable.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Supplier item = suppliersTable.getSelectionModel().getSelectedItem();
                if (item != null) {
                    GUIController.getInstance().callScreen(ScreenType.SUPPLIER_DISPLAY, item);
                }
            }
        });
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
                if (GUIController.getInstance().showUmpromoteConfirmationAlert(person.getName())) {
                    PersonDAO.umpromotePerson(person);
                    populateTable();
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

    @Override
    public void setPath() {
        this.path = FXMLPaths.SUPPLIER_QUERY;
    }

    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.getSupplier().addObserver(this);
    }
}
