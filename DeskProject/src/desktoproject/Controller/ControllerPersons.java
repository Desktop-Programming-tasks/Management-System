/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Controller.Panels.AddressComponentController;
import desktoproject.Controller.Panels.TelephoneComponentController;
import desktoproject.Utils.Pairs.ScreenData;
import desktoproject.Utils.Pairs.ScreenObject;
import java.io.IOException;

/**
 *
 * @author ecsanchesjr
 */
public abstract class ControllerPersons extends ControllerEdit {
    private ScreenObject addressComponent;
    private ScreenObject telephoneComponent;
    
    public static ScreenData call() throws IOException {
        ScreenData callReturn = ControllerPersons.call();
        ControllerPersons controllerPersons = (ControllerPersons) callReturn.getController();
        controllerPersons.setSecondaryComponents();
        return new ScreenData(callReturn.getParent(), controllerPersons);
    }
    
    public static ScreenData call(Object obj) throws IOException {
        ScreenData callReturn = ControllerPersons.call(obj);
        ControllerPersons controllerPersons = (ControllerPersons) callReturn.getController();
        controllerPersons.setSecondaryComponents();
        return new ScreenData(callReturn.getParent(), controllerPersons);
    }
    
    private void setSecondaryComponents() throws IOException {
        setAddressComponent(AddressComponentController.call());
        setTelephoneComponent(TelephoneComponentController.call());
    }
    
    private void setAddressComponent(ScreenObject addressComponent) {
        this.addressComponent = addressComponent;
    }

    private void setTelephoneComponent(ScreenObject telephoneComponent) {
        this.telephoneComponent = telephoneComponent;
    }
    
    public abstract void setDynamicSecondary();
}
