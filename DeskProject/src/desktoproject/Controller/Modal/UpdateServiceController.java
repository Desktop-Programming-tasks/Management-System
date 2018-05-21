/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import desktoproject.Model.Classes.Transactions.Service;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class UpdateServiceController implements Initializable {
    
    public static Parent call(Object obj) throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UpdateServiceController.class.getClassLoader().getResource("desktoproject/View/Modal/UpdateService.fxml"));
            Parent p = loader.load();
            UpdateServiceController controller = loader.load();
            controller.setService((Service) obj);
            return p;
    }
    
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }
    
    @FXML
    private TextField textFieldServiceName;
    @FXML
    private TextField textFieldValue;
    @FXML
    private ComboBox comboBoxState;
    @FXML
    private ComboBox comboBoxEmployee;
    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    private void update(){
        
    }
    
    @FXML
    public void closeModal() {
        
    }
}
