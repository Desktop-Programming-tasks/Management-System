/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author viniciusmn
 */
public abstract class Animation {
    
    public static void bindAnimation(Node node) {
        final Color startColor = Color.web("#b3b3b3");
        final Color endColor = Color.web("#1976D2");
        
        final ObjectProperty<Color> color = new SimpleObjectProperty<>(startColor);
        final StringBinding cssColorSpec = Bindings.createStringBinding(() -> String.format("-fx-border-color: rgb(%d, %d, %d);",
                (int) (256 * color.get().getRed()),
                (int) (256 * color.get().getGreen()),
                (int) (256 * color.get().getBlue())), color);
        
        node.styleProperty().bind(cssColorSpec);
        
        final Timeline colorAnim = new Timeline(
            new KeyFrame(Duration.ZERO,new KeyValue(color,startColor)),
            new KeyFrame(Duration.millis(250),new KeyValue(color,endColor)));
        
        
        node.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                colorAnim.setRate(1);
                colorAnim.play();
            }else{
                colorAnim.setRate(-1);
                colorAnim.play();
            }
        });
    }

    public static void bindShadowAnimation(Node node){
        final Integer startRadius = 5;
        final Integer endRadius = 10;
        
        final ObjectProperty<Integer> shadow = new SimpleObjectProperty<>(startRadius);
        final StringBinding cssShadowSpec = Bindings.createStringBinding(() -> String.format("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4) , %d, 0, 0, 0);", shadow.get()), shadow);
        
        node.styleProperty().bind(cssShadowSpec);
        
        final Timeline shadowAnim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shadow, startRadius)),
                new KeyFrame(Duration.millis(100), new KeyValue(shadow, endRadius))
        );
//        
        node.setOnMouseEntered((event) -> {
                shadowAnim.setRate(1);
                shadowAnim.play();
        });
        
        node.setOnMouseExited((event) -> {
                shadowAnim.setRate(-1);
                shadowAnim.play();
        });
        
        node.setOnMousePressed((event) -> {
            shadowAnim.setRate(-1);
            shadowAnim.play();
        });
        
        node.setOnMouseReleased((event) -> {
            shadowAnim.setRate(1);
            shadowAnim.play();
        });
    }
}
