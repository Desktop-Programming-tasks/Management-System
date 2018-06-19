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
public class TextChangeListener implements ChangeListener {

    Timer timer;
    ChangeListenerRunnable runnable;

    public TextChangeListener(ChangeListenerRunnable runnable) {
        this.timer = new Timer();
        this.runnable = runnable;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        runnable.newValue = (String) newValue;
        timer.cancel();
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        }, 300);
    }
}
