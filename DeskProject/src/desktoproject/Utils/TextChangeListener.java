/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils;

import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author gabriel
 */
public abstract class TextChangeListener implements ChangeListener {

    Timer timer;

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if(timer!=null){
            timer.cancel();
        }
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runLogic(observable, oldValue, newValue);
                timer.cancel();
            }
        }, 300);
    }

    public abstract void runLogic(ObservableValue observable, Object oldValue, Object newValue);
}
