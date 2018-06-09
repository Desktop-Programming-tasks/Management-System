/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
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
//        final Color startColorText = Color.web("#1976D2");
//        final Color endColorText = Color.web("#FAFAFA");
//        final Color startColorBack = endColorText;
//        final Color endColorBack = startColorText;
        final Color startColor = Color.WHITE;
        final Color endColor = Color.web("#1976D2");
        
        final ObjectProperty<Color> color = new SimpleObjectProperty<>(startColor);
        final StringBinding cssHighSpec = Bindings.createStringBinding(() -> String.format("-fx-border-color: rgb(%d, %d, %d);",
                (int) (256 * color.get().getRed()),
                (int) (256 * color.get().getGreen()),
                (int) (256 * color.get().getBlue())), color);
//        
//        final ObjectProperty<Color> colorBack = new SimpleObjectProperty<>(startColorBack);
//        final StringBinding cssBackSpec = Bindings.createStringBinding(() -> String.format("-fx-background-color: rgb(%d, %d, %d);",
//                (int) (256 * colorBack.get().getRed()),
//                (int) (256 * colorBack.get().getGreen()),
//                (int) (256 * colorBack.get().getBlue())), colorBack);
        
        final ObjectProperty<Integer> shadow = new SimpleObjectProperty<>(startRadius);
        final StringBinding cssShadowSpec = Bindings.createStringBinding(() -> String.format("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4) , %d, 0, 0, 0);", 
                shadow.get()), 
                shadow);
        
//        node.styleProperty().bind(cssShadowSpec);
        node.styleProperty().bind(Bindings.concat(cssHighSpec,cssShadowSpec));
//        node.styleProperty().bind(cssBackSpec);
//        node.styleProperty().bind(cssTextSpec);
//        node.styleProperty().bind(Bindings.concat(cssBackSpec,cssShadowSpec,cssTextSpec));
        
        final Timeline shadowAnim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shadow, startRadius)),
                new KeyFrame(Duration.millis(100), new KeyValue(shadow, endRadius))
        );
        
        final Timeline highAnim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(color, startColor)),
                new KeyFrame(Duration.millis(100), new KeyValue(color, endColor))
        );
        
//        final Timeline textAnim = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(colorText, startColorText)),
//                new KeyFrame(Duration.millis(100), new KeyValue(colorText, endColorText))
//        );
//        
//        final Timeline backAnim = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(colorBack, startColorBack)),
//                new KeyFrame(Duration.millis(100), new KeyValue(colorBack, endColorBack))
//        );
        
//        final ParallelTransition focusAnim = new ParallelTransition(textAnim,backAnim);

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
        
        node.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                highAnim.setRate(1);
                highAnim.play();
            }else{
                highAnim.setRate(-1);
                highAnim.play();
            }
        });
    }
}
