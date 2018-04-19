/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.query;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.GUIController;
import main.objects.persons.Address;
import main.objects.persons.JuridicalPerson;
import main.objects.persons.LegalPerson;
import main.objects.persons.Person;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class PersonQueryController implements Initializable {

    String personType;
    
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
        // TODO
        System.out.println("adasdasdas");
        customers= FXCollections.observableArrayList();
        
        Address address = new Address("32321", 0, "dsdas", "dsda", "dsadsa");
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("dsada");
        LegalPerson lp= new LegalPerson("32312312","batata",address,telephones,"312321");
        customers.add(lp);
        JuridicalPerson jp = new JuridicalPerson("batata2", address, telephones, "9031231212");
        customers.add(jp);
        
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        PersonTable.setItems(customers);
    }    
    
    public void setInformation(){
        personQueryTitle.setText("Consulta de "+personType);
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
    
    @FXML
    private void createNew(){
        switch(personType) {
            case "Funcionários":
                GUIController.getInstance().showEmployeeRegister(false);
                break;
                
            case "Clientes":
                GUIController.getInstance().showCustomerRegister(false);
                break;
        }
    }
    
    @FXML
    private void detailsPerson(){
        switch(personType) {
            case "Funcionários":
                GUIController.getInstance().showEmployeeRegister(true);
                break;
                
            case "Clientes":
                GUIController.getInstance().showCustomerRegister(true);
                break;
        }
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
}
