/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.modal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.GUIController;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField ProductName;
    @FXML
    private TextField ProductPrice;
    @FXML
    private TextField ProductQuantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProductPrice.setText("50.60");
        ProductPrice.setDisable(true);
    }

    @FXML
    public void addProduct() {
        if(Validate.validateEmpty("Name", ProductName.getText())){
            Validate.validateName(ProductName.getText());
        }
        if(Validate.validateEmpty("Quantidade",ProductQuantity.getText())){
            Validate.validateAddressNumber(ProductQuantity.getText());
        }
        String errorMsg = Validate.getErrorMessage();
        System.out.println(errorMsg);
        if(errorMsg.isEmpty()){
            System.out.println("foi");
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().closeModal();
    }
}
