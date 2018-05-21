/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Modal;

import desktoproject.Model.Classes.Transactions.Brand;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class BrandModalController implements Initializable {
    
    public static Parent call() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BrandModalController.class.getClassLoader().getResource("desktoproject/View/Modal/BrandModal.fxml"));
            Parent p = loader.load();
            BrandModalController controller = loader.getController();
            controller.setUpComponents();
            return p;
    }
    
    public static Parent call(Object brand) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BrandModalController.class.getClassLoader().getResource("desktoproject/View/Modal/BrandModal.fxml"));
        
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
        if(edit) {
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void actionClick(){
        
    }
    
    @FXML
    public void back() {
        
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
