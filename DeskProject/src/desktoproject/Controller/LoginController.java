/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import Classes.Enums.EmployeeType;
import Classes.Persons.Address;
import Classes.Persons.Employee;
import RMI.RemoteLogin;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Globals;
import desktoproject.Utils.Animation;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Animation.bindAnimation(passTextField);
        Animation.bindAnimation(userTextField);
        Animation.bindShadowAnimation(loginBtn);
    }    
    
    @FXML
    private void login(){
        try {
            ArrayList<String> telephones = new ArrayList<>();
            telephones.add("9955999599");
            telephones.add("6845465465465");
            Address address = new Address("Rua Da batata quente", 13, "Seu cu", "Fodase", "E o caralho");
            Employee employee = new Employee("login test", "password", EmployeeType.MANAGER, "87854", "employee of the month", address, telephones, "498431");
            Globals.getInstance().setEmployee(employee);
        } catch (RemoteException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GUIController.getInstance().callScreen(ScreenType.INDEX);
    }
}
