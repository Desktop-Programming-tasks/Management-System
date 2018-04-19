/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.register;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import main.GUIController;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        personGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton radio = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                if(radio.getId().toString().equals("legal")){
                    CNPJTextField.setVisible(false);
                    legalGroup.setVisible(true);
                }else{
                    CNPJTextField.setVisible(true);
                    legalGroup.setVisible(false);
                }
                
            }
            
        });
        
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML 
    public void register(){
        if(Validate.validateEmpty("Nome", nameTextField.getText())){
            Validate.validateName(nameTextField.getText());
        }
        if(((RadioButton)personGroup.getSelectedToggle()).getId().toString().equals("legal")){
            if(Validate.validateEmpty("RG", RGTextField.getText())){
                Validate.validateRG(RGTextField.getText());
            }
            if(Validate.validateEmpty("CPF", CPFTextField.getText())){
                Validate.validateCPF(CPFTextField.getText());
            }
        }else{
            if(Validate.validateEmpty("CNPJ", CNPJTextField.getText())){
                Validate.validateCNPJ(CNPJTextField.getText());
            }
        }
        if(Validate.validateEmpty("Telefone", telTextField.getText())){
            Validate.validateTelephone(telTextField.getText());
        }
        if(!secTelTextField.getText().isEmpty()){
            Validate.validateTelephone(secTelTextField.getText());
        }
        Validate.validateEmpty("Rua", streetTextField.getText());
        Validate.validateEmpty("Bairro", districtTextField.getText());
        if(Validate.validateEmpty("Número", numberTextField.getText())){
            Validate.validateAddressNumber(numberTextField.getText());
        }
        
        String errorMsg = Validate.getErrorMessage();
        System.out.println(errorMsg);
        if(errorMsg.isEmpty()){
            System.out.println("foi");
        }
        
    }
    
    public void setEdit(){
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
}
