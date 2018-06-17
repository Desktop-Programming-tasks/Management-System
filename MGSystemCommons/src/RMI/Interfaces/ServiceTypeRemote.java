/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Classes.Transactions.ServiceType;
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
public interface ServiceTypeRemote extends Remote {

    public void insertServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException;

    public void updateServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException;

    public void inactivateServiceType(ServiceType st) throws RemoteException, DatabaseErrorException;

    public ServiceType queryServiceType(String id) throws RemoteException, DatabaseErrorException, NoResultsException;

    public ArrayList<ServiceType> queryAllServiceTypes() throws RemoteException, DatabaseErrorException;
}
