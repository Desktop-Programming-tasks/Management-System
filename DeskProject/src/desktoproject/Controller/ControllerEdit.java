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
    
    private void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    public static ScreenData call(Object obj) throws IOException {
        ScreenData callReturn = ControllerEdit.call();
        ControllerEdit controllerEdit = (ControllerEdit) callReturn.getController();
        controllerEdit.setEdit(true);
        controllerEdit.setScreenObject(obj);
        controllerEdit.setUpComponents();
        return new ScreenData(callReturn.getParent(), controllerEdit);
    }
    
    public abstract void setScreenObject(Object obj);
    
    public abstract void setUpComponents();
    
    public abstract void fillScreen();
}
