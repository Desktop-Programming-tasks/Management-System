/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import Exceptions.OperationNotAllowed;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public interface PersonRemote extends Remote {
    public Person queryPerson(String id) throws RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Person> queryAllPersons() throws RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Supplier> queryAllSuppliers() throws RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Employee> queryAllEmployees() throws RemoteException, NoResultsException, DatabaseErrorException;
    public Employee queryEmployeeByLogin(String login) throws RemoteException, DatabaseErrorException, NoResultsException;
    
    public void insertPerson(Person person) throws RemoteException, DuplicatedEntryException, DuplicatedLoginException, DatabaseErrorException;
    public void updatePerson(Person person) throws RemoteException, DuplicatedLoginException, NoResultsException, DatabaseErrorException;
    public void unpromotePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException;
    public void deletePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException;
    
    public void promoteEmployee(Employee employee) throws RemoteException, DatabaseErrorException, DuplicatedLoginException; 
    public void promoteSupplier(Supplier supplier) throws RemoteException, DatabaseErrorException, DuplicatedEntryException;
}
