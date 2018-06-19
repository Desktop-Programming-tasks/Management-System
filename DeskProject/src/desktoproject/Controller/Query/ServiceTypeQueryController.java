/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Transactions.ServiceTypeDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.ChangeListenerRunnable;
import desktoproject.Utils.TextChangeListener;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author viniciusmn
 */
public class ServiceTypeQueryController extends Controller implements Initializable, TableScreen, AppObserver {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<ServiceType> ServiceTable;
    @FXML
    private TableColumn<ServiceType, String> nameColumn;
    @FXML
    private TableColumn<ServiceType, String> priceColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button editBtn;
    @FXML
    private Button newBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button deleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(newBtn);
        Animation.bindShadowAnimation(editBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(deleteBtn);

        Animation.bindAnimation(searchTextField);
        ServiceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        populateTable();
        setTableAction();
        setUpSearch();
        subscribe();
    }

    @Override
    public void setUpSearch() {
        searchTextField.textProperty().addListener(new TextChangeListener(
                new ChangeListenerRunnable() {
            @Override
            public void run() {
                newValue = newValue.trim();
                if (newValue.isEmpty()) {
                    populateTable();
                } else {
                    try {
                        ServiceTable.setItems(FXCollections.observableArrayList(ServiceTypeDAO.searchServiceTypes(newValue)));
                    } catch (RemoteException | DatabaseErrorException ex) {
                        //
                    }
                }
            }
        }));
//        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            newValue = newValue.trim();
//            if (newValue.isEmpty()) {
//                populateTable();
//            } else {
//                try {
//                    ServiceTable.setItems(FXCollections.observableArrayList(ServiceTypeDAO.searchServiceTypes(newValue)));
//                } catch (RemoteException | DatabaseErrorException ex) {
//                    //
//                }
//            }
//        });
    }

    @Override
    public void populateTable() {
        try {
            ServiceType selectedService = ServiceTable.getSelectionModel().getSelectedItem();
            ServiceTable.setItems(FXCollections.observableArrayList(ServiceTypeDAO.queryAllServiceTypes()));
            selectTable(selectedService);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        }
    }

    @Override
    public void selectTable(Object o) {
        if (o != null) {
            ServiceType cst = (ServiceType) o;
            for (ServiceType st : ServiceTable.getItems()) {
                if (st.getId() == cst.getId()) {
                    ServiceTable.getSelectionModel().select(st);
                }
            }
        }
    }

    @Override
    public void setTableAction() {
        ServiceTable.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                ServiceType item = ServiceTable.getSelectionModel().getSelectedItem();
                if (item != null) {
                    GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_EDIT, item);
                    populateTable();
                }
            }
        });
        ServiceTable.setRowFactory(tv -> {
            TableRow<ServiceType> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ServiceType serviceType = row.getItem();
                    GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_EDIT, serviceType);
                    populateTable();
                }
            });
            return row;
        });
    }

    @FXML
    private void delete() {
        ServiceType st = ServiceTable.getSelectionModel().getSelectedItem();
        if (st == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            try {
                if (GUIController.getInstance().showEraseConfirmationAlert(st.getName())) {
                    ServiceTypeDAO.inactivateServiceType(st);
                    populateTable();
                }
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            }
        }
    }

    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    private void newServiceType() {
        GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_CREATE);
        populateTable();
    }

    @FXML
    private void editServiceType() {
        ServiceType serviceType = ServiceTable.getSelectionModel().getSelectedItem();
        if (serviceType == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_EDIT, serviceType);
            populateTable();
        }
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.SERVICE_TYPE_QUERY;
    }

    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.getServiceType().addObserver(this);
    }
}
