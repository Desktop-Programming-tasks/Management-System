/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Brand;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class BrandDAO {
    public abstract void insertBrand(Brand brand) throws DatabaseErrorException,DuplicatedEntryException;
    public abstract void updateBrand(Brand brand) throws DatabaseErrorException,NoResultsException;
    public abstract void removeBrand(Brand brand) throws DatabaseErrorException,NoResultsException;
    public abstract Brand getBrand(String id) throws DatabaseErrorException,NoResultsException;
    public abstract ArrayList<Brand>  getAllBrands() throws DatabaseErrorException;    
}
    