/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Persons.Address;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class CustomerController implements Initializable {

    private static final String panelCustomerPath = "desktoproject/View/Panels/Customer.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CustomerController.class.getClassLoader().getResource(panelCustomerPath));
        Parent p = loader.load();
        CustomerController controller = loader.getController();
        controller.setEdit(false);
        controller.setAddressComponentObj(AddressComponentController.call());
        controller.setTelephoneComponent(TelephoneComponentController.call());
        controller.setAnchors(p);
        controller.setUpComponents();
        return p;
    }

    public static Parent call(Object person) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CustomerController.class.getClassLoader().getResource(panelCustomerPath));
        Parent p = loader.load();
        CustomerController controller = loader.getController();
        controller.setPerson((Person) person);
        controller.setAddressComponentObj(AddressComponentController.call(controller.getPerson().getAddress()));
        controller.setTelephoneComponent(TelephoneComponentController.call(controller.getPerson().getTelephones()));
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

    private Person person;
    private boolean isLegalPerson;
    private boolean edit;
    private ScreenObject addressComponent;
    private ScreenObject telephoneComponent;

    private void setUpComponents() {
        addressPane.getChildren().clear();
        addressPane.getChildren().add(addressComponent.getParent());
        telephonePane.getChildren().clear();
        telephonePane.getChildren().add(telephoneComponent.getParent());
        if (edit) {
            mainLabel.setText("Editar Cliente");
            mainBtn.setText("Alterar");

            isLegalPerson = (person instanceof LegalPerson);
            toggleFields();
            setRadioButtons();
            legalPersonRadio.setDisable(true);
            juridicalPersonRadio.setDisable(true);
            fillScreen();
        } else {
            mainLabel.setText("Cadastrar Cliente");
            mainBtn.setText("Cadastrar");
        }
    }

    private void fillScreen() {
        nameTextField.setText(person.getName());

        if (isLegalPerson) {
            RGTextField.setText(((LegalPerson) person).getRG());
            CPFTextField.setText(((LegalPerson) person).getCPF());
        } else {
            CNPJTextField.setText(((JuridicalPerson) person).getCNPJ());
        }
    }

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField RGTextField;
    @FXML
    private TextField CPFTextField;
    @FXML
    private TextField CNPJTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private ToggleGroup personGroup;
    @FXML
    private Group legalGroup;
    @FXML
    private RadioButton legalPersonRadio;
    @FXML
    private RadioButton juridicalPersonRadio;
    @FXML
    private AnchorPane addressPane;
    @FXML
    private AnchorPane telephonePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isLegalPerson = true;
        personGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            isLegalPerson = observable.getValue().equals(legalPersonRadio);
            toggleFields();
        });
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    public void mainAction() {
        if (validate()) {
            Person newPerson;
            Address address = ((AddressComponentController) addressComponent.getController()).getAddress();
            ArrayList<String> telephones = ((TelephoneComponentController) telephoneComponent.getController()).getTelephones();
            if (isLegalPerson) {
                person = new LegalPerson(RGTextField.getText(), nameTextField.getText(), address, telephones, CPFTextField.getText());
            } else {
                person = new JuridicalPerson(nameTextField.getText(), address, telephones, CNPJTextField.getText());
            }
            try {
                if(edit){
                    PersonDAO.updatePerson(person);
                    GUIController.getInstance().showUpdateAlert();
                    GUIController.getInstance().backToPrevious();
                }else{
                    PersonDAO.insertPerson(person);
                    GUIController.getInstance().showRegisterAlert("Cliente");
                    GUIController.getInstance().backToPrevious();
                }
            } catch (RemoteException|DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Cliente",isLegalPerson?"CPF":"CNPJ");
            } catch (DuplicatedLoginException ex) {
                //no login inserting costumer
            } catch (NoResultsException ex) {
                GUIController.getInstance().showUpdateErrorAlert();
            }
        }
        
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setIsLegalPerson(boolean isLegalPerson) {
        this.isLegalPerson = isLegalPerson;
    }

    public void setAddressComponentObj(ScreenObject addressComponentObj) {
        this.addressComponent = addressComponentObj;
    }

    public void setTelephoneComponent(ScreenObject telephoneComponent) {
        this.telephoneComponent = telephoneComponent;
    }

    private void toggleFields() {
        legalGroup.setVisible(isLegalPerson);
        CNPJTextField.setVisible(!isLegalPerson);
    }

    private void setRadioButtons() {
        if (isLegalPerson) {
            legalPersonRadio.setSelected(true);
        } else {
            juridicalPersonRadio.setSelected(true);
        }
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.validateName(nameTextField.getText());
        if (isLegalPerson) {
            valObj.validateRG(RGTextField.getText());
            valObj.validateCPF(CPFTextField.getText());
        } else {
            valObj.validateCNPJ(CNPJTextField.getText());
        }

        valObj.appendErrorMessage(((TelephoneComponentController) telephoneComponent.getController()).validateFields());

        valObj.appendErrorMessage(((AddressComponentController) addressComponent.getController()).validateFields());

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
