/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import Classes.Persons.Person;
import Classes.Transactions.Brand;
import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Service;
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
public interface ServerMethods extends Remote {
    public Person queryPerson(String id) throws RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Person> queryAllPersons() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertPerson(Person person) throws RemoteException, DuplicatedEntryException, DuplicatedLoginException, DatabaseErrorException;
    public void deletePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException, OperationNotAllowed;
    
    public Brand queryBrand() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Brand> queryAllBrands() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertBrand(Brand brand) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void deleteBrand(Brand brand) throws RemoteException, NoResultsException, DatabaseErrorException;
    
    public Product queryProduct() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Product> queryAllProducts() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertProduct(Product product) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void deleteProduct(Product product) throws RemoteException, NoResultsException, DatabaseErrorException;

    public Record queryRecord() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Record> queryAllRecords() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertRecord(Record record) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void deleteRecord(Record record) throws RemoteException, NoResultsException, DatabaseErrorException;

    public Service queryService() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Service> querryAllServices() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertService(Service service) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void deleteService(Service service) throws RemoteException, NoResultsException, DatabaseErrorException;
    
    public ArrayList<String> queryStates() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<String> queryCities(String state) throws RemoteException, NoResultsException, DatabaseErrorException;
            
}
