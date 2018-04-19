/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.register;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.GUIController;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class SupplierRegisterController implements Initializable {
    @FXML
    private TextField nameTextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    public void register(){
        if(Validate.validateEmpty("'Nome do Cliente'", nameTextField.getText())) {
            Validate.validateName(nameTextField.getText());
        }
        if(Validate.validateEmpty("'CNPJ'", CNPJTextField.getText())) {
            Validate.validateCNPJ(CNPJTextField.getText());
        }
        if(Validate.validateEmpty("'Telefone principal'", telTextField.getText())) {
            Validate.validateTelephone(telTextField.getText());
        }
        Validate.validateEmpty("'Rua'", streetTextField.getText());
        Validate.validateEmpty("'Bairro'", districtTextField.getText());
        if(Validate.validateEmpty("'Número da casa'", numberTextField.getText())) {
            Validate.validateAddressNumber(numberTextField.getText());   
        }
        String errorMsg = Validate.getErrorMessage();
        System.out.println(errorMsg);
        if(errorMsg.isEmpty()){
            System.out.println("foi");
        }
    }
 
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    public void setEdit(){
        nameTextField.setText("Placeholder name 1");
        telTextField.setText("(43)98756-5498");
        streetTextField.setText("Placeholder street");
        numberTextField.setText("245");
        districtTextField.setText("District 13");
        
        mainLabel.setText("Detalhes de Fornecedor");
        actionBtn.setText("Salvar"); 
    }
}
