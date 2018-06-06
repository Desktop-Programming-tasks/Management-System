/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Enums.ServiceStatus;
import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Transactions.Service;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;


/**
 * FXML Controller class
 *
 * @author noda
 */
public class CreateServiceController implements Initializable {

    private static final String PATH = "desktoproject/View/Modal/CreateService.fxml";
    
    private Service newServiceReturn;
    
    public static ScreenObject call() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CreateServiceController.class.getClassLoader().getResource(PATH));
            Parent p = loader.load();
            CreateServiceController controller = loader.getController();
            controller.setEdit(false);
            controller.setUpComponents();
            return new ScreenObject(p, controller); 
    }
    
    public static Parent call(Object obj) throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CreateServiceController.class.getClassLoader().getResource(PATH));
            Parent p = loader.load();
            CreateServiceController controller = loader.getController();
            controller.setService((Service) obj);
            controller.setEdit(true);
            controller.setUpComponents();
            return p;
    }
    
    private Service service;
    private boolean edit;
    
    private void setUpComponents() {
        if(edit){
            mainLabel.setText("Atualizar Serviço");
            primaryBtn.setText("Atualizar");
            comboBoxState.setVisible(true);
            fillScreen();
        }else{
            mainLabel.setText("Adicionar Serviço");
            primaryBtn.setText("Adicionar");
//            comboBoxState.setVisible(false);
        }
    }

    private void fillScreen() {
        
        valueTextField.setText(String.valueOf(service.getPrice()));
        beginDate.setValue(service.getStartDate());
        endDate.setValue(service.getEstimatedDate());
        //setar estado e employee
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //value from database
        
        comboBoxSetup();
        loadComboBox();
    }
    
    private void comboBoxSetup(){
        //Service state
        comboBoxState.setCellFactory(new Callback<ListView<ServiceStatus>, ListCell<ServiceStatus>>(){
            @Override
            public ListCell<ServiceStatus> call(ListView<ServiceStatus> param) {
                return new ListCell<ServiceStatus>(){
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
                if(object==null){
                    return null;
                }else{
                    return ServiceStatus.translateEnumToGUI(object);
                }
            }

            @Override
            public ServiceStatus fromString(String string) {
                return null;
            }
        });
        
        //Employees available
        comboBoxEmployee.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>(){
            @Override
            public ListCell<Employee> call(ListView<Employee> param) {
                return new ListCell<Employee>(){
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
                if(object==null){
                    return null;
                }else{
                    return object.getName();
                }
            }

            @Override
            public Employee fromString(String string) {
                return null;
            }
        });
        
        //Service types
        comboBoxService.setCellFactory(new Callback<ListView<ServiceType>, ListCell<ServiceType>>(){
            @Override
            public ListCell<ServiceType> call(ListView<ServiceType> param) {
                return new ListCell<ServiceType>(){
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
                if(object==null){
                    return null;
                }else{
                    return object.getName();
                }
            }

            @Override
            public ServiceType fromString(String string) {
                return null;
            }
        });
    }
    
    private void loadComboBox(){
        comboBoxState.setItems(FXCollections.observableArrayList(ServiceStatus.values()));
        comboBoxState.getSelectionModel().select(ServiceStatus.ON_ESTIMATE);
        
        try {
            ArrayList<Employee> employees = PersonDAO.queryAllEmployees();
            if(employees.isEmpty()){
                comboBoxEmployee.setPromptText("Nenhum empregado");
                comboBoxEmployee.setDisable(true);
            }else{
                comboBoxEmployee.setItems(FXCollections.observableArrayList(employees));
            }
        } catch (RemoteException|DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            comboBoxEmployee.setPromptText("Nenhum empregado");
            comboBoxEmployee.setDisable(true);
        }
    }
    
    @FXML
    private void mainAction(){
        if(validate()){
            Service newService = new Service(beginDate.getValue(),endDate.getValue(),comboBoxState.getValue(),comboBoxEmployee.getValue(),comboBoxService.getValue());
            
            if(edit){
                
            }else{
                newServiceReturn = newService;
            }
        }
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().closeModal();
    }
    
    private void setService(Service service) {
        this.service = service;
    }

    private void setEdit(boolean edit) {
        this.edit = edit;
    }

    public Service getNewServiceReturn() {
        return newServiceReturn;
    }
    
    private boolean validate(){
        Validate valObj = new Validate();
        
        valObj.emptyComboBoxSelection(comboBoxEmployee, "um empregado");
        valObj.emptyComboBoxSelection(comboBoxService,"um serviço");
        boolean beginValid = valObj.validateDatePicker(beginDate, "uma data de início"),
                endValid = valObj.validateDatePicker(endDate, "uma data de término");
        if(beginValid && endValid){
            valObj.validateDate(beginDate, endDate);
        }
        
        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
