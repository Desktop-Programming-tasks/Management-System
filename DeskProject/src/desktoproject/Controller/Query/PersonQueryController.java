/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Controller.Enums.PersonQueryType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class PersonQueryController implements Initializable {
    
    public static Parent call(PersonQueryType type) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PersonQueryController.class.getClassLoader().getResource("desktoproject/View/Query/PersonQuery.fxml"));
        PersonQueryController controller = loader.getController();
        controller.setType(type);
        controller.setUpComponents();
        return loader.load();
    }
    
    private PersonQueryType type;
    
    private void setType(PersonQueryType type) {
        this.type = type;
    }
    
    private void setUpComponents() {
        switch(type) {
            case CUSTOMER: {
                mainLabel.setText("Consulta de Clientes");
                personDocColumn.setText("CPF/CNPJ");
                break;
            }
            case EMPLOYEE: {
                mainLabel.setText("Consulta de Funcionários");
                personDocColumn.setText("CPF");
                break;
            }
        }
    }
    
    @FXML 
    private Label mainLabel;
    @FXML
    private TableView PersonTable;
    @FXML
    private TableColumn personDocColumn;
    @FXML
    private TableColumn personNameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void createNew(){
        
    }
    
    @FXML
    private void detailsPerson(){
        
    }
    
    @FXML
    public void back(){
        
    }
}
