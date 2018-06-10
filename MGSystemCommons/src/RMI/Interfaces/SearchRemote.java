/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Classes.Transactions.Product;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public interface SearchRemote extends Remote { 
    public ArrayList<Employee> searchEmployees(String id) throws RemoteException, DatabaseErrorException;
    public ArrayList<Supplier> searchSupliers(String id) throws RemoteException, DatabaseErrorException;
    public ArrayList<Product> searchProduct(String id) throws RemoteException, DatabaseErrorException;
    public ArrayList<Person> searchPersons(String id) throws RemoteException, DatabaseErrorException;
    public ArrayList<ServiceType> searchServiceTypes(String id) throws RemoteException, DatabaseErrorException;
}
