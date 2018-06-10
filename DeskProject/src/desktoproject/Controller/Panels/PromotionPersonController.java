/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Persons.Employee;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.PersonPromotion;
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
 * @author ecsanchesjr
 */
public class PromotionPersonController implements Initializable {

    private static final String promotionFXMLPath = "desktoproject/View/Panels/PromotionPerson.fxml";

    public static Parent call(PersonPromotion type) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PromotionPersonController.class.getClassLoader().getResource(promotionFXMLPath));
        Parent p = loader.load();
        PromotionPersonController controller = loader.getController();
        controller.setType(type);
        controller.setUpComponents();
        return p;
    }

    private PersonPromotion type;

    private void setType(PersonPromotion type) {
        this.type = type;
    }

    private void setUpComponents() {
        switch (type) {
            case LEGAL_PERSON: {
                mainLabel.setText("Promoção de pessoas físicas");
                break;
            }
            case JURIDICAL_PERSON: {
                mainLabel.setText("Promoção de pessoas jurídicas");
                break;
            }
        }
        populateTable();
    }
    @FXML
    private Label mainLabel;
    @FXML
    private Button promotionButton;
    @FXML
    private Button backBtn;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> personNameColumn;
    @FXML
    private TableColumn<Person, String> personDocColumn;
    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(promotionButton);

        personDocColumn.setCellValueFactory(new PropertyValueFactory<>("documentId"));
        personNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        setTableAction();
        setUpSearch();
    }

    @FXML
    private void promote() {
        if (personTable.getSelectionModel().getSelectedItem() != null) {
            switch (type) {
                case JURIDICAL_PERSON: {
                    JuridicalPerson person = (JuridicalPerson) personTable.getSelectionModel().getSelectedItem();
                    GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_PROMOTE, new Supplier(person));
                    break;
                }
                case LEGAL_PERSON: {
                    LegalPerson person = (LegalPerson) personTable.getSelectionModel().getSelectedItem();
                    GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_PROMOTE, new Employee(person));
                    break;
                }
            }
        } else {
            GUIController.getInstance().showSelectionErrorAlert();
        }
    }

    private void setUpSearch() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.trim();
            if (newValue.isEmpty()) {
                populateTable();
            } else {
                try {
                    if (type == PersonPromotion.LEGAL_PERSON) {
                        personTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(PersonDAO.searchLegalPersons(newValue))));
                    } else {
                        personTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(PersonDAO.searchJuridicalPersons(newValue))));
                    }
                } catch (RemoteException | DatabaseErrorException e) {
                }
            }
        });
    }

    private void setTableAction() {
        personTable.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Person person = personTable.getSelectionModel().getSelectedItem();
                if (person != null) {
                    if (type == PersonPromotion.LEGAL_PERSON) {
                        GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_PROMOTE, new Employee((LegalPerson) person));
                    } else {
                        GUIController.getInstance().callScreen(ScreenType.SUPPLIER_PROMOTE, new Supplier((JuridicalPerson) person));
                    }
                }
            }
        });

        personTable.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Person person = row.getItem();
                    if (type == PersonPromotion.LEGAL_PERSON) {
                        GUIController.getInstance().callScreen(ScreenType.EMPLOYEE_PROMOTE, new Employee((LegalPerson) person));
                    } else {
                        GUIController.getInstance().callScreen(ScreenType.SUPPLIER_PROMOTE, new Supplier((JuridicalPerson) person));
                    }
                }
            });
            return row;
        });
    }

    private void populateTable() {
        try {
            if (type == PersonPromotion.LEGAL_PERSON) {
                personTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllLegalPersons()));
            } else {
                personTable.setItems(FXCollections.observableArrayList(PersonDAO.queryAllJuridicalPersons()));
            }
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
            System.out.println(ex.getMessage());
        } catch (NoResultsException ex) {
            //
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
}
