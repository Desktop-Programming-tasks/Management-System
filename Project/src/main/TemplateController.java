/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.objects.transations.Transation;
import main.utils.TableProxyTransation;
import main.utils.Validate;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class TemplateController implements Initializable {
    private String actionType;
    @FXML
    private Button registerBtnMainAction;
    @FXML
    private Label mainActionScreenTitle;
    @FXML
    private Label FinalPrice;
    @FXML
    private TextField customerOrSupplier;
    
    @FXML
    private TableView Transations;
    @FXML
    private TableColumn<TableProxyTransation,String> Name;
    @FXML
    private TableColumn<TableProxyTransation,Float> Price;
    @FXML
    private TableColumn<TableProxyTransation,Integer> Quantity;
    
    ObservableList<TableProxyTransation> transations;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transations= FXCollections.observableArrayList();
        TableProxyTransation tbt = new TableProxyTransation();
        tbt.setName("Item 1");
        tbt.setPrice(200);
        tbt.setQuantity(3);
        transations.add(tbt);
        
        TableProxyTransation tbt2 = new TableProxyTransation();
        tbt2.setName("Item 2");
        tbt2.setPrice(250);
        tbt2.setQuantity(1);
        transations.add(tbt2);
        
        TableProxyTransation tbt3 = new TableProxyTransation();
        tbt3.setName("Item 3");
        tbt3.setPrice(1230);
        tbt3.setQuantity(1);
        transations.add(tbt3);
        
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Transations.setItems(transations);
        float totalvalue=0;
        for(TableProxyTransation tbtaux: transations){
            totalvalue+=tbtaux.getPrice();
        }
        FinalPrice.setText(Float.toString(totalvalue));
        
        setInformation();
    }    
    
    public void setInformation(){
        registerBtnMainAction.setText("Registrar "+actionType);
        mainActionScreenTitle.setText("Registrar "+actionType);
    }
    
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    
    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }
    
    @FXML
    public void register(){
        if(!customerOrSupplier.getText().isEmpty()){
            if(Validate.validateCPF(customerOrSupplier.getText())){
                //go
                System.out.println("template::foi");
            }else{
                System.out.println(Validate.getErrorMessage());
                System.out.println("template::erro cpf");
            }
        }else{
            //Show error
            System.out.println("template::vazio");
        }
    }
    
    @FXML
    public void showModalAddService() {
        GUIController.getInstance().showModalAddService();
    }
    
    @FXML
    public void showModalAddProduct() {
        GUIController.getInstance().showModalAddProduct();
    }
    
    @FXML
    public void deleteEntry() {
        
    }
}
