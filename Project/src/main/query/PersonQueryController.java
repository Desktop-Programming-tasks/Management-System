/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.query;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.GUIController;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class PersonQueryController implements Initializable {

    String personType;
    
    @FXML 
    private Label personQueryTitle;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setInformation(){
        personQueryTitle.setText("Consulta de "+personType);
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
}
