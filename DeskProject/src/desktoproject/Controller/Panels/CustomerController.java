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
import desktoproject.Controller.ControllerPersons;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Validate;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import desktoproject.Controller.FXMLPaths;



/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class CustomerController extends ControllerPersons implements Initializable {

//    private static final String PATH = "desktoproject/View/Panels/Customer.fxml";
//
//    public static Parent call() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(CustomerController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        CustomerController controller = loader.getController();
//        controller.setEdit(false);
//        controller.setAddressComponentObj(AddressComponentController.call());
//        controller.setTelephoneComponent(TelephoneComponentController.call());
//        controller.setAnchors(p);
//        controller.setUpComponents();
//        return p;
//    }
//
//    public static Parent call(Object person) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(CustomerController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        CustomerController controller = loader.getController();
//        controller.setPerson((Person) person);
//        controller.setAddressComponentObj(AddressComponentController.call(controller.getPerson().getAddress()));
//        controller.setTelephoneComponent(TelephoneComponentController.call(controller.getPerson().getTelephones()));
//        controller.setAnchors(p);
//        controller.setEdit(true);
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
    private Button backBtn;
    @FXML
    private ToggleGroup personGroup;
    @FXML
    private RadioButton legalPersonRadio;
    @FXML
    private RadioButton juridicalPersonRadio;
    @FXML
    private AnchorPane addressPane;
    @FXML
    private AnchorPane telephonePane;
    
    private Person person;
    private boolean isLegalPerson;

    @Override
    public void setUpComponents() {
//        addressPane.getChildren().clear();
//        addressPane.getChildren().add(getAddressComponent().getParent());
//        telephonePane.getChildren().clear();
//        telephonePane.getChildren().add(getTelephoneComponent().getParent());
        if (isEdit()) {
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

    @Override
    public void fillScreen() {
        nameTextField.setText(person.getName());

        if (isLegalPerson) {
            RGTextField.setText(((LegalPerson) person).getRG());
            CPFTextField.setText(((LegalPerson) person).getCPF());
        } else {
            CNPJTextField.setText(((JuridicalPerson) person).getCNPJ());
        }
    }

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
        
        Animation.bindShadowAnimation(mainBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindAnimation(nameTextField);
        Animation.bindAnimation(RGTextField);
        Animation.bindAnimation(CPFTextField);
        Animation.bindAnimation(CNPJTextField);
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    public void mainAction() {
        if (validate()) {
            Person newPerson;
            Address address = ((AddressComponentController) getAddressComponent().getController()).getAddress();
            ArrayList<String> telephones = ((TelephoneComponentController) getTelephoneComponent().getController()).getTelephones();
            if (isLegalPerson) {
                newPerson = new LegalPerson(RGTextField.getText(), nameTextField.getText(), address, telephones, CPFTextField.getText());
            } else {
                newPerson = new JuridicalPerson(nameTextField.getText(), address, telephones, CNPJTextField.getText());
            }
            try {
                if(isEdit()){
                    newPerson.setId(person.getId());
                    newPerson.setActive(person.isActive());
                    PersonDAO.updatePerson(newPerson);
                    GUIController.getInstance().showUpdateAlert();
                    GUIController.getInstance().backToPrevious();
                }else{
                    PersonDAO.insertPerson(newPerson);
                    GUIController.getInstance().showRegisterAlert("Cliente");
                    GUIController.getInstance().backToPrevious();
                }
            } catch (RemoteException|DatabaseErrorException ex) {
                System.out.println(ex.getMessage());
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

    public void setIsLegalPerson(boolean isLegalPerson) {
        this.isLegalPerson = isLegalPerson;
    }
    
    private void toggleFields() {
        RGTextField.setVisible(isLegalPerson);
        CPFTextField.setVisible(isLegalPerson);
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

        valObj.appendErrorMessage(((TelephoneComponentController) getTelephoneComponent().getController()).validateFields());

        valObj.appendErrorMessage(((AddressComponentController) getTelephoneComponent().getController()).validateFields());

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
        this.person = (Person) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.CUSTOMER_SCREEN;
    }
}
