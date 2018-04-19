/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.query;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.GUIController;
import main.objects.persons.Address;
import main.objects.transations.Brand;
import main.objects.persons.Supplier;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class SupplierQueryController implements Initializable {

    @FXML
    private TableView Suppliers;
    @FXML
    private TableColumn<Supplier, String> Cnpj;
    @FXML
    private TableColumn<Supplier, String> Name;
    @FXML
    private TableColumn<Supplier,String> Brands;
    ObservableList<Supplier> suppliers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        suppliers = FXCollections.observableArrayList();
        //
        Address address = new Address("32321", 0, "dsdas", "dsda", "dsadsa");
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add("dsada");
        ArrayList<Brand> marcas = new ArrayList<>();
        marcas.add(new Brand(1, "Nvidia"));
        marcas.add(new Brand(1,"AMD"));
        Supplier s  = new Supplier(marcas, "Fulano", address, telephones, "323123121/100-1");
        suppliers.add(s);
        
        ArrayList<Brand> marcas2 = new ArrayList<>();
        marcas2.add(new Brand(1, "Corsair"));
        marcas2.add(new Brand(1,"XFX"));
        marcas2.add(new Brand(1,"Intel"));
        Supplier s2  = new Supplier(marcas2, "Fulano2", address, telephones, "332131254754/100-1");
        suppliers.add(s2);
        //
        Cnpj.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Brands.setCellValueFactory(new PropertyValueFactory<>("avaliableBrands"));
        Suppliers.setItems(suppliers);

    }

    @FXML
    private void back(ActionEvent event) {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    private void createNew() {
        GUIController.getInstance().showSupplierRegister(false);
    }
    
    @FXML
    private void editSupplier() {
        GUIController.getInstance().showSupplierRegister(true);
    }

}
