/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class TemplateController implements Initializable {
    private String actionType;
    @FXML
    private Button registerBtnMainAction;
    @FXML
    private Label mainActionScreenTitle;
    @FXML
    private TextField customerOrSupplier;
    @FXML
    private TextArea description;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInformation();
    }    
    
    public void setInformation(){
        registerBtnMainAction.setText("Registrar "+actionType);
        mainActionScreenTitle.setText("Registrar "+actionType);
    }
    
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    public void register(){
        if(!customerOrSupplier.getText().isEmpty() && !description.getText().isEmpty()){
            if(Validate.validaCPF(customerOrSupplier.getText())){
                //go
                System.out.println("template::foi");
            }else{
                System.out.println("template::erro cpf");
            }
        }else{
            //Show error
            System.out.println("template::vazio");
        }
    }
    
    @FXML
    public void showModalAddService() {
        
    }
    
    @FXML
    public void showModalAddProduct() {
        GUIController.getInstance().showModalAddProduct();
    }
    
    @FXML
    public void deleteEntry() {
        
    }
}
