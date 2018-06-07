/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Enums.EmployeeType;
import Classes.Persons.Address;
import Classes.Persons.Employee;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Pairs.ScreenObject;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class EmployeeController implements Initializable {

    private static final String PATH = "desktoproject/View/Panels/Employee.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        EmployeeController controller = loader.getController();
        controller.setAddressComponentObj(AddressComponentController.call());
        controller.setTelephoneComponent(TelephoneComponentController.call());
        controller.setAnchors(p);
        controller.setUpComponents();
        return p;
    }

    public static Parent call(Object employee) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();

        EmployeeController controller = loader.getController();
        controller.setAnchors(p);
        controller.setEmployee((Employee) employee);
        controller.setAddressComponentObj(AddressComponentController.call(controller.getEmployee().getAddress()));
        controller.setTelephoneComponent(TelephoneComponentController.call(controller.getEmployee().getTelephones()));
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

    private Employee employee;
    private boolean edit;
    private ScreenObject addressComponentObj;
    private ScreenObject telephoneComponent;

    private void setUpComponents() {
        addressPane.getChildren().clear();
        addressPane.getChildren().add(addressComponentObj.getParent());
        telephonePane.getChildren().clear();
        telephonePane.getChildren().add(telephoneComponent.getParent());
        if (edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Funcionário");
            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Funcionário");
        }
    }

    private void fillScreen() {
        nameTextField.setText(employee.getName());
        RGTextField.setText(employee.getRG());
        CPFTextField.setText(employee.getCPF());

        userTextField.setText(employee.getLogin());
        //dont set the password, only detect changes in password if anything new is writen in the password and confirm password fields
    }
    
    @FXML
    private GridPane gridpaneBasicData;

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
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            Address address = ((AddressComponentController) addressComponentObj.getController()).getAddress();
            ArrayList<String> telephones = ((TelephoneComponentController) telephoneComponent.getController()).getTelephones();
            Employee newEmployee = new Employee(userTextField.getText(), passwordFieldOficial.getText(), EmployeeType.COMMOM, RGTextField.getText(), nameTextField.getText(), address, telephones, CPFTextField.getText());

            try {
                if (edit) {
                    PersonDAO.updatePerson(newEmployee);
                    GUIController.getInstance().showUpdateAlert();
                    GUIController.getInstance().backToPrevious();
                } else {
                    PersonDAO.insertPerson(newEmployee);
                    GUIController.getInstance().showRegisterAlert("Funcionário");
                    GUIController.getInstance().backToPrevious();
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setAddressComponentObj(ScreenObject addressComponentObj) {
        this.addressComponentObj = addressComponentObj;
    }

    public void setTelephoneComponent(ScreenObject telephoneComponent) {
        this.telephoneComponent = telephoneComponent;
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.validateName(nameTextField.getText());

        valObj.validateRG(RGTextField.getText());
        valObj.validateCPF(CPFTextField.getText());

        valObj.appendErrorMessage(((TelephoneComponentController) telephoneComponent.getController()).validateFields());

        valObj.appendErrorMessage(((AddressComponentController) addressComponentObj.getController()).validateFields());

        valObj.validateNick(userTextField.getText());

        if (valObj.validatePassword(passwordFieldOficial.getText()) && valObj.validateConfirmPassword(passwordFieldConfirm.getText())) {
            valObj.passwordMatch(passwordFieldOficial.getText(), passwordFieldConfirm.getText());
        }

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
