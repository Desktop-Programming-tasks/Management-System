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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ecsanchesjr
 */
public class LoginController implements Initializable {

    private static final String path = "desktoproject/View/Login.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LoginController.class.getClassLoader().getResource(path));
        return loader.load();
    }
    
    @FXML
    TextField passTextField;
    @FXML
    TextField userTextField;
    @FXML
    Button loginBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void login(){
        GUIController.getInstance().callScreen(ScreenType.INDEX);
    }
}
