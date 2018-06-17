/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import Observables.SocketData;
import RMI.RemoteLogin;
import RMI.RemoteMethods;
import deskprojectserver.Observable.Aggregator;
import deskprojectserver.Observable.ObservableThread;
import deskprojectserver.Observable.Observables.ObservablesHolder;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ecsanchesjr
 */
public class StartRemoteServer {
    private RemoteServer remoteServer;
    private LoginServer loginServer;
    
    private RemoteLogin loginChannel;
    private RemoteMethods methodsChannel;
    
    private Registry rmiRegistry;
    
    private ServerSocket observableServer;
    private Aggregator aggregator;
    private ObservableThread observableThread;
    
    public static void main(String[] args) {
        StartRemoteServer hostServer = new StartRemoteServer();
        
        hostServer.openLoginServer();
        hostServer.openRemoteServer();
        hostServer.openObservableServer();
    }
    
    private void openLoginServer() {
        loginServer = new LoginServer();
        
        try {
            loginChannel = (RemoteLogin) UnicastRemoteObject.exportObject(loginServer, 0);
            rmiRegistry = LocateRegistry.createRegistry(RemoteLogin.RMI_PORT);
            rmiRegistry.bind(RemoteLogin.RMI_LOGIN, loginChannel);
            
            System.out.println("LoginServer ready to receive connections...");
        } catch(RemoteException | AlreadyBoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private void openRemoteServer() {
        remoteServer = new RemoteServer();
        try {

            methodsChannel = (RemoteMethods) UnicastRemoteObject.exportObject(remoteServer, 0);
            rmiRegistry = LocateRegistry.createRegistry(RemoteMethods.RMI_PORT);
            rmiRegistry.bind(RemoteMethods.RMI_BD, methodsChannel);

            System.out.println("RemoteServer ready to receive connections...");
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(RemoteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void openObservableServer() {
        setUpObservers();
        try {
            observableServer = new ServerSocket(SocketData.SERVER_PORT);
            observableThread = new ObservableThread(SocketData.THREAD_NAME, aggregator, observableServer);
            observableThread.start();
        } catch (IOException ex) {
            Logger.getLogger(StartRemoteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void setUpObservers(){
        System.out.println("===Observable system===");
        System.out.println("Creating aggregator");
        
        aggregator = new Aggregator();
        ObservablesHolder.subscribeToAll(aggregator);
        
        System.out.println("Observer aggregator ready....");
    }
}
