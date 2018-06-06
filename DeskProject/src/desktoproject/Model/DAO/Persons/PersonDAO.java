/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import Exceptions.OperationNotAllowed;
import desktoproject.Globals;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public abstract class PersonDAO {
    public static void insertPerson(Person person) throws RemoteException, DuplicatedEntryException, DuplicatedLoginException, DatabaseErrorException {
        Globals.getInstance().getChannel().insertPerson(person);
    }
    
    public static Person queryPerson(String id) throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryPerson(id);
    }
    
    public static ArrayList<Person> queryAllPersons() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllPersons();
    }
    
    public static ArrayList<Employee> queryAllEmployees() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllEmployees();
    }
    
    public static ArrayList<Supplier> queryAllSuppliers() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllSuppliers();
    }
    
    public static void updatePerson(Person person) throws RemoteException, DuplicatedLoginException, NoResultsException, DatabaseErrorException {
        Globals.getInstance().getChannel().updatePerson(person);
    }
    
    public static void deletePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException, OperationNotAllowed {
        Globals.getInstance().getChannel().deletePerson(person);
    }
    
    public static ArrayList<Employee> searchEmployees(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchEmployees(id);
    }
    
    public static ArrayList<Supplier> searchSuppliers(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchSupliers(id);
    }
    
    public static ArrayList<Person> searchPersons(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchPersons(id);
    }
}
