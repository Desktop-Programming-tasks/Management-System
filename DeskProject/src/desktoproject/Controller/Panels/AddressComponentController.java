/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;


import Classes.Persons.Address;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.LocationsDAO;
import desktoproject.Utils.Pairs.ScreenObject;
import desktoproject.Utils.Validate;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class AddressComponentController implements Initializable {
    
    private static final String PATH = "desktoproject/View/Panels/addressComponent.fxml";
    
    public static ScreenObject call() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddressComponentController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        AddressComponentController controller = loader.getController();
        controller.setAnchors(p);
        return new ScreenObject(p, controller);
    }
    
    public static ScreenObject call(Object obj) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddressComponentController.class.getClassLoader().getResource(PATH));
        Parent p = loader.load();
        AddressComponentController controller = loader.getController();
        controller.setAnchors(p);
        controller.setAddress((Address) obj);
        controller.fillScreen();
        return new ScreenObject(p, controller);
    }
    
    private Address address;
    
    private void fillScreen() {
        setFields(address.getStreet(), String.valueOf(address.getNumber()), address.getDistrict(), address.getCity(), address.getState());
    }
    
    private void setAnchors(Parent p){
        AnchorPane.setTopAnchor(p,0.0);
        AnchorPane.setLeftAnchor(p,0.0);
        AnchorPane.setBottomAnchor(p,0.0);
        AnchorPane.setRightAnchor(p,0.0);
    }
    
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField districtTextField;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private ComboBox<String> stateComboBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ArrayList<String> states = LocationsDAO.getStates();
            stateComboBox.setItems(FXCollections.observableArrayList(states));
            if(!states.isEmpty()){
                stateComboBox.setValue(states.get(0));
                getCityList();
            }
        } catch (RemoteException|DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
        
    }
    
    @FXML
    private void getCityList(){
        try {
            ArrayList<String> cities = LocationsDAO.getCities(stateComboBox.getValue());
            cityComboBox.setItems(FXCollections.observableArrayList(cities));
            if(!cities.isEmpty()){
                cityComboBox.setValue(cities.get(0));
                cityComboBox.setDisable(false);
            }else{
                cityComboBox.setDisable(true);
            }
        } catch (RemoteException|DatabaseErrorException ex) {
            GUIController.getInstance().showConnectionErrorAlert();
        } catch (NoResultsException ex) {
            //
        }
        
    }
    
    private void setFields(String street, String number, String district, String city, String state){
        streetTextField.setText(street);
        numberTextField.setText(number);
        districtTextField.setText(district);
        cityComboBox.setValue(city);
        stateComboBox.setValue(state);
    }
    
    public String validateFields(){
        Validate valObj = new Validate();
        valObj.validateAddressNumber(numberTextField.getText());
        valObj.validateStreet(streetTextField.getText());
        valObj.validateDistrict(districtTextField.getText());
        valObj.validateCity(cityComboBox.getValue());
        valObj.validateState(stateComboBox.getValue());
        return valObj.getErrorMessage();
    }
    
    public String getStreet(){
        return streetTextField.getText();
    }
    
    public int getNumber(){
        return Integer.valueOf(numberTextField.getText());
    }
    
    public String getDistrict(){
        return districtTextField.getText();
    }
    
    public String getCity(){
        return cityComboBox.getValue()==null?"":cityComboBox.getValue();
    }
    
    public String getState(){
        return stateComboBox.getValue()==null?"":stateComboBox.getValue();
    }
    
    public Address getAddress(){
        return new Address(getStreet(), getNumber(), getDistrict(), getCity(), getState());
    }
    
    private void setAddress(Address address) {
        this.address = address;
    }
}
