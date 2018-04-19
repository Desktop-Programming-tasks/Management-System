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
    }

    @FXML
    public void addProduct() {
        if (!ProductName.getText().isEmpty() && !ProductPrice.getText().isEmpty()
                && !ProductQuantity.getText().isEmpty()) {
            try {
                Integer.parseInt(ProductQuantity.getText());
            } catch (Exception e) {
                System.out.println("AddProduct::Erro");
            }
        }
    }

    @FXML
    public void back() {
        GUIController.getInstance().closeModal();
    }
}
