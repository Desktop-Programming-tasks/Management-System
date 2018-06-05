/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Transactions.Service;
import Classes.Transactions.ServiceType;
import desktoproject.Controller.GUIController;
import desktoproject.Utils.Misc;
import desktoproject.Utils.Pairs.ScreenObject;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceTypeController implements Initializable {

    private static final String PATH = "desktoproject/View/Modal/ServiceType.fxml";
    private boolean EDIT;
    private Service service;
    
    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServiceTypeController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        ServiceTypeController controller = loader.getController();
        controller.setEDIT(false);
        return p;
    }

    public static Parent call(Object service) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServiceTypeController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        ServiceTypeController controller = loader.getController();
        controller.setEDIT(true);
        
        return p;
    }
    
    
    private void setUpComponents(){
        if(EDIT){
            mainLabel.setText("Editar Serviço");
            mainBtn.setText("Alterar");
        }else{
            mainLabel.setText("Registrar Serviço");
            mainBtn.setText("Registrar");
        }
    }
    
    private void fillScreen(){
        nameTextField.setText(service.getName());
        valueTextField.setText(String.valueOf(service.getPrice()));
    }

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField valueTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Misc.setOnlyNumbersWithComma(valueTextField);
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            ServiceType newService = new ServiceType(nameTextField.getText(), Float.valueOf(Misc.changeToDot(valueTextField.getText())));
            
        }
    }

    @FXML
    private void back() {
        GUIController.getInstance().closeModal();
    }

    private boolean validate() {
        Validate valObj = new Validate();
        valObj.validateName(nameTextField.getText());
        valObj.validateEmpty("Valor", valueTextField.getText());

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }

    public void setEDIT(boolean EDIT) {
        this.EDIT = EDIT;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
