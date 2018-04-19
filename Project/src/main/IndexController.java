/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showMainAction(ActionEvent evt) {
        String btnName = (evt.getSource()).toString();
        String actionType;
        
        if(btnName.equals("VENDA")) {
            actionType = "Venda";
        } else {
            actionType = "Compra";
        }
        
        GUIController.getInstance().showMainActionScreen(actionType);
    }
    
    @FXML
    private void showGenericTransaction(ActionEvent evt) {
        MenuItem itemName = (MenuItem) (evt.getSource());
        
        GUIController.getInstance().showGenericTransationQuery(itemName.getText());
    }
    
}
