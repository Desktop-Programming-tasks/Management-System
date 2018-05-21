/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Model.Classes.Persons.JuridicalPerson;
import desktoproject.Model.Classes.Persons.LegalPerson;
import desktoproject.Model.Classes.Persons.Person;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class CustomerController implements Initializable {

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CustomerController.class.getClassLoader().getResource("desktoproject/View/Register/CustomerRegister.fxml"));
        return loader.load();
    }

    public static Parent call(Object person) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CustomerController.class.getClassLoader().getResource("desktoproject/View/Register/CustomerRegister.fxml"));
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

            toggleFields(isLegalPerson = (person instanceof LegalPerson));
        } else {
            mainLabel.setText("Cadastrar Cliente");
            mainBtn.setText("Cadastrar");
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

    }

    @FXML
    public void back() {

    }

    @FXML
    public void register() {

    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    private void toggleFields(boolean legal) {
        legalGroup.setDisable(!legal);
        CNPJTextField.setDisable(legal);
        legalPersonRadio.setSelected(legal);
        juridicalPersonRadio.setSelected(!legal);
    }
}
