/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public interface LocationRemote {

    public ArrayList<String> queryStates() throws RemoteException, NoResultsException, DatabaseErrorException;

    public ArrayList<String> queryCities(String state) throws RemoteException, NoResultsException, DatabaseErrorException;
}
