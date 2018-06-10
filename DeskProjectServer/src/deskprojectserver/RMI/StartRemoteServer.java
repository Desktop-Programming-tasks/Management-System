/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import Observable.Aggregator;
import Observable.Observables.ObservablesHolder;
import RMI.RemoteLogin;
import RMI.RemoteMethods;
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
    
    public static void main(String[] args) {
        StartRemoteServer hostServer = new StartRemoteServer();
        
        hostServer.openLoginServer();
        hostServer.openRemoteServer();
        hostServer.setUpObservers();
    }
    
    private void openLoginServer() {
        loginServer = new LoginServer();
        
        try {
            loginChannel = (RemoteLogin) UnicastRemoteObject.exportObject(loginServer, 0);
            rmiRegistry = LocateRegistry.createRegistry(RemoteLogin.RMI_PORT);
            rmiRegistry.bind(RemoteLogin.RMI_LOGIN, loginChannel);
            
            System.out.println("LoginServer already to receive connections...");
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

            System.out.println("RemoteServer already to receive connections...");
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(RemoteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setUpObservers(){
        Aggregator ag = new Aggregator();
        ObservablesHolder.getBrand().addObserver(ag);
        ObservablesHolder.getEmployee().addObserver(ag);
    }
}
