/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class BrandDAO {
    
    public abstract void insertBrand(Brand brand) throws DatabaseErrorException, DuplicatedEntryException;
    
    protected abstract Brand getBrand(String id, boolean justActive) throws DatabaseErrorException, NoResultsException;
    
    public abstract void updateBrand(Brand brand) throws DatabaseErrorException, NoResultsException;
    
    public void removeBrand(Brand brand) throws DatabaseErrorException, NoResultsException {
        brand.setActive(false);
        updateBrand(brand);
    }
    
    public abstract void checkIfExists(Brand brand) throws DatabaseErrorException, NoResultsException;
    
    public abstract ArrayList<Brand> getAllBrands() throws DatabaseErrorException;
}
