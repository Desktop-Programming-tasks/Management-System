/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.Interfaces.Controller;
import Classes.Enums.Autentication;
import Classes.Persons.Employee;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import RMI.RemoteLogin;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Globals;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Validate;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ecsanchesjr
 */
public class LoginController extends Controller implements Initializable {

//    private static final String path = "desktoproject/View/Login.fxml";
//    
//    public static Parent call() throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(LoginController.class.getClassLoader().getResource(path));
//        return loader.load();
//    }
    
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
        Validate valLogin = new Validate();
        
        String login = userTextField.getText();
        String pass = passTextField.getText();
        System.out.println("?????????");
        valLogin.validateEmpty("Campo de usuário", pass);
        valLogin.validateEmpty("Campo de senha", login);
        
        if(valLogin.getErrorMessage().isEmpty()) {
            System.out.println("????????");
            validateLogin(login, pass);
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro de login", "Campos não foram validados!", valLogin.getErrorMessage().toString());
        }
        
//        try {
//            ArrayList<String> telephones = new ArrayList<>();
//            telephones.add("9955999599");
//            telephones.add("6845465465465");
//            Address address = new Address("Rua Da batata quente", 13, "Seu cu", "Fodase", "E o caralho");
//            Employee employee = new Employee("login test", "password", EmployeeType.MANAGER, "87854", "employee of the month", address, telephones, "498431");
//            Globals.getInstance().setEmployee(employee);
//        } catch (RemoteException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        GUIController.getInstance().callScreen(ScreenType.INDEX);
    }
    
    private void validateLogin(String login, String password) {
        Autentication autentication = remoteValidationLogin(login, password);
        switch(autentication) {
            case SUCCESS: {
                loginSuccess(login);
                break;
            }
            case USERNAME_NOT_FOUND: {
                GUIController.getInstance().showAlert(Alert.AlertType.ERROR, 
                        "Erro ao login", 
                        "Login não cadastrado", "");
                break;
            }
            case WRONG_PASSWORD: {
                GUIController.getInstance().showAlert(Alert.AlertType.ERROR, 
                        "Erro ao login", 
                        "Senha inválida", "");
                break;
            }
        }
    }
    
    private Autentication remoteValidationLogin(String login, String password) {
        System.out.println("pq vc não tá aparecendo");
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry("localhost", RemoteLogin.RMI_PORT);
            RemoteLogin loginChannel = (RemoteLogin) rmiRegistry.lookup(RemoteLogin.RMI_LOGIN);
            System.out.println("?");
            return loginChannel.tryLogin(login, password);
        } catch (RemoteException | NotBoundException | DatabaseErrorException ex) {
            ex.printStackTrace();
            GUIController.getInstance().showConnectionErrorAlert();
        }
        return Autentication.ERROR_ON_LOGIN;
    }
    
    private void loginSuccess(String login) {
        try {
            getEmployeeData(login);
            Globals.getInstance().getObserverThread().start();
            GUIController.getInstance().callScreen(ScreenType.INDEX);
        } catch (RemoteException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getEmployeeData(String login) {
        try {
            Employee logged = Globals.getInstance().getChannel().queryEmployeeByLogin(login);
            Globals.getInstance().setEmployee(logged);
        } catch (RemoteException | DatabaseErrorException | NoResultsException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setPath() {
        this.path = FXMLPaths.LOGIN_SCREEN;
    }
}
