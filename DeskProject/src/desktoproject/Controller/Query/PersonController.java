/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Persons.Person;
import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import Exceptions.OperationNotAllowed;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.PersonQueryType;
import static desktoproject.Controller.Enums.PersonQueryType.CUSTOMER;
import static desktoproject.Controller.Enums.PersonQueryType.EMPLOYEE;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.PersonDAO;
import desktoproject.Utils.Animation;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class PersonController implements Initializable {

    private static final String personControllerPath = "desktoproject/View/Query/Person.fxml";

    public static Parent call(PersonQueryType type) throws IOException {
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
        switch (type) {
            case CUSTOMER: {
                mainLabel.setText("Consulta de Clientes");
                personDocColumn.setText("CPF/CNPJ");
                deleteBtn.setVisible(false);
                break;
            }
            case EMPLOYEE: {
                mainLabel.setText("Consulta de Funcionários");
                personDocColumn.setText("CPF");
                deleteBtn.setVisible(true);
                break;
            }
        }
        populateTable();
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

    private void setUpSearch(){
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
    
    private void setTableAction() {
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

    private void populateTable() {
        try {
            if (type == PersonQueryType.CUSTOMER) {
                personTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllPersons()));
            } else {
                personTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllEmployees()));
            }
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
            System.out.println(ex.getMessage());
        } catch (NoResultsException ex) {
            //
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
}
