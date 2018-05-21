/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class AddServiceController implements Initializable {

    public static Parent call() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddServiceController.class.getClassLoader().getResource("desktoproject/View/Modal/AddService.fxml"));
            return loader.load();
    }
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField valueTextField;
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
        //value from database
        
    }    
    
    @FXML
    private void addService(){
        
    }
    
    @FXML
    public void back() {
        
    }
}
