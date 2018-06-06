/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Transactions;

import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.UnavailableBrandException;
import desktoproject.Globals;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author viniciusmn
 */
public abstract class ProductDAO {
    public static Product queryProduct(String id) throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryProduct(id);
    }
    
    public static ArrayList<Product> queryAllProducts() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllProducts();
    }
    
    public static void insertProduct(Product product) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, UnavailableBrandException{
        Globals.getInstance().getChannel().insertProduct(product);
    }
    
    public static void deleteProduct(Product product) throws RemoteException, DatabaseErrorException, NoResultsException {
        Globals.getInstance().getChannel().deleteProduct(product);
    }
    
    public static void updateProduct(Product product) throws RemoteException, UnavailableBrandException, UnavailableBrandException, DatabaseErrorException, NoResultsException, DuplicatedEntryException {
        Globals.getInstance().getChannel().updateProduct(product);
    }
    
    public ArrayList<Product> searchProduct(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchProduct(id);
    }
}
