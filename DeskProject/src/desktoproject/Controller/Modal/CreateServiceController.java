/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Transactions.Service;
import desktoproject.Controller.GUIController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author noda
 */
public class CreateServiceController implements Initializable {

    private static final String PATH = "desktoproject/View/Modal/CreateService.fxml";
    
    public static Parent call() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CreateServiceController.class.getClassLoader().getResource(PATH));
            Parent p = loader.load();
            CreateServiceController controller = loader.getController();
            controller.setEdit(false);
            controller.setUpComponents();
            return p;
    }
    
    public static Parent call(Object obj) throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CreateServiceController.class.getClassLoader().getResource(PATH));
            Parent p = loader.load();
            CreateServiceController controller = loader.getController();
            controller.setService((Service) obj);
            controller.setEdit(true);
            controller.setUpComponents();
            return p;
    }
    
    private Service service;
    private boolean edit;
    
    private void setUpComponents() {
        if(edit){
            mainLabel.setText("Atualizar Serviço");
            primaryBtn.setText("Atualizar");
            comboBoxState.setVisible(true);
            fillScreen();
        }else{
            mainLabel.setText("Adicionar Serviço");
            primaryBtn.setText("Adicionar");
            comboBoxState.setVisible(false);
        }
    }

    private void fillScreen() {
        nameTextField.setText(service.getServiceType().getName());
        valueTextField.setText(String.valueOf(service.getPrice()));
        beginDate.setValue(service.getStartDate());
        endDate.setValue(service.getEstimatedDate());
        //setar estado e employee
    }
    
    @FXML
    private Label mainLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField valueTextField;
    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox comboBoxState;
    @FXML
    private ComboBox comboBoxEmployee;
    @FXML
    private Button primaryBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //value from database
        
    }    
    
    @FXML
    private void mainAction(){
        
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().closeModal();
    }
    
    private void setService(Service service) {
        this.service = service;
    }

    private void setEdit(boolean edit) {
        this.edit = edit;
    }
}
