/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Classes.Transactions.Brand;
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
public interface BrandRemote extends Remote {

    public ArrayList<Brand> queryAllBrands() throws RemoteException, NoResultsException, DatabaseErrorException;

    public void insertBrand(Brand brand) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;

    public void updateBrand(Brand brand) throws RemoteException, DatabaseErrorException, NoResultsException;

    public void deleteBrand(Brand brand) throws RemoteException, NoResultsException, DatabaseErrorException;
}
