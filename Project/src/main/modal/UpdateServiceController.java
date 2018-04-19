/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class UpdateServiceController implements Initializable {
    
    @FXML
    private TextField textFieldServiceName;
    @FXML
    private TextField textFieldValue;
    @FXML
    private TextArea textAreaServiceDescription;
    @FXML
    private ComboBox comboBoxState;
    @FXML
    private ComboBox comboBoxEmployee;
    @FXML
    private DatePicker datePickerInit;
    @FXML
    private DatePicker datePickerEnd;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setEdit() {
        //change labels and load data from object
        textFieldServiceName.setText("BATATA");
        textFieldValue.setText("1222200000.00");
        textAreaServiceDescription.setText("Vou descrever essa música linda que tocou meu coração e pentrou no cu do kapriel aquele arrombado do caralho");
    }
}
