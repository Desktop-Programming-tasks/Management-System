/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Interfaces;

import desktoproject.Utils.Pairs.ScreenData;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ecsanchesjr
 */
public abstract class Controller implements FXMLPaths {
    protected String path;
    
    public abstract void setPath();
    
    public ScreenData call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        setPath();
        loader.setLocation(Controller.class.getClassLoader().getResource(path));
        Parent parent = loader.load();
        Controller controller = loader.getController();
        controller.setAnchors(parent);
        return new ScreenData(parent, controller);
    }
    
    private void setAnchors(Parent p) {
        AnchorPane.setTopAnchor(p, 0.0);
        AnchorPane.setLeftAnchor(p, 0.0);
        AnchorPane.setBottomAnchor(p, 0.0);
        AnchorPane.setRightAnchor(p, 0.0);
    }
}
