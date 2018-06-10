/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Classes.Transactions.Service;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public interface ServiceRemote extends Remote {
    public Service queryService() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Service> querryAllServices() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertService(Service service) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void deleteService(Service service) throws RemoteException, NoResultsException, DatabaseErrorException;
}
