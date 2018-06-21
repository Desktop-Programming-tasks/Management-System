/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject;

import Classes.Persons.Employee;
import Observables.SocketData;
import RMI.RemoteMethods;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ecsanchesjr
 */
public class Globals {

    private Employee employee;
    private RemoteMethods rmiChannel;
    private static Globals INSTANCE;
    private ObserverThread observerServer;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public ObserverThread getObserverThread() {
        return observerServer;
    }
    
    public RemoteMethods getChannel() {
        return rmiChannel;
    }

    private Globals() throws RemoteException {
        try {
            observerServer = new ObserverThread(SocketData.THREAD_NAME);
            Registry rmiRegistry = LocateRegistry.getRegistry(SocketData.SERVER_HOST, RemoteMethods.RMI_PORT);
            this.rmiChannel = (RemoteMethods) rmiRegistry.lookup(RemoteMethods.RMI_BD);
        } catch (NotBoundException | RemoteException ex) {
            throw new RemoteException();
        }
    }

    public static Globals getInstance() throws RemoteException {
        return INSTANCE==null?INSTANCE= new Globals():INSTANCE;
    }
}
