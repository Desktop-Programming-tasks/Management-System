/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Transactions;

import Classes.Transactions.Brand;
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
public abstract class BrandDAO {
    public static ArrayList<Brand> queryAllBrands() throws RemoteException, DatabaseErrorException, NoResultsException {
        return Globals.getInstance().getChannel().queryAllBrands();
    }
    
    public static void insertBrand(Brand brand) throws RemoteException, DuplicatedEntryException, DatabaseErrorException {
        Globals.getInstance().getChannel().insertBrand(brand);
    }
    
    public static void deleteBrand(Brand brand) throws RemoteException, NoResultsException, DatabaseErrorException {
        Globals.getInstance().getChannel().deleteBrand(brand);
    }
}
