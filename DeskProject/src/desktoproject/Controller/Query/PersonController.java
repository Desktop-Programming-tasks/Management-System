/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Persons.Person;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.PersonQueryType;
import static desktoproject.Controller.Enums.PersonQueryType.CUSTOMER;
import static desktoproject.Controller.Enums.PersonQueryType.EMPLOYEE;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 * @author noda
 */
public class PersonController implements Initializable {
    
    private static final String personControllerPath = "desktoproject/View/Query/Person.fxml";
    
    public static Parent call(PersonQueryType type) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PersonController.class.getClassLoader().getResource(personControllerPath));
        Parent p = loader.load();
        PersonController controller = loader.getController();
        controller.setType(type);
        controller.setUpComponents();
        return p;
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
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person,String> personDocColumn;
    @FXML
    private TableColumn<Person,String> personNameColumn;
    @FXML
    private TextField searchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTable();
    }
    
    private void populateTable(){
        try {
            personTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllPersons()));
        } catch (RemoteException|DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
            System.out.println(ex.getMessage());
        } catch (NoResultsException ex) {
            //
        }
    }
    
    @FXML
    private void createNew(){
        
    }
    
    @FXML
    private void detailsPerson(){
        
    }
    
    @FXML
    public void back(){
        GUIController.getInstance().backToPrevious();
    }
}
