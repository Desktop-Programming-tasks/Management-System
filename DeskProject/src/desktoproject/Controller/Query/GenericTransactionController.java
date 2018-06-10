/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Query;

import desktoproject.Controller.Controller;
import desktoproject.Controller.Enums.TransactionQueryType;
import static desktoproject.Controller.Enums.TransactionQueryType.ALL;
import desktoproject.Controller.FXMLPaths;
import desktoproject.Controller.GUIController;
import desktoproject.Controller.TableScreen;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Pairs.ScreenData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ecaanchesjr
 */
public class GenericTransactionController extends Controller implements Initializable, TableScreen {

//    private static final String genericTransactionPath = "desktoproject/View/Query/GenericTransaction.fxml";
//    
//    public static Parent call(TransactionQueryType type) throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(GenericTransactionController.class.getClassLoader().getResource(genericTransactionPath));
//        Parent p = loader.load();
//        GenericTransactionController controller = loader.getController();
//        controller.setType(type);
//        controller.setUpComponents();
//        return p;
//    }
    
    public ScreenData call(TransactionQueryType type) throws IOException {
        ScreenData callReturn = super.call();
        GenericTransactionController controller = (GenericTransactionController) callReturn.getController();
        controller.setType(type);
        controller.setUpComponents();
        return new ScreenData(callReturn.getParent(), controller);
    }
    
    private TransactionQueryType type;

    public void setType(TransactionQueryType type) {
        this.type = type;
    }
    
    private void setUpComponents(){
        switch(type){
            case ALL:{
                mainLabel.setText("Consulta de Transações");
                break;
            }
            case BUY:{
                mainLabel.setText("Consulta de Compras");
                break;
            }
            case SALE:{
                mainLabel.setText("Consulta de Vendas");
                break;
            }
        }
    }
    
    @FXML
    private Label mainLabel;
    @FXML
    private TableView transactionTable;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn codeColumn;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private ComboBox typeComboBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button detailsBtn;
    @FXML
    private Button backBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation.bindAnimation(searchTextField);
        Animation.bindAnimation(typeComboBox);
        
        Animation.bindShadowAnimation(detailsBtn);
        Animation.bindShadowAnimation(backBtn);
        
        transactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void details(){
        
    }

    @FXML
    public void back() {
        GUIController.getInstance().backToPrevious();
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.GENERIC_TRANSACTION_QUERY;
    }

    @Override
    public void populateTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTableAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpSearch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
