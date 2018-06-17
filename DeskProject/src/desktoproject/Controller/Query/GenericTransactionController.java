/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Enums.RecordType;
import Classes.Enums.ServiceStatus;
import Classes.Persons.Supplier;
import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.TransactionScreenMode;
import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Transactions.RecordDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Pairs.ScreenData;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class GenericTransactionController extends Controller implements Initializable, TableScreen, AppObserver {

//    private static final String genericTransactionPath = "desktoproject/View/Query/GenericTransaction.fxml";
//    
//    public static Parent call(TransactionQueryType type) throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(GenericTransactionController.class.getClassLoader().getResource(genericTransactionPath));
//        Parent p = loader.load();
//        GenericTransactionController controller = loader.getController();
//        controller.setType(type);
//        controller.setUpComponents();
//        return p;
//    }
    @FXML
    private Label mainLabel;
    @FXML
    private TableView<Record> transactionTable;
    @FXML
    private TableColumn<Record, String> typeColumn;
    @FXML
    private TableColumn<Record, String> codeColumn;
    @FXML
    private TableColumn<Record, String> dateColumn;
    @FXML
    private TableColumn<Record, String> nameColumn;
    @FXML
    private TableColumn<Record, String> priceColumn;
    @FXML
    private ComboBox<TransactionScreenMode> typeComboBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button detailsBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindAnimation(searchTextField);
        Animation.bindAnimation(typeComboBox);

        Animation.bindShadowAnimation(detailsBtn);
        Animation.bindShadowAnimation(backBtn);


        setUpTable();
        comboBoxSetup();
        loadComboBox();
        subscribe();
        populateTable();
    }
    
    private void setUpTable(){
        transactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory((TableColumn.CellDataFeatures<Record, String> p) -> {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            return new SimpleStringProperty(formatter.format(p.getValue().getRegisterDate()));
        });
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Record, String> p) -> {
            return new SimpleStringProperty(p.getValue().getCustomer().getName());
        });
        typeColumn.setCellValueFactory((TableColumn.CellDataFeatures<Record, String> p) -> {
            return new SimpleStringProperty(String.valueOf(p.getValue().getType()));
        });
    }
    
    @Override
    public void setUpSearch() {
        searchTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            newValue = newValue.trim();
            if(newValue.isEmpty()){
                populateTable();
            }else{
                try {
                    switch(typeComboBox.getSelectionModel().getSelectedItem()){
                        case ALL:{
                            transactionTable.setItems(FXCollections.observableArrayList(RecordDAO.queryRecord(newValue)));
                            break;
                        }
                        case PURCHASES:{
                            //
                            break;
                        }
                        case SALES:{
                            //
                            break;
                        }
                    }
                } catch (RemoteException|DatabaseErrorException ex) {
                    GUIController.getInstance().showConnectionErrorAlert();
                } catch (NoResultsException ex) {
                    
                }
            }
        });
    }

    @Override
    public void populateTable() {
        try {
            switch (typeComboBox.getSelectionModel().getSelectedItem()) {
                case ALL: {
                    transactionTable.setItems(FXCollections.observableArrayList(RecordDAO.queryAllRecords()));
                    break;
                }
                case PURCHASES: {
                    System.out.println("Carrega com as compras");
                    break;
                }
                case SALES: {
                    System.out.println("Carrega com as vendas");
                    break;
                }
            }
        } catch (RemoteException|DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }

    }

    private void comboBoxSetup() {
        typeComboBox.setCellFactory(new Callback<ListView<TransactionScreenMode>, ListCell<TransactionScreenMode>>() {
            @Override
            public ListCell<TransactionScreenMode> call(ListView<TransactionScreenMode> param) {
                return new ListCell<TransactionScreenMode>() {
                    @Override
                    protected void updateItem(TransactionScreenMode item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.translateEnumToGUI());
                        }
                    }
                };
            }
        });
        
        typeComboBox.setConverter(new StringConverter<TransactionScreenMode>() {
            @Override
            public String toString(TransactionScreenMode object) {
                if (object == null) {
                    return null;
                } else {
                    return object.translateEnumToGUI();
                }
            }

            @Override
            public TransactionScreenMode fromString(String string) {
                return null;
            }
        });
        
        typeComboBox.selectionModelProperty().addListener((observable) -> {
            subscribe();
            populateTable();
            searchTextField.setText("");
        });
    }

    private void loadComboBox() {
        typeComboBox.setItems(FXCollections.observableArrayList(TransactionScreenMode.values()));
        typeComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void details() {

    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.GENERIC_TRANSACTION_QUERY;
    }

    @Override
    public void setTableAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void selectTable(Object o) {
        //
    }

    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.clearAll();
        switch(typeComboBox.getSelectionModel().getSelectedItem()){
            case ALL:{
                ObservableServer.getTransaction().addObserver(this);
                break;
            }
            case PURCHASES:{
                ObservableServer.getBuy().addObserver(this); 
                break;
            }
            case SALES:{
                ObservableServer.getSale().addObserver(this);
                break;
            }
        }
    }
}
