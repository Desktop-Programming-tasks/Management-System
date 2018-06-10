package desktoproject.Controller;


import desktoproject.Controller.Panels.AddressComponentController;
import desktoproject.Controller.Panels.TelephoneComponentController;
import desktoproject.Utils.Pairs.ScreenData;
import desktoproject.Utils.Pairs.ScreenObject;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ecsanchesjr
 */
public abstract class ControllerPersons extends ControllerEdit {
    private ScreenData addressComponent;
    private ScreenData telephoneComponent;
    
    @Override
    public ScreenData call() throws IOException {
        ScreenData callReturn = super.call();
        ControllerPersons controller = (ControllerPersons) callReturn.getController();
        controller.setSecondaryComponents();
        controller.setDynamicSecondary();
        return new ScreenData(callReturn.getParent(), controller);
    }
    
    @Override
    public ScreenData call(Object obj) throws IOException {
        ScreenData callReturn = super.call(obj);
        ControllerPersons controller = (ControllerPersons) callReturn.getController();
        controller.setSecondaryComponents();
        controller.setDynamicSecondary();
        return new ScreenData(callReturn.getParent(), controller);
    }
    
    private void setSecondaryComponents() throws IOException {
        setAddressComponent(new AddressComponentController().call());
        setTelephoneComponent(new TelephoneComponentController().call());
    }
    
    public abstract void setDynamicSecondary();

    public ScreenData getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(ScreenData addressComponent) {
        this.addressComponent = addressComponent;
    }

    public ScreenData getTelephoneComponent() {
        return telephoneComponent;
    }

    public void setTelephoneComponent(ScreenData telephoneComponent) {
        this.telephoneComponent = telephoneComponent;
    }
    
}
