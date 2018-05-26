/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.Address;
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class AddressDAO {
    public abstract void insertAddress(Person person) throws DatabaseErrorException;
    public abstract void removeAddress(Person person) throws DatabaseErrorException,NoResultsException;
    public abstract void updateAddress(Person person) throws DatabaseErrorException;
    public abstract Address getAddress(Person person) throws DatabaseErrorException,NoResultsException ;
    public abstract ArrayList<Address> getAllAddresses() throws DatabaseErrorException;  
}
