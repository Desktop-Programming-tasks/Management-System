/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Classes.Transactions.Brand;
import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Service;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import Exceptions.OperationNotAllowed;
import Exceptions.UnavailableBrandException;
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
    public void updatePerson(Person person) throws RemoteException, DuplicatedLoginException, NoResultsException, DatabaseErrorException;
    public void deletePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException, OperationNotAllowed;
    public void promoteEmployee(Employee employee) throws RemoteException, DatabaseErrorException, DuplicatedLoginException; 
    public void promoteSupplier(Supplier supplier) throws RemoteException, DatabaseErrorException, DuplicatedEntryException;
    
    public ArrayList<Brand> queryAllBrands() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertBrand(Brand brand) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void updateBrand(Brand brand) throws RemoteException, DatabaseErrorException, NoResultsException;
    public void deleteBrand(Brand brand) throws RemoteException, NoResultsException, DatabaseErrorException;
    
    public Product queryProduct(String id) throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Product> queryAllProducts() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertProduct(Product product) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, UnavailableBrandException;
    public void updateProduct(Product product) throws RemoteException, UnavailableBrandException, DatabaseErrorException, NoResultsException, DuplicatedEntryException;
    public void deleteProduct(Product product) throws RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Product> searchProduct(String id) throws RemoteException, DatabaseErrorException;

    public Record queryRecord() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Record> queryAllRecords() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertRecord(Record record) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void deleteRecord(Record record) throws RemoteException, NoResultsException, DatabaseErrorException;

    public Service queryService() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Service> querryAllServices() throws RemoteException, NoResultsException, DatabaseErrorException;
    public void insertService(Service service) throws RemoteException, DuplicatedEntryException, DatabaseErrorException;
    public void deleteService(Service service) throws RemoteException, NoResultsException, DatabaseErrorException;
    
    public void insertServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException;
    public void updateServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException;
    public ServiceType queryServiceType(String id) throws RemoteException, DatabaseErrorException, NoResultsException;
    public ArrayList<ServiceType> queryAllServiceTypes() throws RemoteException, DatabaseErrorException;
    public ArrayList<ServiceType> searchServiceTypes(String id) throws RemoteException, DatabaseErrorException;
    
    public ArrayList<String> queryStates() throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<String> queryCities(String state) throws RemoteException, NoResultsException, DatabaseErrorException;
            
    public ArrayList<Supplier> queryAllSuppliers() throws RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Employee> queryAllEmployees() throws RemoteException, NoResultsException, DatabaseErrorException;
    
    public ArrayList<Employee> searchEmployees(String id) throws RemoteException, DatabaseErrorException;
    public ArrayList<Supplier> searchSupliers(String id) throws RemoteException, DatabaseErrorException;
    public ArrayList<Person> searchPersons(String id) throws RemoteException, DatabaseErrorException;
}