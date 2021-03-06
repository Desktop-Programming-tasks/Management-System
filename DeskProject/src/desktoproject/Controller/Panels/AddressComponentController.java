/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Panels;


import Classes.Persons.Address;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Controller.Interfaces.ControllerEdit;
import desktoproject.Controller.GUIController;
import desktoproject.Model.DAO.Persons.LocationsDAO;
import desktoproject.Utils.Animation;
import desktoproject.Utils.Misc;
import desktoproject.Utils.Validate;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import desktoproject.Controller.Interfaces.FXMLPaths;

/**
 * FXML Controller class
 *
 * @author noda
 */
public class AddressComponentController extends ControllerEdit implements Initializable {

    private Address address;
    
    @Override
    public void fillScreen() {
        setFields(address.getStreet(), String.valueOf(address.getNumber()), address.getDistrict(), address.getCity(), address.getState());
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
        Animation.bindAnimation(streetTextField);
        Animation.bindAnimation(numberTextField);
        Animation.bindAnimation(districtTextField);
        Animation.bindAnimation(cityComboBox);
        Animation.bindAnimation(stateComboBox);
        
        Misc.setOnlyNumbersWithDot(numberTextField);
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

    @Override
    public void setUpComponents() {
        // nothing happen here
    }

    @Override
    public void setScreenObject(Object obj) {
        this.address = (Address) obj;
    }

    @Override
    public void setPath() {
        this.path = FXMLPaths.ADDRESS_COMPONENT;
    }
}
