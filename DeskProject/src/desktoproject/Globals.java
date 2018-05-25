/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject;

import desktoproject.Model.Classes.Persons.Employee;
import desktoproject.Model.ServerMethods;
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
public class Globals {

    private Employee employee;
    private ServerMethods rmiChannel;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public ServerMethods getChannel() {
        return rmiChannel;
    }

    private Globals() {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry("Localhost", 1099);
            this.rmiChannel = (ServerMethods) rmiRegistry.lookup("RMI_BD_Server");
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Globals getInstance() {
        return NewSingletonHolder.INSTANCE;
    }

    private static class NewSingletonHolder {

        private static final Globals INSTANCE = new Globals();
    }
}
