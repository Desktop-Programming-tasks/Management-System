/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Enums.ServiceStatus;
import Classes.Persons.Employee;
import Classes.Transactions.Service;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Interfaces.ControllerEdit;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Model.DAO.Transactions.ServiceTransactionDAO;
import desktoproject.Model.DAO.Transactions.ServiceTypeDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import desktoproject.Utils.Pairs.ScreenData;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class CreateServiceController extends ControllerEdit implements Initializable, AppObserver {
    
    public ScreenData call(Object obj, boolean newEdit) throws IOException {
        ScreenData callReturn = super.call(obj);
        CreateServiceController c = ((CreateServiceController) callReturn.getController());
        c.newEdit = newEdit;
        c.fixComponents();
        return new ScreenData(callReturn.getParent(), c);
    }

    private Service service;
    private Service newServiceReturn;
    private boolean newEdit;

    @Override
    public void setUpComponents() {
        if (isEdit()) {
            mainLabel.setText("Atualizar Serviço");
            primaryBtn.setText("Atualizar");
            comboBoxState.setVisible(true);
            fillScreen();
        } else {
            mainLabel.setText("Adicionar Serviço");
            primaryBtn.setText("Adicionar");
            comboBoxState.setVisible(false);
        }
    }

    private void fixComponents() {
        if (newEdit) {
            comboBoxState.setVisible(false);
        } else {
            comboBoxState.setVisible(true);
            comboBoxEmployee.setDisable(true);
            comboBoxService.setDisable(true);
            if(service.getStatus()==ServiceStatus.REFUSED || service.getStatus()==ServiceStatus.DONE ){
                comboBoxState.setDisable(true);
            }
        }
    }

    @Override
    public void fillScreen() {
        valueTextField.setText(String.valueOf(service.getPrice()));
        beginDate.setValue(Misc.dateToLocal(service.getStartDate()));
        endDate.setValue(Misc.dateToLocal(service.getEstimatedDate()));
        selectComboBoxEmployee((Employee) service.getAssignedEmployee());
        selectComboBoxServiceType(service.getServiceType());
        comboBoxState.getSelectionModel().select(service.getStatus());
        descriptionTextArea.setText(service.getMessage());
    }

    @FXML
    private Label mainLabel;
    @FXML
    private TextField valueTextField;
    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<ServiceStatus> comboBoxState;
    @FXML
    private ComboBox<Employee> comboBoxEmployee;
    @FXML
    private ComboBox<ServiceType> comboBoxService;
    @FXML
    private Button primaryBtn;
    @FXML
    private Button backBtn;
    @FXML
    private TextArea descriptionTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //value from database
        Animation.bindAnimation(valueTextField);
        Animation.bindAnimation(beginDate);
        Animation.bindAnimation(endDate);
        Animation.bindAnimation(comboBoxEmployee);
        Animation.bindAnimation(comboBoxState);
        Animation.bindAnimation(comboBoxService);
        Animation.bindAnimation(descriptionTextArea);
        Animation.bindShadowAnimation(primaryBtn);
        Animation.bindShadowAnimation(backBtn);
        

        comboBoxSetup();
        loadComboBox();
        loadComboBoxState();
    }

    private void comboBoxSetup() {
        //Service state
        comboBoxState.setCellFactory(new Callback<ListView<ServiceStatus>, ListCell<ServiceStatus>>() {
            @Override
            public ListCell<ServiceStatus> call(ListView<ServiceStatus> param) {
                return new ListCell<ServiceStatus>() {
                    @Override
                    protected void updateItem(ServiceStatus item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(ServiceStatus.translateEnumToGUI(item));
                        }
                    }
                };
            }
        });
        comboBoxState.setConverter(new StringConverter<ServiceStatus>() {
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

        //Employees available
        comboBoxEmployee.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>() {
            @Override
            public ListCell<Employee> call(ListView<Employee> param) {
                return new ListCell<Employee>() {
                    @Override
                    protected void updateItem(Employee item, boolean empty) {
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
        comboBoxEmployee.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee object) {
                if (object == null) {
                    return null;
                } else {
                    return object.getName();
                }
            }

            @Override
            public Employee fromString(String string) {
                return null;
            }
        });

        //Service types
        comboBoxService.setCellFactory(new Callback<ListView<ServiceType>, ListCell<ServiceType>>() {
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
        comboBoxService.setConverter(new StringConverter<ServiceType>() {
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
        comboBoxService.valueProperty().addListener((observable) -> {
            System.out.println("entrou aqui");
            System.out.println(Misc.changeToComma(String.valueOf(comboBoxService.getSelectionModel().getSelectedItem().getPrice())));
            valueTextField.setText(Misc.changeToComma(String.valueOf(comboBoxService.getSelectionModel().getSelectedItem().getPrice())));
        });
    }

    private void loadComboBoxState() {
        comboBoxState.setItems(FXCollections.observableArrayList(ServiceStatus.values()));
        comboBoxState.getSelectionModel().select(ServiceStatus.ON_ESTIMATE);
    }

    private void selectComboBoxEmployee(Employee e) {
        if (e != null) {
            for (Employee em : comboBoxEmployee.getItems()) {
                if (em.getId() == e.getId()) {
                    comboBoxEmployee.getSelectionModel().select(em);
                }
            }
        }
    }

    private void selectComboBoxServiceType(ServiceType st) {
        if (st != null) {
            for (ServiceType sty : comboBoxService.getItems()) {
                if (sty.getId() == st.getId()) {
                    comboBoxService.getSelectionModel().select(sty);
                }
            }
        }
    }

    private void loadComboBox() {
        try {
            Employee e = comboBoxEmployee.getSelectionModel().getSelectedItem();
            ArrayList<Employee> employees = PersonDAO.queryAllEmployees();
            if (employees.isEmpty()) {
                comboBoxEmployee.setPromptText("Nenhum empregado");
                comboBoxEmployee.setDisable(true);
            } else {
                comboBoxEmployee.setItems(FXCollections.observableArrayList(employees));
                selectComboBoxEmployee(e);
            }

        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            comboBoxEmployee.setPromptText("Nenhum empregado");
            comboBoxEmployee.setDisable(true);
        }

        try {
            ServiceType st = comboBoxService.getSelectionModel().getSelectedItem();
            ArrayList<ServiceType> serviceTypes = ServiceTypeDAO.queryAllServiceTypes();

            if (serviceTypes.isEmpty()) {
                comboBoxService.setPromptText("Nenhum serviço cadastrado");
                comboBoxService.setDisable(true);
            } else {
                comboBoxService.setItems(FXCollections.observableArrayList(serviceTypes));
                selectComboBoxServiceType(st);
            }
        } catch (RemoteException | DatabaseErrorException ex) {
            Logger.getLogger(CreateServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            String message = descriptionTextArea.getText();
            if(message==null){
                message="";
            }
            if (isEdit() && !newEdit) {
                service.setStartDate(Misc.localToDate(beginDate.getValue()));
                service.setEstimatedDate(Misc.localToDate(endDate.getValue()));
                service.setStatus(comboBoxState.getValue());
                service.setAssignedEmployee(comboBoxEmployee.getValue());
                service.setServiceType(comboBoxService.getValue());
                service.setMessage(message);
                
                try {
                    //save update in database
                    ServiceTransactionDAO.updateService(service);
                    GUIController.getInstance().closeModal();
                } catch (RemoteException|DatabaseErrorException ex) {
                    GUIController.getInstance().showConnectionErrorAlert();
                } catch (NoResultsException ex) {
                    GUIController.getInstance().showUpdateErrorAlert();
                }
            } else {
                newServiceReturn = new Service(
                        Misc.localToDate(beginDate.getValue()),
                        Misc.localToDate(endDate.getValue()),
                        ServiceStatus.ON_ESTIMATE,
                        comboBoxEmployee.getValue(),
                        comboBoxService.getValue()
                );
                newServiceReturn.setMessage(message);
                
                GUIController.getInstance().closeModal();
            }
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().closeModal();
    }

    public Service getNewServiceReturn() {
        return newServiceReturn;
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.emptyComboBoxSelection(comboBoxEmployee, "um empregado");
        valObj.emptyComboBoxSelection(comboBoxService, "um serviço");
        boolean beginValid = valObj.validateDatePicker(beginDate, "uma data de início"),
                endValid = valObj.validateDatePicker(endDate, "uma data de término");
        if (beginValid && endValid) {
            valObj.validateDate(beginDate, endDate);
        }

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }

    @Override
    public void setScreenObject(Object obj) {
        this.service = (Service) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.CREATE_SERVICE_MODAL;
    }

    @Override
    public void update() {
        loadComboBox();
    }

    @Override
    public void subscribe() {
        ObservableServer.getEmployee().addObserver(this);
        ObservableServer.getServiceType().addObserver(this);
    }
}
