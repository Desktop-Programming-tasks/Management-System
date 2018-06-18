/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Observable;

import Classes.Enums.ObservableType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author viniciusmn
 */
public class Aggregator implements ServerObserver {

    private DataOutputStream output;
    private final ArrayList<Socket> clients;
    private final VerifyClientSockets verify;

    public Aggregator() {
        this.clients = new ArrayList<>();
        verify = new VerifyClientSockets(this);
        verify.start();
    }

    @Override
    public void update(ObservableType type) {

        System.out.println("Changed: " + type.name());
        try {
            clients.forEach(client -> {
                try {
                    output = new DataOutputStream(client.getOutputStream());
                    output.writeUTF(type.name());
                } catch (IOException ex) {
                }
            });
        } catch (ConcurrentModificationException ex) {
            System.out.println("Concurrent exception");
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause().toString());
        }
    }

    public ArrayList<Socket> getClients() {
        return clients;
    }

    public void addClient(Socket client) {
        clients.add(client);
    }

    private class VerifyClientSockets extends Thread {

        private final Aggregator aggregator;
        private DataOutputStream output;
        private DataInputStream response;

        public VerifyClientSockets(Aggregator aggregator) {
            this.aggregator = aggregator;
            this.setDaemon(true);
        }

        @Override
        public void run() {
            ArrayList<Socket> toRemove = new ArrayList<>();
            while (true) {
                toRemove.clear();
                try {
                    sleep(10 * 1000);
                    aggregator.clients.forEach(client -> {
                        try {
                            output = new DataOutputStream(client.getOutputStream());
                            output.writeUTF(".");
                            response = new DataInputStream(client.getInputStream());
                            client.setSoTimeout(500);
                            response.read();
                        } catch (IOException ex) {
                            toRemove.add(client);
                        }
                    });
                    toRemove.forEach(client -> aggregator.clients.remove(client));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Aggregator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}