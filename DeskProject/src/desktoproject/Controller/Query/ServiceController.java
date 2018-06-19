/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Enums.ServiceStatus;
import Classes.Persons.Employee;
import Classes.Transactions.Service;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Model.DAO.Transactions.ServiceTransactionDAO;
import desktoproject.Model.DAO.Transactions.ServiceTypeDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceController extends Controller implements Initializable, TableScreen, AppObserver {

    @FXML
    private TableView<Service> serviceTable;
    @FXML
    private TableColumn<Service, String> dateColumn;
    @FXML
    private TableColumn<Service, String> serviceTypeColumn;
    @FXML
    private TableColumn<Service, String> messageColumn;
    @FXML
    private TableColumn<Service, String> idColumn;
    @FXML
    private ComboBox<ServiceStatus> stateComboBox;
    @FXML
    private ComboBox<ServiceType> serviceTypeComboBox;
    @FXML
    private ComboBox<Employee> employeeComboBox;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(updateBtn);
        Animation.bindShadowAnimation(cancelBtn);
        Animation.bindShadowAnimation(backBtn);

        Animation.bindAnimation(stateComboBox);
        Animation.bindAnimation(serviceTypeComboBox);
        Animation.bindAnimation(employeeComboBox);

        subscribe();
        setUpTable();
        comboBoxSetup();
        loadComboBox();
    }

    private void setUpTable() {
        dateColumn.setCellValueFactory((TableColumn.CellDataFeatures<Service, String> p) -> {
            return new SimpleStringProperty(Misc.dateToString(p.getValue().getEstimatedDate()));
        });
        serviceTypeColumn.setCellValueFactory((TableColumn.CellDataFeatures<Service, String> p) -> {
            return new SimpleStringProperty(p.getValue().getServiceType().getName());
        });
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    private void comboBoxSetup() {
        //Service state combo box setup
        stateComboBox.setCellFactory(new Callback<ListView<ServiceStatus>, ListCell<ServiceStatus>>() {
            @Override
            public ListCell<ServiceStatus> call(ListView<ServiceStatus> param) {
                return new ListCell<ServiceStatus>() {
                    @Override
                    protected void updateItem(ServiceStatus item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else if (item == null) {
                            setText("Todos");
                        } else {
                            setText(ServiceStatus.translateEnumToGUI(item));
                        }
                    }
                };
            }
        });

        stateComboBox.setConverter(new StringConverter<ServiceStatus>() {
            @Override
            public String toString(ServiceStatus object) {
                if (object == null) {
                    return null;
                } else {
                    return ServiceStatus.translateEnumToGUI(object);
                }
            }

            @Override
            public ServiceStatus fromString(String string) {
                return null;
            }
        });

        stateComboBox.valueProperty().addListener((observable) -> {
            populateTable();
        });

        //Service type combo box
        serviceTypeComboBox.setCellFactory(new Callback<ListView<ServiceType>, ListCell<ServiceType>>() {
            @Override
            public ListCell<ServiceType> call(ListView<ServiceType> param) {
                return new ListCell<ServiceType>() {
                    @Override
                    protected void updateItem(ServiceType item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else if (item == null) {
                            setText("Todos");
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

        serviceTypeComboBox.setConverter(new StringConverter<ServiceType>() {
            @Override
            public String toString(ServiceType object) {
                if (object == null) {
                    return null;
                } else {
                    return object.getName();
                }
            }

            @Override
            public ServiceType fromString(String string) {
                return null;
            }
        });

        serviceTypeComboBox.valueProperty().addListener((observable) -> {
            populateTable();
        });

        //Employee combo box
        employeeComboBox.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>() {
            @Override
            public ListCell<Employee> call(ListView<Employee> param) {
                return new ListCell<Employee>() {
                    @Override
                    protected void updateItem(Employee item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else if (item == null) {
                            setText("Todos");
                        } else {
                            setText(item.getLogin());
                        }
                    }
                };
            }
        });

        employeeComboBox.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee object) {
                if (object == null) {
                    return null;
                } else {
                    return object.getLogin();
                }
            }

            @Override
            public Employee fromString(String string) {
                return null;
            }
        });

        employeeComboBox.valueProperty().addListener((observable) -> {
            populateTable();
        });
    }

    private void loadComboBox() {
        stateComboBox.setItems(FXCollections.observableArrayList(ServiceStatus.values()));
        stateComboBox.getSelectionModel().selectFirst();

        try {
            serviceTypeComboBox.setItems(FXCollections.observableArrayList(ServiceTypeDAO.queryAllServiceTypes()));
            serviceTypeComboBox.getItems().add(null);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        }

        try {
            employeeComboBox.setItems(FXCollections.observableArrayList(PersonDAO.queryAllEmployees()));
            employeeComboBox.getItems().add(null);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    public void showModalUpdateService() {
        Service s = serviceTable.getSelectionModel().getSelectedItem();

        if (s == null) {
            GUIController.getInstance().showConnectionErrorAlert();
        } else {
            GUIController.getInstance().callModal(ModalType.SERVICE_UPDATE, s);
        }
    }

    @FXML
    private void cancelService() {
        Service s = serviceTable.getSelectionModel().getSelectedItem();

        if (s == null) {
            GUIController.getInstance().showConnectionErrorAlert();
        } else {
            System.out.println("status "+s.getStatus().name());
            switch (s.getStatus()) {
                case DONE:
                case REFUSED: {
                    System.out.println("done or refused");
                    GUIController.getInstance().showCancelErrorAlert();
                    break;
                }
                default: {
                    System.out.println("default");
                    try {
                        s.setStatus(ServiceStatus.REFUSED);
                        ServiceTransactionDAO.updateService(s);
                        GUIController.getInstance().showCancelAlert(String.valueOf(s.getId()));
                    } catch (RemoteException | DatabaseErrorException ex) {
                        GUIController.getInstance().showConnectionErrorAlert();
                    } catch (NoResultsException ex) {
                    }
                }
            }
        }
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.SERVICE_QUERY;
    }

    @Override
    public void populateTable() {
        Employee e = employeeComboBox.getSelectionModel().getSelectedItem();
        ServiceType st = serviceTypeComboBox.getSelectionModel().getSelectedItem();

        int status = ServiceStatus.enumToInt(stateComboBox.getValue());
        String employeeLogin = e != null ? e.getLogin() : "";
        String serviceTypeName = st != null ? st.getName() : "";

        try {
            serviceTable.setItems(FXCollections.observableArrayList(ServiceTransactionDAO.queryServices(employeeLogin, status, serviceTypeName)));
            ServiceTransactionDAO.queryServices(employeeLogin, status, serviceTypeName).forEach((t) -> {
                System.out.println("service: "+t.getId()+" status: "+t.getStatus());
            });
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
    }

    @Override
    public void setTableAction() {
        serviceTable.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Service item = serviceTable.getSelectionModel().getSelectedItem();
                if (item != null) {
                    GUIController.getInstance().callModal(ModalType.SERVICE_UPDATE, item);
                }
            }
        });
        serviceTable.setRowFactory(tv -> {
            TableRow<Service> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Service service = row.getItem();
                    GUIController.getInstance().callModal(ModalType.SERVICE_UPDATE, service);
                }
            });
            return row;
        });
    }

    @Override
    public void setUpSearch() {
        //no search in this table
    }

    @Override
    public void selectTable(Object o) {
        //no need to select
    }

    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.getService().addObserver(this);
    }
}
