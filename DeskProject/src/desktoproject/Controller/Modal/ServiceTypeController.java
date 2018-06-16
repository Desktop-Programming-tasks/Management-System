/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import desktoproject.Controller.Interfaces.ControllerEdit;
import desktoproject.Controller.Interfaces.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Transactions.ServiceTypeDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import static desktoproject.Utils.Misc.changeToComma;
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
public class ServiceTypeController extends ControllerEdit implements Initializable {

//    private static final String PATH = "desktoproject/View/Modal/ServiceType.fxml";
//    
//
//    public static Parent call() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(ServiceTypeController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        ServiceTypeController controller = loader.getController();
//        controller.setEDIT(false);
//        controller.setUpComponents();
//        return p;
//    }
//
//    public static Parent call(Object serviceType) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(ServiceTypeController.class.getClassLoader().getResource(PATH));
//        Parent p = loader.load();
//        ServiceTypeController controller = loader.getController();
//        controller.setEDIT(true);
//        controller.setServiceType((ServiceType) serviceType);
//        controller.setUpComponents();
//
//        return p;
//    }

    public void setUpComponents() {
        if (isEdit()) {
            mainLabel.setText("Editar Serviço");
            mainBtn.setText("Alterar");
            fillScreen();
        } else {
            mainLabel.setText("Registrar Serviço");
            mainBtn.setText("Registrar");
        }
    }

    @Override
    public void fillScreen() {
        nameTextField.setText(serviceType.getName());
        System.out.println(serviceType.getPrice());
        System.out.println(String.valueOf(serviceType.getPrice()));
        valueTextField.setText(changeToComma(String.valueOf(serviceType.getPrice())));
    }

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField valueTextField;
    @FXML
    private Label mainLabel;
    @FXML
    private Button mainBtn;
    @FXML
    private Button backBtn;
    
    private ServiceType serviceType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Animation.bindAnimation(nameTextField);
        Animation.bindAnimation(valueTextField);
        Animation.bindShadowAnimation(mainBtn);
        Animation.bindShadowAnimation(backBtn);
        
        Misc.setOnlyNumbersWithComma(valueTextField);
        
    }

    @FXML
    private void mainAction() {
        if (validate()) {
            ServiceType newServiceType = new ServiceType(nameTextField.getText(), Float.valueOf(Misc.changeToDot(valueTextField.getText())));
            try {
                if (isEdit()) {
                    newServiceType.setId(serviceType.getId());
                    ServiceTypeDAO.updateServiceType(newServiceType);
                    GUIController.getInstance().showUpdateAlert();
                    GUIController.getInstance().closeModal();
                } else {
                    ServiceTypeDAO.insertServiceType(newServiceType);
                    GUIController.getInstance().showRegisterAlert("Tipo de serviço");
                    GUIController.getInstance().closeModal();
                }
            } catch (RemoteException|DatabaseErrorException ex) {
                GUIController.getInstance().showConnectionErrorAlert();
            }catch (DuplicatedEntryException ex) {
                GUIController.getInstance().showDupplicatedAlert("Tipo de serviço", newServiceType.getName());
            }
        }

    }

    @FXML
    private void back() {
        GUIController.getInstance().closeModal();
    }

    private boolean validate() {
        Validate valObj = new Validate();
        valObj.validateName(nameTextField.getText());
        valObj.validateEmpty("Valor", valueTextField.getText());

        if (valObj.getErrorMessage().isEmpty()) {
            return true;
        } else {
            GUIController.getInstance().showAlert(Alert.AlertType.ERROR, "Erro", "Erro de validação", valObj.getErrorMessage());
            return false;
        }
    }

    @Override
    public void setScreenObject(Object obj) {
        this.serviceType = (ServiceType) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.SERVICE_TYPE_MODAL;
    }
}
