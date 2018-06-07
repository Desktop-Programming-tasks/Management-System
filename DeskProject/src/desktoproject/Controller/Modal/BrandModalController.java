/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Transactions.BrandDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class BrandModalController implements Initializable {

    private static final String PATH = "desktoproject/View/Modal/BrandModal.fxml";

    public static Parent call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BrandModalController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        BrandModalController controller = loader.getController();
        controller.setUpComponents();
        return p;
    }

    public static Parent call(Object brand) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BrandModalController.class.getClassLoader().getResource(PATH));

        Parent p = loader.load();
        BrandModalController controller = loader.getController();
        controller.setBrand((Brand) brand);
        controller.setEdit(true);
        controller.setUpComponents();

        return p;
    }

    private Brand brand;
    private boolean edit;

    private void setUpComponents() {
        if (edit) {
            mainLabel.setText("Editar Marca");
            mainBtn.setText("Alterar");
            fillScreen();
        } else {
            mainLabel.setText("Cadastrar Marca");
            mainBtn.setText("Cadastrar");
        }
    }

    private void fillScreen() {
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
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            Brand newBrand = new Brand(nameTextField.getText());

            try {
                if (edit) {
                    
                } else {
                    BrandDAO.insertBrand(newBrand);
                    GUIController.getInstance().showRegisterAlert("Marca");
                    GUIController.getInstance().closeModal();
                }

            } catch (RemoteException | DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            } catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Marca", "nome");
            }
        }
    }

    @FXML
    private void back() {
        GUIController.getInstance().closeModal();
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private void setEdit(boolean edit) {
        this.edit = edit;
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
}
