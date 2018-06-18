/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Utils.Animation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author noda
 */
public class ServiceController extends Controller implements Initializable, TableScreen, AppObserver {
    
    @FXML
    private TableView serviceTable;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableColumn serviceTypeColumn;
    @FXML
    private TableColumn messageColumn;
    @FXML
    private ComboBox stateComboBox;
    @FXML
    private ComboBox serviceTypeComboBox;
    @FXML
    private ComboBox employeeComboBox;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button backBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(updateBtn);
        Animation.bindShadowAnimation(cancelBtn);
        Animation.bindShadowAnimation(backBtn);
        
        Animation.bindAnimation(stateComboBox);
        Animation.bindAnimation(serviceTypeComboBox);
        Animation.bindAnimation(employeeComboBox);
        
        serviceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        subscribe();
    }    
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    public void showModalUpdateService() {
        
    }    
    @FXML
    private void detailsProduct(){
        
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.SERVICE_QUERY;
    }

    @Override
    public void populateTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTableAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpSearch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectTable(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.getService().addObserver(this);
    }
}
