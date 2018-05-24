/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils.Pairs;

import desktoproject.Controller.Enums.ScreenType;

/**
 *
 * @author noda
 */
public class ScreenObject {
    private ScreenType screen;
    private Object obj;

    public ScreenObject(ScreenType screen, Object obj) {
        this.screen = screen;
        this.obj = obj;
    }

    public ScreenType getScreen() {
        return screen;
    }

    public void setScreen(ScreenType screen) {
        this.screen = screen;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
