/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.register;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import main.GUIController;
import main.objects.persons.Address;
import main.objects.persons.JuridicalPerson;
import main.objects.persons.LegalPerson;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class CustomerRegisterController implements Initializable {

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
    private Button actionBtn;
    @FXML
    private ToggleGroup personGroup;
    @FXML
    private Group legalGroup;

    private String personType = "legal";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        personGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton radio = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                if (radio.getId().toString().equals("legal")) {
                    CNPJTextField.setVisible(false);
                    legalGroup.setVisible(true);
                    personType = "legal";
                } else {
                    CNPJTextField.setVisible(true);
                    legalGroup.setVisible(false);
                    personType = "juridical";
                }

            }

        });

    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    public void register() {
        if (Validate.validateEmpty("Nome", nameTextField.getText())) {
            Validate.validateName(nameTextField.getText());
        }
        if (((RadioButton) personGroup.getSelectedToggle()).getId().toString().equals("legal")) {
            if (Validate.validateEmpty("RG", RGTextField.getText())) {
                Validate.validateRG(RGTextField.getText());
            }
            if (Validate.validateEmpty("CPF", CPFTextField.getText())) {
                Validate.validateCPF(CPFTextField.getText());
            }
        } else {
            if (Validate.validateEmpty("CNPJ", CNPJTextField.getText())) {
                Validate.validateCNPJ(CNPJTextField.getText());
            }
        }
        if (Validate.validateEmpty("Telefone", telTextField.getText())) {
            Validate.validateTelephone(telTextField.getText());
        }
        if (!secTelTextField.getText().isEmpty()) {
            Validate.validateTelephone(secTelTextField.getText());
        }
        Validate.validateEmpty("Rua", streetTextField.getText());
        Validate.validateEmpty("Bairro", districtTextField.getText());
        if (Validate.validateEmpty("Número", numberTextField.getText())) {
            Validate.validateAddressNumber(numberTextField.getText());
        }

        String errorMsg = Validate.getErrorMessage();
        GUIController.getInstance().showInformationAlert(errorMsg);
        if (errorMsg.isEmpty()) {
            Address address = new Address(streetTextField.getText(),
                    Integer.parseInt(numberTextField.getText()), districtTextField.getText(),
                    "dummy", "dummy");
            ArrayList<String> telephones = new ArrayList<>();
            telephones.add(telTextField.getText());
            if (!secTelTextField.getText().isEmpty()) {
                telephones.add(secTelTextField.getText());
            }
            if (personType.equals("legal")) {

                LegalPerson lp = new LegalPerson(RGTextField.getText(),nameTextField.getText(),
                        address, telephones, CPFTextField.getText());
                System.out.println(lp);
            }else{
                JuridicalPerson jp = new JuridicalPerson(nameTextField.getText(), 
                        address, telephones, CNPJTextField.getText());
                System.out.println(jp);
            }
        }
    }

    public void setEdit() {
        nameTextField.setText("Placeholder name 1");
        RGTextField.setText("12.654.156-8");
        CPFTextField.setText("132.165.458-45");
        telTextField.setText("(43)98756-5498");
        streetTextField.setText("Placeholder street");
        numberTextField.setText("245");
        districtTextField.setText("District 13");

        mainLabel.setText("Detalhe de Cliente");
        actionBtn.setText("Salvar");
    }
         @FXML
    private void showMainActionM(ActionEvent evt) {
        MenuItem btn = (MenuItem) (evt.getSource());
        String actionType;

        if (btn.getText().equals("Registro de Vendas")) {
            actionType = "Venda";
        } else {
            actionType = "Compra";
        }

        GUIController.getInstance().showMainActionScreen(actionType, false);
    }

    @FXML
    private void showGenericTransaction(ActionEvent evt) {
        MenuItem itemName = (MenuItem) (evt.getSource());

        GUIController.getInstance().showGenericTransationQuery(itemName.getText());
    }

    @FXML
    private void showServiceQuery() {
        GUIController.getInstance().showServiceQuery();
    }

    @FXML
    private void showBrandQuery() {
        GUIController.getInstance().showBrandQuery();
    }

    @FXML
    private void showStockQuery() {
        GUIController.getInstance().showStockQuery();
    }

    @FXML
    private void showPersonQuery(ActionEvent evt) {
        MenuItem itemName = (MenuItem) (evt.getSource());

        GUIController.getInstance().showPersonQuery(itemName.getText());
    }

    @FXML
    private void showSupplierQuery() {
        GUIController.getInstance().showSupplierQuery();
    }

    @FXML
    private void showCustomerRegister() {
        GUIController.getInstance().showCustomerRegister(false);
    }

    @FXML
    private void showEmployeeRegister() {
        GUIController.getInstance().showEmployeeRegister(false);
    }

    @FXML
    private void showProductRegister() {
        GUIController.getInstance().showProductRegister(false);
    }

    @FXML
    private void showSupplierRegister() {
        GUIController.getInstance().showSupplierRegister(false);
    }

    @FXML
    private void showModalRegisterBrand() {
        GUIController.getInstance().showModalRegisterBrand();
    }

    @FXML
    private void showModalService() {
        GUIController.getInstance().showModalService();
    }
}
