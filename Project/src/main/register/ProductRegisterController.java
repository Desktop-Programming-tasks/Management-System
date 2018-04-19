/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.register;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.GUIController;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class ProductRegisterController implements Initializable {

    @FXML
    private Button actionBtn;
    @FXML 
    private Label mainLabel;
    @FXML
    private TextField barCodeTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Group quantityGroup;
    @FXML
    private TextField quantityTextField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    public void setEdit(){
        //change labels and load data from object
        actionBtn.setText("Salvar");
        mainLabel.setText("Detalhes do Produto");
        
        quantityGroup.setVisible(true);
        quantityTextField.setText("99");
        
        barCodeTextField.setText("13249842313");
        nameTextField.setText("Produto 12346");
    }
}
