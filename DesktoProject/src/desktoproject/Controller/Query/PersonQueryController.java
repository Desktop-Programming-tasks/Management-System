/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Model.Classes.Persons.Person;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class PersonQueryController implements Initializable {
    
    @FXML 
    private Label personQueryTitle;
    @FXML
    private TableView<Person> PersonTable;
    @FXML
    private TableColumn<Person,String> Id;
    @FXML
    private TableColumn<Person,String> Name;
    
    ObservableList<Person> customers;
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
