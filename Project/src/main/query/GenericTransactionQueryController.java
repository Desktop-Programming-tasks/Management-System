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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.GUIController;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class GenericTransactionQueryController implements Initializable {
    
    private String transactionType;
    
    @FXML
    private Label genericTransactionQueryTitle;
    @FXML
    private Button genericTransationQueryBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setInformation(){
        genericTransactionQueryTitle.setText("Consulta de "+transactionType);
        genericTransationQueryBtn.setText("Cancelar "+transactionType);
    }
    
    public void setTransactionType(String transationType) {
        this.transactionType = transationType;
    }    
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
}
