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
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class BrandController implements Initializable {

    private static final String brandControllerPath = "desktoproject/View/Query/Brand.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BrandController.class.getClassLoader().getResource(brandControllerPath));
        return loader.load();
    }

    @FXML
    TableView<Brand> brandTable;
    @FXML
    TableColumn<Brand, String> nameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        setTableAction();
        populateTable();
    }
    
    private void setTableAction(){
        brandTable.setRowFactory(tv -> {
            TableRow<Brand> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Brand brand = row.getItem();
                    GUIController.getInstance().callModal(ModalType.BRAND_UPDATE, brand);
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
    }

    @FXML
    private void delete() {
        Brand brand = brandTable.getSelectionModel().getSelectedItem();
        if (brand == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            try {
                BrandDAO.deleteBrand(brand);
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (NoResultsException ex) {
                GUIController.getInstance().showDeleteError();
            }
        }
    }

    @FXML
    private void editBrand() {
        Brand brand = brandTable.getSelectionModel().getSelectedItem();
        if (brand == null) {
            GUIController.getInstance().showSelectionErrorAlert();
        } else {
            GUIController.getInstance().callModal(ModalType.BRAND_UPDATE, brand);
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
}
