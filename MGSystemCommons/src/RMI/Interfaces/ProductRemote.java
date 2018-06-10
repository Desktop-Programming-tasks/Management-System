/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.UnavailableBrandException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public interface ProductRemote extends Remote {
    public Product queryProduct(String id) throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Product> queryAllProducts() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertProduct(Product product) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, UnavailableBrandException;
    public void updateProduct(Product product) throws RemoteException, UnavailableBrandException, DatabaseErrorException, NoResultsException, DuplicatedEntryException;
    public void deleteProduct(Product product) throws RemoteException, NoResultsException, DatabaseErrorException;
}
