/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Controller.Interfaces.ControllerEdit;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Validate;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import desktoproject.Controller.Interfaces.FXMLPaths;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class TelephoneComponentController extends ControllerEdit implements Initializable {
    
    @Override
    public void fillScreen() {
        setFields(telephones.get(0),((telephones.size()>1)?(telephones.get(1)):("")));
    }
    
    private void setFields(String primary, String secondary){
        telTextField.setText(primary);
        secTelTextField.setText(secondary);
    }
    
    private ArrayList<String> telephones;
    
    @FXML
    private TextField telTextField;
    @FXML
    private TextField secTelTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Animation.bindAnimation(telTextField);
        Animation.bindAnimation(secTelTextField);
        telephones = new ArrayList<>();
    }    

    private void setTelephones(ArrayList<String> telephones) {
        this.telephones = telephones;
    }
    
    public String getPrimary(){
        return telTextField.getText();
    }
    
    public String getSecondary(){
        return secTelTextField.getText();
    }
    
    public ArrayList<String> getTelephones(){
        telephones.clear();
        telephones.add(telTextField.getText());
        if(!secTelTextField.getText().isEmpty()){
            telephones.add(secTelTextField.getText());
        }else{
            telephones.add("");
        }
        return(telephones);
    }
    
    public String validateFields(){
        Validate valObj = new Validate();
        valObj.validateTelephone(telTextField.getText());
        if(!secTelTextField.getText().isEmpty()){
            valObj.validateTelephone(secTelTextField.getText());
        }
        return valObj.getErrorMessage();
    }

    @Override
    public void setUpComponents() {
        // nothing happen here
    }

    @Override
    public void setScreenObject(Object obj) {
        this.telephones = (ArrayList<String>) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.TELEFONE_COMPONENT;
    }
}
