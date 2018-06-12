/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Utils.Pairs.ScreenData;
import java.io.IOException;

/**
 *
 * @author ecsanchesjr
 */
public abstract class ControllerEdit extends Controller {
    private boolean edit;

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    @Override
    public ScreenData call() throws IOException {
        ScreenData callReturn = super.call();
        ControllerEdit controller = (ControllerEdit) callReturn.getController();
        controller.setUpComponents();
        return new ScreenData(callReturn.getParent(), controller);
    }
    
    public ScreenData call(Object obj) throws IOException {
        ScreenData callReturn = super.call();
        ControllerEdit controller = (ControllerEdit) callReturn.getController();
        controller.setScreenObject(obj);
        controller.setEdit(true);
        controller.setUpComponents();
        controller.fillScreen();
        return new ScreenData(callReturn.getParent(), controller);
    }
    
    public abstract void setUpComponents();
    
    public abstract void fillScreen();
    
    public abstract void setScreenObject(Object obj);
}
