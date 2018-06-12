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
public abstract class ControllerPromotion extends ControllerPersons {
    private boolean promote;
    
    @Override
    public ScreenData call() throws IOException {
        ScreenData callReturn = super.call();
        ControllerPromotion controller = (ControllerPromotion) callReturn.getController();
        return new ScreenData(callReturn.getParent(), controller);
    }
    
    @Override
    public ScreenData call(Object obj) throws IOException {
        ScreenData callReturn = super.call(obj);
        ControllerPromotion controller = (ControllerPromotion) callReturn.getController();
        return new ScreenData(callReturn.getParent(), controller);
    }

    public boolean isPromote() {
        return promote;
    }

    public void setPromote(boolean promote) {
        this.promote = promote;
    }
}
