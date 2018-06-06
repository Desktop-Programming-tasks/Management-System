/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Transactions.Product;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Transactions.ProductDAO;
import desktoproject.Model.DAO.Transactions.ServiceTypeDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author viniciusmn
 */
public class ServiceTypeQueryController implements Initializable {

    private static final String PATH = "desktoproject/View/Query/ServiceTypeQuery.fxml";
    
    public static Parent call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServiceController.class.getClassLoader().getResource(PATH));        
        return loader.load();
    }
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<ServiceType> ServiceTable;
    @FXML
    private TableColumn<ServiceType,String> nameColumn;
    @FXML
    private TableColumn<ServiceType,String> priceColumn;
    @FXML
    private TextField searchTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        populateTable();
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
                    ServiceTable.setItems(FXCollections.observableArrayList(ServiceTypeDAO.searchServiceTypes(newValue)));
                } catch (RemoteException|DatabaseErrorException ex) {
                    
                }
            }
        });
    }
    
    private void populateTable(){
        try {
            ServiceTable.setItems(FXCollections.observableArrayList(ServiceTypeDAO.queryAllServiceTypes()));
        }catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        }
        //
        
    }
    
    private void setTableAction(){
        ServiceTable.setRowFactory(tv -> {
            TableRow<ServiceType> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ServiceType serviceType = row.getItem();
                    GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_EDIT, serviceType);
                    populateTable();
                }
            });
            return row;
        });
    }
    
    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void newServiceType(){
        GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_CREATE);
        populateTable();
    }
    
    @FXML
    private void editServiceType(){
        ServiceType serviceType = ServiceTable.getSelectionModel().getSelectedItem();
        if(serviceType==null){
            GUIController.getInstance().showSelectionErrorAlert();
        }else{
            GUIController.getInstance().callModal(ModalType.SERVICE_TYPE_EDIT,serviceType);
            populateTable();
        }
    }
}
