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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class NewServiceController implements Initializable {

    public static Parent call() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(NewServiceController.class.getClassLoader().getResource("desktoproject/View/Modal/NewService.fxml"));
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
    }    
    
    @FXML
    private void register(){
        
    }
    
    @FXML
    public void back() {
        
    }
}
