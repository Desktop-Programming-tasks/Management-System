/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Transactions;

import Classes.Transactions.Service;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Globals;
import java.rmi.RemoteException;

/**
 *
 * @author ecsanchesjr
 */
public abstract class ServiceTransactionDAO {
    public static void updateService(Service service) throws RemoteException, DatabaseErrorException, NoResultsException {
        Globals.getInstance().getChannel().updateService(service);
    }
}
