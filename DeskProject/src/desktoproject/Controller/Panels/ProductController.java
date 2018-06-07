/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;

import Classes.Transactions.Brand;
import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.UnavailableBrandException;
import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Transactions.BrandDAO;
import desktoproject.Model.DAO.Transactions.ProductDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import static desktoproject.Utils.Misc.changeToComma;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class ProductController implements Initializable {

    private static final String panelProductPath = "desktoproject/View/Panels/Product.fxml";
    private Stage stage;

    public static Parent call(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProductController.class.getClassLoader().getResource(panelProductPath));
        Parent p = loader.load();
        ProductController controller = loader.getController();
        controller.setStage(stage);
        controller.setUpComponents();
        controller.setEdit(false);
        return p;
    }

    public static Parent call(Object product, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getClassLoader().getResource(panelProductPath));
        Parent p = loader.load();

        ProductController controller = loader.getController();
        controller.setStage(stage);
        controller.setProduct((Product) product);
        controller.setEdit(true);
        controller.setUpComponents();

        return p;
    }

    private Product product;
    private boolean edit;
    private Brand brand;

    private void setUpComponents() {
        if (edit) {
            mainBtn.setText("Alterar");
            mainLabel.setText("Editar Produto");
            brand = product.getBrand();
            fillScreen();
        } else {
            mainBtn.setText("Cadastrar");
            mainLabel.setText("Cadastrar Produto");
            brand = null;
        }
        
        quantityGroup.setVisible(edit);
        barCodeTextField.setDisable(edit);
        
        setStageBreak();
        adjustComponents();
    }

    private void setStageBreak() {
        stage.widthProperty().addListener((observable) -> {
            adjustComponents();
        });
    }
    
    private void adjustComponents(){
        if (stage.getWidth() > 1056) {
                ObservableList<Node> workingCollection = FXCollections.observableArrayList(vBox.getChildren());
                vBox.getChildren().clear();
                for (Node n : workingCollection) {
                    hBox.getChildren().add(n);
                    HBox.setHgrow(n, Priority.ALWAYS);
                }
                hBox.toFront();
            } else {
                ObservableList<Node> workingCollection = FXCollections.observableArrayList(hBox.getChildren());
                hBox.getChildren().clear();
                for (Node n : workingCollection) {
                    vBox.getChildren().add(n);
                }
                vBox.toFront();
            }
    }

    private void fillScreen() {
        nameTextField.setText(product.getName());
        priceTextField.setText(changeToComma(String.valueOf(product.getPrice())));
        barCodeTextField.setText(product.getBarCode());
        quantityTextField.setText(String.valueOf(product.getQuantity()));
        
        selectTableBrand();
    }
    
    private void selectTableBrand(){
        for(Brand b : brandsTable.getItems()){
            if(b.getName().equals(product.getBrand().getName())){
                brandsTable.getSelectionModel().select(b);
            }
        }
    }

    @FXML
    private Button mainBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button createBrand;
    @FXML
    private Label mainLabel;
    @FXML
    private Label brandLabel;
    @FXML
    private TextField barCodeTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private HBox quantityGroup;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;

    @FXML
    private TableView<Brand> brandsTable;

    @FXML
    private TableColumn<Brand, String> brandsColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindAnimation(barCodeTextField);
        Animation.bindAnimation(nameTextField);
        Animation.bindAnimation(quantityTextField);
        Animation.bindAnimation(priceTextField);
        Animation.bindShadowAnimation(mainBtn);
        Animation.bindShadowAnimation(backBtn);
        Animation.bindShadowAnimation(createBrand);
        
        
        brandsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        Misc.setOnlyNumbersWithComma(priceTextField);
        Misc.setOnlyNumbers(barCodeTextField);
        setTableListeners();
        populateTable();
    }

    private void populateTable() {
        try {
            brandsTable.setItems(FXCollections.observableArrayList(BrandDAO.queryAllBrands()));
        } catch (RemoteException | DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
    }
    
    private void setTableListeners(){
        brandsTable.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            brandLabel.setText(brandsTable.getSelectionModel().getSelectedItem().getName());
        });
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            Product newProduct = new Product(barCodeTextField.getText(), brandsTable.getSelectionModel().getSelectedItem(), Float.valueOf(Misc.changeToDot(priceTextField.getText())), nameTextField.getText());

            try {
                if (edit) {
                    ProductDAO.updateProduct(newProduct);
                    GUIController.getInstance().showUpdateAlert();
                    GUIController.getInstance().backToPrevious();
                } else {
                    ProductDAO.insertProduct(newProduct);
                    GUIController.getInstance().showRegisterAlert("Produto");
                    GUIController.getInstance().backToPrevious();
                }
            } catch (RemoteException|DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Produto", "Código de barras");
            } catch (UnavailableBrandException ex) {
                GUIController.getInstance().showBrandExceptioAlert();
            }catch (NoResultsException ex) {
                GUIController.getInstance().showUpdateErrorAlert();
            }
        }
    }

    @FXML
    private void createNewBrand() {
        GUIController.getInstance().callModal(ModalType.BRAND_NEW);
        populateTable();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private boolean validate() {
        Validate valObj = new Validate();

        valObj.validateNumber(barCodeTextField.getText());

        valObj.validateName(nameTextField.getText());

        valObj.validatePrice(priceTextField.getText());

        valObj.emptyTableSelection(brandsTable,"uma marca");

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }
}
