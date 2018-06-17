/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Enums.TransactionType;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.Interfaces.ControllerEdit;
import desktoproject.Controller.Interfaces.TableScreen;
import desktoproject.Controller.Observable.AppObserver;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import desktoproject.Model.DAO.Transactions.ProductDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import desktoproject.Utils.Pairs.ScreenData;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class AddProductController extends ControllerEdit implements Initializable, TableScreen, AppObserver {

    public ScreenData call(TransactionType type) throws IOException {
        ScreenData callReturn = super.call();
        AddProductController controller = (AddProductController) callReturn.getController();
        controller.type = type;
        controller.setUpProductQuantityListener();
        controller.populateTable();
        return new ScreenData(callReturn.getParent(), controller);
    }

    public ScreenData call(TransactionType type, Object obj) throws IOException {
        ScreenData callReturn = super.call(obj);
        AddProductController controller = (AddProductController) callReturn.getController();
        controller.type = type;
        controller.setUpProductQuantityListener();
        controller.populateTable();
        return new ScreenData(callReturn.getParent(), controller);
    }

    @FXML
    private TextField searchTextField;
    @FXML
    private TextField ProductPrice;
    @FXML
    private TextField ProductQuantity;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> brandColumn;
    @FXML
    private TableColumn<Product, String> codColumn;
    @FXML
    private TableColumn<Product, String> quantityColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private Button addBtn;
    @FXML
    private Button backBtn;

    private Product tmpProduct;
    private Product selectedProduct;
    private TransactionType type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Animation.bindAnimation(searchTextField);
        Animation.bindAnimation(ProductPrice);
        Animation.bindAnimation(ProductQuantity);
        Animation.bindShadowAnimation(addBtn);
        Animation.bindShadowAnimation(backBtn);

        productTable.requestFocus();

        tmpProduct = null;
        selectedProduct = null;

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> p) {
                return new SimpleStringProperty(p.getValue().getBrand().getName());
            }
        });
        codColumn.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));

        Misc.setOnlyNumbers(ProductQuantity);
        blockFields(true);
        setTableAction();
        setUpSearch();
        subscribe();
    }

    @Override
    public void setUpSearch() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.trim();
            if (newValue.isEmpty()) {
                populateTable();
            } else {
                try {
                    productTable.setItems(FXCollections.observableArrayList(ProductDAO.searchProduct(newValue)));
                } catch (RemoteException | DatabaseErrorException ex) {

                }
            }
        });
    }

    @Override
    public void populateTable() {
        try {
            Product selectedTableProduct = productTable.getSelectionModel().getSelectedItem();
            ArrayList<Product> allProducts = ProductDAO.queryAllProducts();
            if(type == TransactionType.SALE) {
                allProducts.removeIf(p -> p.getQuantityInStock() == 0);
            }
            productTable.setItems(FXCollections.observableArrayList(allProducts));
            selectTable(selectedTableProduct);
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
            System.out.println(ex.getMessage());
        } catch (NoResultsException ex) {
            //
        }
    }

    @Override
    public void selectTable(Object o) {
        if (o != null) {
            Product cp = (Product) o;
            for (Product p : productTable.getItems()) {
                if (p.getId() == cp.getId()) {
                    productTable.getSelectionModel().select(p);
                }
            }
        }
    }

    private void blockFields(boolean block) {
        ProductQuantity.setDisable(block);
        ProductPrice.setDisable(block);
        ProductQuantity.setText("");
        ProductPrice.setText("");
    }

    @Override
    public void setTableAction() {
        productTable.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    tmpProduct = row.getItem();
                    blockFields(false);
                    ProductQuantity.setText(String.valueOf(1));
                    ProductPrice.setText(Misc.changeToComma(String.valueOf((1 * tmpProduct.getPrice()))));
                } else {
                    blockFields(true);
                }
            });
            return row;
        });
    }

    @FXML
    public void selectProduct() {
        if (validate()) {
            selectedProduct = productTable.getSelectionModel().getSelectedItem();
            selectedProduct.setQuantity(Integer.valueOf(ProductQuantity.getText()));
            selectedProduct.setPrice(Float.valueOf(Misc.changeToDot(ProductPrice.getText())));
            GUIController.getInstance().closeModal();
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().closeModal();
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    private boolean validate() {
        Validate valObj = new Validate();
        if (valObj.validateEmpty("Quantidade", ProductQuantity.getText())) {
            valObj.validateNumber(ProductQuantity.getText());
        }
        valObj.validatePrice(ProductPrice.getText());
        valObj.emptyTableSelection(productTable, " um produto");

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.ADD_PRODUCT_MODAL;
    }

    @Override
    public void update() {
        populateTable();
    }

    @Override
    public void subscribe() {
        ObservableServer.getProduct().addObserver(this);
    }

    @Override
    public void setUpComponents() {
        //
    }

    private void setUpProductQuantityListener() {
        ProductQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ProductQuantity.getText().isEmpty()) {
                Product p = productTable.getSelectionModel().getSelectedItem();
                long quant = Long.valueOf(ProductQuantity.getText());
                if (type == TransactionType.SALE) {
                    if (quant > p.getQuantityInStock()) {
                        ProductQuantity.setText(String.valueOf(p.getQuantityInStock()));
                        quant = p.getQuantityInStock();
                    }
                }
                ProductPrice.setText(Misc.changeToComma(String.valueOf(quant * tmpProduct.getPrice())));
            } else {
                ProductPrice.setText("0");
            }
        });
    }

    @Override
    public void fillScreen() {
        selectTable(tmpProduct);
        Float price = tmpProduct.getPrice();
        int quantity = tmpProduct.getQuantity();
        try {
            tmpProduct.setPrice(ProductDAO.queryProduct(tmpProduct.getBarCode()).getPrice());
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
        ProductPrice.setText(Misc.changeToComma(String.valueOf(price * quantity)));
        ProductQuantity.setText(String.valueOf(quantity));
        ProductQuantity.setDisable(false);
    }

    @Override
    public void setScreenObject(Object obj) {
        tmpProduct = (Product) obj;
    }
}
