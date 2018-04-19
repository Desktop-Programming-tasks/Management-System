/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.query;

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
import javafx.scene.control.cell.PropertyValueFactory;
import main.GUIController;
import main.objects.transations.Brand;
import main.objects.transations.Movimentation;
import main.objects.transations.Product;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class GenericTransactionQueryController implements Initializable {

    private String transactionType;
    @FXML
    private Label genericTransactionQueryTitle;
    @FXML
    private Button genericTransationQueryBtn;
    @FXML
    private TableView MovimentationTable;
    @FXML
    private TableColumn<Movimentation,Integer> Code;
    @FXML
    private TableColumn<Movimentation,String> Description;
    @FXML
    private TableColumn<Movimentation,Float> Price;
    
    ObservableList<Movimentation> movs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        movs= FXCollections.observableArrayList();
        
        Product p1= new Product("111", 3, new Brand(1,"Nvidia"), 3, "Placa de video");
        Product p2= new Product("222", 5, new Brand(2,"Asus"), 100, "Placa mãe");
        Movimentation m = new Movimentation(p1,"Compra",3,10);
        movs.add(m);
        
        Movimentation m2= new Movimentation(p2, "Venda", 5, 12);
        movs.add(m2);
        
        Code.setCellValueFactory(new PropertyValueFactory<>("id"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Product"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        MovimentationTable.setItems(movs);
    }

    public void setInformation() {
        genericTransactionQueryTitle.setText("Consulta de " + transactionType);
        genericTransationQueryBtn.setText("Cancelar " + transactionType);
    }
    
    @FXML
    private void details(){
        GUIController.getInstance().showMainActionScreen(transactionType, true);
    }

    public void setTransactionType(String transationType) {
        this.transactionType = transationType;
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

}
