/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Interfaces.ControllerEdit;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Transactions.BrandDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Validate;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class BrandModalController extends ControllerEdit implements Initializable {

//    private static final String PATH = "desktoproject/View/Modal/BrandModal.fxml";
//
//    public static Parent call() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(BrandModalController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        BrandModalController controller = loader.getController();
//        controller.setEdit(false);
//        controller.setUpComponents();
//        return p;
//    }
//
//    public static Parent call(Object brand) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(BrandModalController.class.getClassLoader().getResource(PATH));
//
//        Parent p = loader.load();
//        BrandModalController controller = loader.getController();
//        controller.setBrand((Brand) brand);
//        controller.setEdit(true);
//        controller.setUpComponents();
//
//        return p;
//    }

    private Brand brand;

    public void setUpComponents() {
        if (isEdit()) {
            mainLabel.setText("Editar Marca");
            mainBtn.setText("Alterar");
            fillScreen();
        } else {
            mainLabel.setText("Cadastrar Marca");
            mainBtn.setText("Cadastrar");
        }
    }

    @Override
    public void fillScreen() {
        nameTextField.setText(brand.getName());
    }

    @FXML
    private TextField nameTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Animation.bindAnimation(nameTextField);
        Animation.bindShadowAnimation(mainBtn);
        Animation.bindShadowAnimation(backBtn);
        
        mainBtn.requestFocus();
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            Brand newBrand = new Brand(nameTextField.getText());

            try {
                if (isEdit()) {
                    newBrand.setId(brand.getId());
                    newBrand.setActive(brand.isActive());
                    BrandDAO.updateBrand(newBrand);
                    GUIController.getInstance().showUpdateAlert();
                    GUIController.getInstance().closeModal();
                } else {
                    BrandDAO.insertBrand(newBrand);
                    GUIController.getInstance().showRegisterAlert("Marca");
                    GUIController.getInstance().closeModal();
                }
            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Marca", "nome");
            } catch (NoResultsException ex) {
                System.out.println("No results from inside brand modal controller/ no problem");
            }
        }
    }

    @FXML
    private void back() {
        GUIController.getInstance().closeModal();
    }

    private boolean validate() {
        Validate valObj = new Validate();
        valObj.validateEmpty("Name", nameTextField.getText());
        //validate brand name length?
        //valObj.validateName(nameTextField.getText());
        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }

    @Override
    public void setScreenObject(Object obj) {
        this.brand = (Brand) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.BRAND_MODAL;
    }
}
