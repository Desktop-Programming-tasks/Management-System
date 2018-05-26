/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import Classes.Persons.Supplier;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.util.ArrayList;


/**
 *
 * @author gabriel
 */
public abstract class SupplierDAO {
    public abstract void insertSupplier(Supplier supplier) throws DatabaseErrorException,DuplicatedEntryException;
    public abstract void updateSupplier(Supplier supplier) throws DatabaseErrorException,NoResultsException;
    public abstract void removeSupplier(Supplier supplier) throws DatabaseErrorException,NoResultsException;
    public abstract Supplier getSupplier(String id) throws DatabaseErrorException,NoResultsException;
    public abstract ArrayList<Supplier> getAllSuppliers() throws DatabaseErrorException;
}
