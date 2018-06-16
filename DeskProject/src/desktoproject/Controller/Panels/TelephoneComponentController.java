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
    
//    private static final String PATH = "desktoproject/View/Panels/TelephoneComponent.fxml";
//
//    public static ScreenObject call() throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(TelephoneComponentController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        TelephoneComponentController controller = loader.getController();
//        controller.setAnchors(p);
//        controller.setTelephones(new ArrayList<>());
//        return new ScreenObject(p, controller);
//    }
//    
//    public static ScreenObject call(Object obj) throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(TelephoneComponentController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        TelephoneComponentController controller = loader.getController();
//        controller.setAnchors(p);
//        controller.setTelephones((ArrayList<String>) obj);
//        controller.fillScreen();
//        return new ScreenObject(p, controller);
//    }
    
    @Override
    public void fillScreen() {
        setFields(telephones.get(0),((telephones.size()>1)?(telephones.get(1)):("")));
    }
    
//    private void setAnchors(Parent p){
//        AnchorPane.setTopAnchor(p,0.0);
//        AnchorPane.setLeftAnchor(p,0.0);
//        AnchorPane.setBottomAnchor(p,0.0);
//        AnchorPane.setRightAnchor(p,0.0);
//    }
    
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
