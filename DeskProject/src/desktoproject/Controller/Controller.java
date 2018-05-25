/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller;

import desktoproject.Utils.Pairs.ScreenData;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author ecsanchesjr
 */
public abstract class Controller {
    private static String path;
    
    public static void setPath(String path) {
        Controller.path = path;
    }
    
    public static ScreenData call() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller.class.getResource(path));
        Parent parent = loader.load();
        Controller controller = loader.getController();
        controller.setUpComponents();
        return new ScreenData(parent, controller);
    } 
    
    public abstract void setUpComponents();
}
