/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject;

import Classes.Persons.Employee;
import RMI.ServerMethods;
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
    private ServerMethods rmiChannel;
    private static Globals INSTANCE;

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
            Registry rmiRegistry = LocateRegistry.getRegistry("localhost", 1099);
            this.rmiChannel = (ServerMethods) rmiRegistry.lookup("RMI_BD_Server");
        } catch (NotBoundException | RemoteException ex) {
//            Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CAGOU A MERDA DO RMI "+ex.getMessage());
        }
    }

    public static Globals getInstance() {
        return INSTANCE==null?INSTANCE= new Globals():INSTANCE;
    }
}
