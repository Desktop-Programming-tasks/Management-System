/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Enums.ScreenType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class IndexController implements Initializable {

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IndexController.class.getClassLoader().getResource("desktoproject/View/Index.fxml"));
        return loader.load();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void callBuy(){
        System.out.println("entrou na buy");
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_BUY_CREATE);
    }
    
    @FXML
    private void callSale(){
        System.out.println("entrou na sale");
        GUIController.getInstance().callScreen(ScreenType.TRANSACTION_SALE_CREATE);
    }
}
