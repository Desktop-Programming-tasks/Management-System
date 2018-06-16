/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ecsanchesjr
 */
public class ObservableThread extends Thread {
    private final Aggregator aggregator;
    private final ServerSocket server;
    private Socket clientConnection;

    public ObservableThread(String name, Aggregator aggregator, ServerSocket server) {
        super(name);
        this.aggregator = aggregator;
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("ObservableServer ready to receive connections...");
        // colocar o objeto com todos os client sockets conectados
        // while recebendo conexões
        // método de notify
        while(true) {
            try {
                clientConnection = server.accept();
                aggregator.addClient(clientConnection);
                System.out.println("connected");
            } catch (IOException ex) {
                Logger.getLogger(ObservableThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
