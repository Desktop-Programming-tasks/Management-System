/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import desktoproject.Controller.GUIController;
import desktoproject.Model.Classes.Transactions.Service;
import desktoproject.Model.Classes.Transactions.ServiceType;
import desktoproject.Utils.Misc;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class NewServiceController implements Initializable {

    private static final String newServicePath = "desktoproject/View/Modal/NewService.fxml";
    
    public static Parent call() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(NewServiceController.class.getClassLoader().getResource(newServicePath));
            return loader.load();
    }
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField valueTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Misc.setOnlyNumbers(valueTextField);
    }    
    
    @FXML
    private void register(){
        if(validate()){
            ServiceType service = new ServiceType(nameTextField.getText(), Float.valueOf(Misc.changeToDot(valueTextField.getText())));
            System.out.println(service.toString());
        }
    }
    
    @FXML
    private void back() {
        
    }
    
    private boolean validate(){
        Validate valObj = new Validate();
        valObj.validateName(nameTextField.getText());
        valObj.validateEmpty("Valor",valueTextField.getText());
        
        if(valObj.getErrorMessage().isEmpty()){
            return true;
        }else{
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
