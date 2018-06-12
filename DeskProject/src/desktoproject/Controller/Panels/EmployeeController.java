/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Enums.EmployeeType;
import Classes.Persons.Address;
import Classes.Persons.Employee;
import Classes.Persons.LegalPerson;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import desktoproject.Controller.ControllerPromotion;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Validate;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import desktoproject.Controller.FXMLPaths;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class EmployeeController extends ControllerPromotion implements Initializable {

//    private static final String PATH = "desktoproject/View/Panels/Employee.fxml";
//
//    public static Parent call() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(EmployeeController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        EmployeeController controller = loader.getController();
//        controller.setAddressComponentObj(AddressComponentController.call());
//        controller.setTelephoneComponent(TelephoneComponentController.call());
//        controller.setAnchors(p);
//        controller.setUpComponents();
//        return p;
//    }
//
//    public static Parent call(Object employee, boolean promote) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(EmployeeController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//
//        EmployeeController controller = loader.getController();
//        controller.setAnchors(p);
//        controller.setEmployee((Employee) employee);
//        controller.setAddressComponentObj(AddressComponentController.call(controller.getEmployee().getAddress()));
//        controller.setTelephoneComponent(TelephoneComponentController.call(controller.getEmployee().getTelephones()));
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
    private GridPane gridpaneBasicData;
    @FXML
    private ComboBox<EmployeeType> employeeTypeCombobox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField RGTextField;
    @FXML
    private TextField CPFTextField;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordFieldOficial;
    @FXML
    private PasswordField passwordFieldConfirm;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane addressPane;
    @FXML
    private AnchorPane telephonePane;

    private Employee employee;

    @Override
    public void setUpComponents() {
        if (isEdit()) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Funcionário");
            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Funcionário");
        }
        if(isPromote()) {
            mainLabel.setText("Promover a funcionário");
            mainBtn.setText("Salvar");
        }
    }

    @Override
    public void fillScreen() {
        nameTextField.setText(employee.getName());
        RGTextField.setText(employee.getRG());
        CPFTextField.setText(employee.getCPF());

        userTextField.setText(employee.getLogin());
        employeeTypeCombobox.setValue(employee.getEmployeeType());
        //dont set the password, only detect changes in password if anything new is writen in the password and confirm password fields
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(mainBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindAnimation(nameTextField);
        Animation.bindAnimation(RGTextField);
        Animation.bindAnimation(CPFTextField);
        Animation.bindAnimation(userTextField);
        Animation.bindAnimation(passwordFieldOficial);
        Animation.bindAnimation(passwordFieldConfirm);
        Animation.bindAnimation(employeeTypeCombobox);

        comboBoxSetup();
        loadComboBox();
    }

    private void comboBoxSetup() {
        employeeTypeCombobox.setCellFactory(new Callback<ListView<EmployeeType>, ListCell<EmployeeType>>() {
            @Override
            public ListCell<EmployeeType> call(ListView<EmployeeType> param) {
                return new ListCell<EmployeeType>() {
                    @Override
                    protected void updateItem(EmployeeType item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(EmployeeType.translateEnumToGUI(item));
                        }
                    }
                };
            }
        });
        employeeTypeCombobox.setConverter(new StringConverter<EmployeeType>() {
            @Override
            public String toString(EmployeeType object) {
                if (object == null) {
                    return null;
                } else {
                    return EmployeeType.translateEnumToGUI(object);
                }
            }

            @Override
            public EmployeeType fromString(String string) {
                return null;
            }
        });
    }

    private void loadComboBox() {
        employeeTypeCombobox.setItems(FXCollections.observableArrayList(EmployeeType.values()));
        employeeTypeCombobox.setValue(EmployeeType.COMMOM);
    }

    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            Address address = ((AddressComponentController) getAddressComponent().getController()).getAddress();
            ArrayList<String> telephones = ((TelephoneComponentController) getTelephoneComponent().getController()).getTelephones();

            String password;

            if ((isEdit() && (!passwordFieldOficial.getText().isEmpty() || !passwordFieldConfirm.getText().isEmpty())) || (!isEdit())) {
                password = passwordFieldOficial.getText();
            } else {
                password = employee.getPassword();
            }

            Employee newEmployee = new Employee(userTextField.getText(), password, employeeTypeCombobox.getValue(), RGTextField.getText(), nameTextField.getText(), address, telephones, CPFTextField.getText());

            try {
                if (isPromote()) {
                    newEmployee.setId(employee.getId());
                    newEmployee.setActive(employee.isActive());
                    PersonDAO.promoteEmployee(newEmployee);
                    GUIController.getInstance().showPromoteAlert("Funcionário");
                    GUIController.getInstance().backToPrevious();
                } else {
                    if (isEdit()) {
                        newEmployee.setId(employee.getId());
                        newEmployee.setActive(employee.isActive());
                        PersonDAO.updatePerson(newEmployee);
                        GUIController.getInstance().showUpdateAlert();
                        GUIController.getInstance().backToPrevious();
                    } else {
                        PersonDAO.insertPerson(newEmployee);
                        GUIController.getInstance().showRegisterAlert("Funcionário");
                        GUIController.getInstance().backToPrevious();
                    }
                }
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Funcionário", "CPF");
            } catch (DuplicatedLoginException ex) {
                GUIController.getInstance().showDupplicatedAlert("Funcionário", "Login");
            } catch (NoResultsException ex) {
                GUIController.getInstance().showUpdateErrorAlert();
            }
        }
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.validateName(nameTextField.getText());

        valObj.validateRG(RGTextField.getText());
        valObj.validateCPF(CPFTextField.getText());

        valObj.appendErrorMessage(((TelephoneComponentController) getAddressComponent().getController()).validateFields());

        valObj.appendErrorMessage(((AddressComponentController) getTelephoneComponent().getController()).validateFields());

        valObj.validateNick(userTextField.getText());

        if ((isEdit() && (!passwordFieldOficial.getText().isEmpty() || !passwordFieldConfirm.getText().isEmpty())) || (!isEdit())) {
            if (valObj.validatePassword(passwordFieldOficial.getText()) && valObj.validateConfirmPassword(passwordFieldConfirm.getText())) {
                valObj.passwordMatch(passwordFieldOficial.getText(), passwordFieldConfirm.getText());
            }
        }

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
        if(obj instanceof Employee) {
            this.employee = (Employee) obj;
        } else {
            this.employee = new Employee((LegalPerson) obj);
            setPromote(true);
        }
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.EMPLOYEE_SCREEN;
    }
}
