/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject;

import Classes.Enums.ObservableType;
import Observables.SocketData;
import desktoproject.Controller.Observable.Observables.ObservableServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    private boolean run;

    public ObserverThread(String name) {
        super(name);
        this.setDaemon(true);
        run = true;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
    
    @Override
    public void run() {
        try {
            connection = new Socket(SocketData.SERVER_HOST, SocketData.SERVER_PORT);
            notification = new DataInputStream(connection.getInputStream());
            
            while(run) {
                String serverAtt = notification.readUTF();
                if(serverAtt.equals(".")) {
                    DataOutputStream output = new DataOutputStream(connection.getOutputStream());
                    output.writeBoolean(true);
                } else {
                    System.out.println("Changed: "+serverAtt);
                    ObservableServer.trigger(ObservableType.valueOf(serverAtt));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ObserverThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
