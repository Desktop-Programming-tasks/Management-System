/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Persons.Person;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import Exceptions.OperationNotAllowed;
import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Enums.PersonQueryType;
import static desktoproject.Controller.Enums.PersonQueryType.CUSTOMER;
import static desktoproject.Controller.Enums.PersonQueryType.EMPLOYEE;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Pairs.ScreenData;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class PersonController extends Controller implements Initializable, TableScreen, AppObserver {
    
    public ScreenData call(PersonQueryType type) throws IOException {
        ScreenData callReturn = super.call();
        PersonController controller = (PersonController) callReturn.getController();
        controller.setType(type);
        controller.setUpComponents();
        return new ScreenData(callReturn.getParent(), controller);
    }

    private PersonQueryType type;

    private void setType(PersonQueryType type) {
        System.out.println("set type "+type.name());
        this.type = type;
    }

    private void setUpComponents() {
        System.out.println("SetUpComponents "+type.name());
        switch (type) {
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
        populateTable();
        subscribe();
    }

    @FXML
    private Label mainLabel;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> personDocColumn;
    @FXML
    private TableColumn<Person, String> personNameColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button editBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindAnimation(searchTextField);
        Animation.bindShadowAnimation(editBtn);
        Animation.bindShadowAnimation(createBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(deleteBtn);
        
        personDocColumn.setCellValueFactory(new PropertyValueFactory<>("documentId"));
        personNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        setTableAction();
        setUpSearch();
    }

    @Override
    public void setUpSearch(){
        searchTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            newValue = newValue.trim();
            if(newValue.isEmpty()){
                populateTable();
            }else{
                try {
                    if(type==EMPLOYEE){
                        personTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(PersonDAO.searchEmployees(newValue))));
                    }else{
                        personTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(PersonDAO.searchPersons(newValue))));
                    }
                } catch (RemoteException|DatabaseErrorException ex) {
                    
                }
            }
        });
    }
    
    @Override
    public void setTableAction() {
        personTable.setOnKeyReleased((event) -> {
            if(event.getCode() == KeyCode.ENTER){
                Person item = personTable.getSelectionModel().getSelectedItem();
                if(item!=null){
                    if (type == PersonQueryType.CUSTOMER) {
                        GUIController.getInstance().callScreen(ScreenType.CUSTOMER_DISPLAY, item);
                    } else {
                        GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_DISPLAY, item);
                    }
                }
            }
        });
        
        personTable.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Person person = row.getItem();
                    if (type == PersonQueryType.CUSTOMER) {
                        GUIController.getInstance().callScreen(ScreenType.CUSTOMER_DISPLAY, person);
                    } else {
                        GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_DISPLAY, person);
                    }
                }
            });
            return row;
        });
    }

    @Override
    public void populateTable() {
        try {
            Person p = personTable.getSelectionModel().getSelectedItem();
            if (type == PersonQueryType.CUSTOMER) {
                personTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllPersons()));
            } else {
                personTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllEmployees()));
            }
            selectTable(p);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
            System.out.println(ex.getMessage());
        } catch (NoResultsException ex) {
            //
        }
    }
    
    @Override
    public void selectTable(Object o) {
        if(o!=null){
            Person cp = (Person)o;
            for(Person p : personTable.getItems()){
                if(p.getId() == cp.getId()){
                    personTable.getSelectionModel().select(p);
                }
            }
        }
    }

    @FXML
    private void createNew() {
        if(type == PersonQueryType.CUSTOMER){
            GUIController.getInstance().callScreen(ScreenType.CUSTOMER_CREATE);
        }else{
            GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_CREATE);
        }
    }

    @FXML
    private void detailsPerson() {
        Person person = personTable.getSelectionModel().getSelectedItem();
        if (person == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            if (type == PersonQueryType.CUSTOMER) {
                GUIController.getInstance().callScreen(ScreenType.CUSTOMER_DISPLAY, person);
            } else {
                GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_DISPLAY, person);
            }
        }
    }

    @FXML
    private void delete() {
        Person person = personTable.getSelectionModel().getSelectedItem();
        if (person == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            try {
                if (GUIController.getInstance().showEraseConfirmationAlert(person.getName())) {
                    PersonDAO.deletePerson(person);
                    populateTable();
                }
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (NoResultsException ex) {
                GUIController.getInstance().showDeleteErrorAlert();
            } catch (OperationNotAllowed ex) {
                GUIController.getInstance().showOperationNotAllowed();
            }
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.PERSON_QUERY;
    }

    @Override
    public void update() {
        System.out.println("pq vc não tá workando?");
        populateTable();
    }

    @Override
    public void subscribe() {
        System.out.println("whats happen "+(type == null));
        if(type == PersonQueryType.CUSTOMER){
            System.out.println("observable customer");
            ObservableServer.getClient().addObserver(this);
        }else{
            System.out.println("observable employee");
            ObservableServer.getEmployee().addObserver(this);
        }
    }
}
