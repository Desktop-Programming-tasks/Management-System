/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import desktoproject.Model.Classes.Persons.Employee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class EmployeeController implements Initializable {
    
    private static final String panelEmployeePath = "desktoproject/View/Panels/Employee.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(panelEmployeePath));
        Parent p = loader.load();
        EmployeeController controller = loader.getController();
        controller.setUpComponents();
        return p;
    }
    
    public static Parent call(Object employee) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(panelEmployeePath));        
        Parent p = loader.load();
        
        EmployeeController controller = loader.getController();
        controller.setEmployee((Employee) employee);
        controller.setEdit(true);
        controller.setUpComponents();
        
        return p;
    }
    
    private Employee employee;
    private boolean edit;
    
    private void setUpComponents() {
        if(edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Funcionário");
            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Funcionário");
        }
    }
    
    private void fillScreen(){
        nameTextField.setText(employee.getName());
        RGTextField.setText(employee.getRG());
        CPFTextField.setText(employee.getCPF());
        telTextField.setText(employee.getTelephones().get(0));
        if(employee.getTelephones().size()>1){
            secTelTextField.setText(employee.getTelephones().get(1));
        }
        streetTextField.setText(employee.getAddress().getStreet());
        numberTextField.setText(String.valueOf(employee.getAddress().getNumber()));
        districtTextField.setText(employee.getAddress().getBlock());
        
        userTextField.setText(employee.getLogin());
        //dont set the password, only detect changes in password if anything new is writen in the password and confirm password fields
    }
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField RGTextField;
    @FXML
    private TextField CPFTextField;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordFieldOficial;
    @FXML
    private PasswordField passwordFieldConfirm;
    @FXML
    private TextField telTextField;
    @FXML
    private TextField secTelTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField districtTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private ComboBox<String> City;
    @FXML
    private ComboBox<String> State;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void back() {
        
    }
    
    @FXML
    private void register(){
        
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
