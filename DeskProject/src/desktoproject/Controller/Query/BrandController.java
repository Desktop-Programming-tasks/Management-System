/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Transactions.BrandDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class BrandController implements Initializable {

    private static final String PATH = "desktoproject/View/Query/Brand.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BrandController.class.getClassLoader().getResource(PATH));
        return loader.load();
    }

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        Animation.bindShadowAnimation(editBtn);
        Animation.bindShadowAnimation(createBtn);
        Animation.bindShadowAnimation(backBtn);
        
        setTableAction();
        populateTable();
    }
    
    private void setTableAction(){
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

    public void populateTable() {
        try {
            brandTable.setItems(FXCollections.observableArrayList(BrandDAO.queryAllBrands()));
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
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
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
}
