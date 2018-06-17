/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Interfaces.Controller;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Transactions.BrandDAO;
import desktoproject.Utils.Animation;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class BrandController extends Controller implements Initializable, TableScreen, AppObserver {
//
//    private static final String PATH = "desktoproject/View/Query/Brand.fxml";
//
//    public static Parent call() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(BrandController.class.getClassLoader().getResource(PATH));
//        return loader.load();
//    }

    @FXML
    private TableView<Brand> brandTable;
    @FXML
    private TableColumn<Brand, String> nameColumn;
    @FXML
    private Button editBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField searchTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        Animation.bindAnimation(searchTextField);

        Animation.bindShadowAnimation(editBtn);
        Animation.bindShadowAnimation(createBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(deleteBtn);
        
        setTableAction();
        populateTable();
        subscribe();
    }
    
    @Override
    public void setTableAction(){
        brandTable.setOnKeyReleased((event) -> {
            if(event.getCode() == KeyCode.ENTER){
                Brand item = brandTable.getSelectionModel().getSelectedItem();
                if(item!=null){
                    GUIController.getInstance().callModal(ModalType.BRAND_UPDATE, item);
                    populateTable();
                }
            }
        });
        
        brandTable.setRowFactory(tv -> {
            TableRow<Brand> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    System.out.println("entrou no click");
                    Brand brand = row.getItem();
                    GUIController.getInstance().callModal(ModalType.BRAND_UPDATE, brand);
                    populateTable();
                }
            });
            return row;
        });
    }

    @Override
    public void populateTable() {
        try {
            Brand selectedBrand = brandTable.getSelectionModel().getSelectedItem();
            brandTable.setItems(FXCollections.observableArrayList(BrandDAO.queryAllBrands()));
            selectTable(selectedBrand);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
    }
    @Override
    public void selectTable(Object o) {
        if (o != null) {
            Brand cb = (Brand)o;
            for (Brand b : brandTable.getItems()) {
                if (b.getId() == cb.getId()) {
                    brandTable.getSelectionModel().select(b);
                }
            }
        }
    }

    @FXML
    private void createNewBrand() {
        GUIController.getInstance().callModal(ModalType.BRAND_NEW);
        populateTable();
    }

    @FXML
    private void editBrand() {
        Brand brand = brandTable.getSelectionModel().getSelectedItem();
        if (brand == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            GUIController.getInstance().callModal(ModalType.BRAND_UPDATE, brand);
            populateTable();
        }
    }

    @FXML
    private void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void delete(){
        Brand brand = brandTable.getSelectionModel().getSelectedItem();
        if (brand == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            try {
                if (GUIController.getInstance().showEraseConfirmationAlert(brand.getName())) {
                    BrandDAO.deleteBrand(brand);
                    populateTable();
                }
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (NoResultsException ex) {
                GUIController.getInstance().showDeleteErrorAlert();
            }
        }
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.BRAND_QUERY;
    }

    @Override
    public void setUpSearch() {
        searchTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            newValue = newValue.trim();
            if(newValue.isEmpty()){
                populateTable();
            }else{
//                try {
//                    brandTable.setItems(FXCollections.observableArrayList(BrandDAO.));
//                } catch (RemoteException|DatabaseErrorException ex) {
//                    GUIController.getInstance().showConnectionErrorAlert();
//                }
            }
        });
    }
    
    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.getBrand().addObserver(this);
    }
}
