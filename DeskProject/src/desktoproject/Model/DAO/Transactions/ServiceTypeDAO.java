/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Transactions;

import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import desktoproject.Globals;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public abstract class ServiceTypeDAO {
    public static void insertServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException {
        Globals.getInstance().getChannel().insertServiceType(st);
    }
    
    public static void updateServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException {
        Globals.getInstance().getChannel().updateServiceType(st);
    }
    
    public static ServiceType queryServiceType(String id) throws RemoteException, DatabaseErrorException, NoResultsException {
        return Globals.getInstance().getChannel().queryServiceType(id);
    }
    
    public ArrayList<ServiceType> queryAllServiceTypes() throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllServiceTypes();
    }
            
    public ArrayList<ServiceType> searchServiceTypes(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchServiceTypes(id);
    }
}
