/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Transactions.ProductDAO;
import desktoproject.Utils.Animation;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class StockController implements Initializable {

    private static final String stockPath = "desktoproject/View/Query/Stock.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StockController.class.getClassLoader().getResource(stockPath));
        return loader.load();
    }

    @FXML
    private TableView<Product> StockTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> brandColumn;
    @FXML
    private TableColumn<Product, String> quantityColumn;
    @FXML
    private TableColumn<Product, String> codeColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button editBtn;
    @FXML
    private Button newBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button deleteBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindShadowAnimation(newBtn);
        Animation.bindShadowAnimation(editBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(deleteBtn);
        
        Animation.bindAnimation(searchTextField);
        
        StockTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));
        brandColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Product, String> p) {
                return new SimpleStringProperty(p.getValue().getBrand().getName());
            }
        });
        
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
                    StockTable.setItems(FXCollections.observableArrayList(ProductDAO.searchProduct(newValue)));
                } catch (RemoteException|DatabaseErrorException ex) {
                    
                }
            }
        });
    }
    
    private void populateTable(){
        try {
            StockTable.setItems(FXCollections.observableArrayList(ProductDAO.queryAllProducts()));
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
            System.out.println(ex.getMessage());
        } catch (NoResultsException ex) {
            //
        }
    }
    
    private void setTableAction(){
        StockTable.setOnKeyReleased((event) -> {
            if(event.getCode() == KeyCode.ENTER){
                Product item = StockTable.getSelectionModel().getSelectedItem();
                if(item!=null){
                    GUIController.getInstance().callScreen(ScreenType.PRODUCT_DISPLAY, item);
                }
            }
        });
        StockTable.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product product = row.getItem();
                    GUIController.getInstance().callScreen(ScreenType.PRODUCT_DISPLAY, product);
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
    private void create(){
        GUIController.getInstance().callScreen(ScreenType.PRODUCT_CREATE);
    }

    @FXML
    private void detailsProduct() {
        Product product = StockTable.getSelectionModel().getSelectedItem();
        if(product==null){
            GUIController.getInstance().showSelectionErrorAlert();
        }else{
            GUIController.getInstance().callScreen(ScreenType.PRODUCT_DISPLAY, product);
            populateTable();
        }
    }
    
    @FXML
    private void delete(){
        Product product = StockTable.getSelectionModel().getSelectedItem();
        if(product==null){
            GUIController.getInstance().showSelectionErrorAlert();
        }else{
            try {
                if (GUIController.getInstance().showEraseConfirmationAlert(product.getName())) {
                    ProductDAO.deleteProduct(product);
                    populateTable();
                }
            } catch (RemoteException|DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            }catch (NoResultsException ex) {
                System.out.println("ops - no result in stock controller");
            }
        }
    }
}
