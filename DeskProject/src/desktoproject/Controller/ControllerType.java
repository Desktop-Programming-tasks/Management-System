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
public abstract class ControllerType extends Controller {
    public static ScreenData call(String enumType) throws IOException {
        ScreenData callReturn = ControllerType.call();
        ControllerType controller = (ControllerType) callReturn.getController();
        controller.setType(enumType);
        controller.setUpComponents();
        return new ScreenData(callReturn.getParent(), controller);
    }
    
    public abstract void setType(String enumType);

    public abstract void setUpComponents();
}
