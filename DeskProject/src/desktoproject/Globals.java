/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject;

import Classes.Persons.Employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public RemoteMethods getChannel() {
        return rmiChannel;
    }

    private Globals() throws RemoteException {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry("localhost", RemoteMethods.RMI_PORT);
            this.rmiChannel = (RemoteMethods) rmiRegistry.lookup(RemoteMethods.RMI_BD);
        } catch (NotBoundException | RemoteException ex) {
            throw new RemoteException();
        }
    }

    public static Globals getInstance() throws RemoteException {
        return INSTANCE==null?INSTANCE= new Globals():INSTANCE;
    }
}
