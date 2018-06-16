/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils.Pairs;

import desktoproject.Controller.Interfaces.Controller;
import javafx.scene.Parent;

/**
 *
 * @author ecsanchesjr
 */
public class ScreenData {
    private Parent parent;
    private Controller controller;

    public ScreenData(Parent parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
    }
    
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
