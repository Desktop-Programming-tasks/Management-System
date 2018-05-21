/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Enums.ModalType;
import desktoproject.Controller.Enums.ScreenType;
import desktoproject.Controller.Enums.TransactionType;
import desktoproject.Controller.Modal.AddProductController;
import desktoproject.Controller.Modal.AddServiceController;
import desktoproject.Controller.Modal.NewBrandController;
import desktoproject.Controller.Modal.NewServiceController;
import desktoproject.Controller.Modal.UpdateServiceController;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ecaanchesjr
 */
public class GUIController {

    private Stage mainStage;
    private Stage modalStage;

    private Parent indexParent;

    private Scene nowScene;
    private Scene previousScene;

    private Stack<Scene> executionStack;

    AnchorPane dynamic;

    private GUIController() {
        executionStack = new Stack<>();
    }

    public static GUIController getInstance() {
        return GUIControllerHolder.INSTANCE;
    }

    private static class GUIControllerHolder {

        private static final GUIController INSTANCE = new GUIController();
    }

    public void startApp(Stage stage) {
        try {
            mainStage = stage;
            mainStage.setMinWidth(1280);
            mainStage.setMinHeight(720);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("desktoproject/View/Menu.fxml"));
            indexParent = loader.load();
            MenuController controller = loader.getController();
            this.dynamic = controller.getDynamic();

            callScreen(ScreenType.INDEX);

            nowScene = new Scene(indexParent);
            executionStack.push(nowScene);
            mainStage.setScene(nowScene);
            mainStage.show();
            
        } catch (IOException ex) {
            System.out.println("Error starting app: " + ex.getMessage());
        }
        setUpModalStage();
    }

    private void setUpModalStage() {
        modalStage = new Stage();
        modalStage.initOwner(mainStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
    }

    private void setDynamicChild(Parent p) {
        this.dynamic.getChildren().clear();
        AnchorPane.setTopAnchor(p,32.00);
        AnchorPane.setLeftAnchor(p,32.00);
        AnchorPane.setBottomAnchor(p,32.00);
        AnchorPane.setRightAnchor(p,32.00);
        this.dynamic.getChildren().add(p);
    }
    
    //if no object is passed the callScreen is called with null
    //this method is used to call screens that don't need to display information of an already created object
    public void callScreen(ScreenType type){
        callScreen(type, null);
    }

    public void callScreen(ScreenType type, Object obj) {
        try {
            switch (type) {
                case INDEX: {
                    setDynamicChild(IndexController.call());
                    break;
                }
                case TRANSACTION_BUY_CREATE:{
                    setDynamicChild(TransactionController.call(TransactionType.BUY));
                    break;
                }
                case TRANSACTION_SALE_CREATE:{
                    setDynamicChild(TransactionController.call(TransactionType.SALE));
                    break;
                }
                case TRANSACTION_BUY_DISPLAY:{
                    setDynamicChild(TransactionController.call(TransactionType.BUY,obj));
                    break;
                }
                case TRANSACTION_SALE_DISPLAY:{
                    setDynamicChild(TransactionController.call(TransactionType.SALE,obj));
                    break;
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void callModal(ModalType type){
        callModal(type,null);
    }
    
    public void callModal(ModalType type, Object obj){
        try{
            switch(type){
                case PRODUCT_ADD:{
                    setUpModal(AddProductController.call());
                    break;
                }
                case BRAND_NEW:{
                    setUpModal(NewBrandController.call());
                    break;
                }
                case SERVICE_ADD:{
                    setUpModal(AddServiceController.call());
                    break;
                }
                case SERVICE_NEW:{
                    setUpModal(NewServiceController.call());
                    break;
                }
                case SERVICE_UPDATE:{
                    setUpModal(UpdateServiceController.call(obj));
                    break;
                }
            }
        }catch(IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setUpModal(Parent p){
        modalStage.setScene(new Scene(p));
        modalStage.show();
    }

    public void showInformationEraseAlert() {
        Alert aboutInfo = new Alert(Alert.AlertType.CONFIRMATION);

        aboutInfo.setTitle("Operação de remoção");
        aboutInfo.setHeaderText("Remoção bem sucedida!");
        aboutInfo.setContentText("Operação de remoção concluída!");

        DialogPane diagPanel = aboutInfo.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getResource("css/alert.css").toExternalForm());
        aboutInfo.showAndWait();
    }

    public void showInformationAlert(String msg) {
        Alert informationDiag;
        if (msg.isEmpty()) {
            informationDiag = new Alert(Alert.AlertType.CONFIRMATION);
            informationDiag.setTitle("Operação bem sucedida");
            informationDiag.setHeaderText("Confirmação de operação!");
            informationDiag.setContentText("Operação realizada com sucesso!");
        } else {
            informationDiag = new Alert(Alert.AlertType.ERROR);
            informationDiag.setTitle("Operação falhou");
            informationDiag.setHeaderText("Falha de operação");
            informationDiag.setContentText(msg);
        }
        DialogPane diagPanel = informationDiag.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getResource("css/alert.css").toExternalForm());
        informationDiag.showAndWait();
    }

    public void showAboutInformationAlert() {
        Alert aboutInfo = new Alert(Alert.AlertType.INFORMATION);

        aboutInfo.setTitle("Sobre o Software");
        aboutInfo.setHeaderText("Sistema de Gerênciamento para Lojas de Informática.");
        aboutInfo.setContentText("Software desenvolvido como trabalho prático para a \ndiscíplina de Programação Desktop.\n");

        DialogPane diagPanel = aboutInfo.getDialogPane();
        diagPanel.getStylesheets().add(getClass().getResource("css/alert.css").toExternalForm());
        aboutInfo.showAndWait();
    }

    public void closeModal() {
        modalStage.close();
    }

    public void backToPrevious() {
        executionStack.pop();
        nowScene = executionStack.peek();
        mainStage.setScene(nowScene);
        mainStage.show();
    }
}
