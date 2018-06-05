/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class ServiceTypeDAO {
    public abstract void insertServiceType(ServiceType st) throws DatabaseErrorException,DuplicatedEntryException;
    public abstract void updateServiceType(ServiceType st) throws DatabaseErrorException,DuplicatedEntryException;
    public abstract ServiceType getServiceType(String id) throws DatabaseErrorException,NoResultsException;
    public abstract ArrayList<ServiceType> getAllServiceTypes() throws DatabaseErrorException;
    public abstract ArrayList<ServiceType> getLikeServiceTypes(String id) throws DatabaseErrorException;
    
}
