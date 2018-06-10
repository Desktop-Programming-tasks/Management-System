/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import Exceptions.DatabaseErrorException;
import RMI.RemoteMethods;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ecsanchesjr
 */
public class Client {
    public static void main(String[] args) throws DatabaseErrorException {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry("localhost", RemoteMethods.RMI_PORT);
            RemoteMethods rmiChannel = (RemoteMethods) rmiRegistry.lookup(RemoteMethods.RMI_BD);
            System.out.println(rmiChannel.searchEmployees("batata"));
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
