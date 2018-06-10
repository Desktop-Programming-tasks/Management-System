/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import RMI.RemoteLogin;
import RMI.ServerMethods;
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
    private ServerMethods methodsChannel;
    
    private Registry rmiRegistry;
    
    public static void main(String[] args) {
        StartRemoteServer hostServer = new StartRemoteServer();
        
        hostServer.openLoginServer();
        hostServer.openRemoteServer();
    }
    
    private void openLoginServer() {
        loginServer = new LoginServer();
        
        try {
            loginChannel = (RemoteLogin) UnicastRemoteObject.exportObject(loginServer, 0);
            rmiRegistry = LocateRegistry.createRegistry(1099);
            rmiRegistry.bind("RMI_LOGIN_Server", loginChannel);
            
            System.out.println("LoginServer already to receive connections...");
        } catch(RemoteException | AlreadyBoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private void openRemoteServer() {
        remoteServer = new RemoteServer();
        try {
            methodsChannel = (ServerMethods) UnicastRemoteObject.exportObject(remoteServer, 0);
            rmiRegistry = LocateRegistry.createRegistry(1100);
            rmiRegistry.bind("RMI_BD_Server", methodsChannel);

            System.out.println("RemoteServer already to receive connections...");
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(RemoteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
