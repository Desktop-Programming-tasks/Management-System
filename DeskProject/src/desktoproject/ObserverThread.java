/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject;

import Classes.Enums.ObservableType;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ecsanchesjr
 */
public class ObserverThread extends Thread {
    private Socket connection;
    private DataInputStream notification;

    public ObserverThread(String name) {
        super(name);
        this.setDaemon(true);
    }
    
    @Override
    public void run() {
        try {
            connection = new Socket("localhost", 9000);
            notification = new DataInputStream(connection.getInputStream());
            
            while(true) {
                String serverAtt = notification.readUTF();
                ObservableServer.trigger(ObservableType.valueOf(serverAtt));
            }
        } catch (IOException ex) {
            Logger.getLogger(ObserverThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
