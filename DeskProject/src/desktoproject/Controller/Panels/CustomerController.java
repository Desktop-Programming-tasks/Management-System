/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Controller.GUIController;
import desktoproject.Model.Classes.Persons.Address;
import desktoproject.Model.Classes.Persons.JuridicalPerson;
import desktoproject.Model.Classes.Persons.LegalPerson;
import desktoproject.Model.Classes.Persons.Person;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

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
        controller.setUpComponents();
        return p;
    }

    public static Parent call(Object person) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CustomerController.class.getClassLoader().getResource(panelCustomerPath));
        Parent p = loader.load();
        CustomerController controller = loader.getController();
        controller.setPerson((Person) person);
        controller.setEdit(true);
        controller.setUpComponents();
        return p;
    }

    private Person person;
    private boolean isLegalPerson;
    private boolean edit;

    private void setUpComponents() {
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
        telTextField.setText(person.getTelephones().get(0));
        if(person.getTelephones().size()>1){
            secTelTextField.setText(person.getTelephones().get(1));
        }
        streetTextField.setText(person.getAddress().getStreet());
        numberTextField.setText(String.valueOf(person.getAddress().getNumber()));
        districtTextField.setText(person.getAddress().getBlock());
        
        if(isLegalPerson){
            RGTextField.setText(((LegalPerson) person).getRG());
            CPFTextField.setText(((LegalPerson) person).getCPF());
        }else{
            CNPJTextField.setText(((JuridicalPerson ) person).getCNPJ());
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
    private TextField telTextField;
    @FXML
    private TextField secTelTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField districtTextField;
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
    private ComboBox<String> City;

    @FXML
    private ComboBox<String> State;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isLegalPerson = true;
        personGroup.selectedToggleProperty().addListener((observable,oldValue,newValue) -> {
            isLegalPerson = observable.getValue().equals(legalPersonRadio);
            toggleFields();
        });
    }

    @FXML
    public void back() {

    }

    @FXML
    public void mainAction() {
        if(edit){
        
        }else{
            if(validate()){
                Address address = new Address(streetTextField.getText(), Integer.parseInt(numberTextField.getText()),districtTextField.getText(),"estado batata", "cidade batata");
                ArrayList<String> telephones = new ArrayList<>();
                telephones.add(telTextField.getText());
                if(!secTelTextField.getText().isEmpty()){
                    telephones.add(secTelTextField.getText());
                }
                if(isLegalPerson){
                    LegalPerson legalPerson = new LegalPerson(RGTextField.getText(), nameTextField.getText(), address, telephones,CPFTextField.getText());
                    System.out.println(legalPerson.toString()); 
               }else{
                    JuridicalPerson juridicalperson = new JuridicalPerson(nameTextField.getText(), address, telephones, CNPJTextField.getText());
                    System.out.println(juridicalperson.toString());
                }
            }
        }
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setIsLegalPerson(boolean isLegalPerson) {
        this.isLegalPerson = isLegalPerson;
    }
    

    private void toggleFields() {
        legalGroup.setVisible(isLegalPerson);
        CNPJTextField.setVisible(!isLegalPerson);
    }
    
    private void setRadioButtons(){
        if(isLegalPerson){
            legalPersonRadio.setSelected(true);
        }else{
            juridicalPersonRadio.setSelected(true);
        }
    }
    
    private boolean validate() {
        Validate valObj = new Validate();
        
        valObj.validateName(nameTextField.getText());
        if(isLegalPerson){
            valObj.validateRG(RGTextField.getText());
            valObj.validateCPF(CPFTextField.getText());
        }else{
            valObj.validateCNPJ(CNPJTextField.getText());
        }
        
        valObj.validateTelephone(telTextField.getText());
        if(!secTelTextField.getText().isEmpty()){
            valObj.validateTelephone(secTelTextField.getText());
        }
        
        valObj.validateAddressNumber(numberTextField.getText());
        valObj.validateStreet(streetTextField.getText());
        valObj.validateDistrict(districtTextField.getText());
        valObj.validateCity();
        valObj.validateState();
        
        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
