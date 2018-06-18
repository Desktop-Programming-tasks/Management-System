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
import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Service,String> dateColumn;
    @FXML
    private TableColumn<Service,String> serviceTypeColumn;
    @FXML
    private TableColumn<Service,String> messageColumn;
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
        serviceTypeColumn.setCellValueFactory((TableColumn.CellDataFeatures<Service,String> p) -> {
            return new SimpleStringProperty(p.getValue().getServiceType().getName());
        });
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
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
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.name());
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
                        if (item == null || empty) {
                            setGraphic(null);
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
                        if (item == null || empty) {
                            setGraphic(null);
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
        
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    public void showModalUpdateService() {
        
    }
    
    @FXML
    private void detailsProduct(){
        
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.SERVICE_QUERY;
    }

    @Override
    public void populateTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTableAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpSearch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectTable(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
