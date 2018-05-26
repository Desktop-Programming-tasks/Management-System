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
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public interface ServerMethods extends Remote {
    public Person queryPerson(String id) throws RemoteException;
    public ArrayList<Person> querryAllPersons() throws RemoteException;
    public void insertPerson(Person person) throws RemoteException;
    public void deletePerson(Person person) throws RemoteException;
    
    public Brand queryBrand() throws RemoteException;
    public ArrayList<Brand> queryAllBrands() throws RemoteException;
    public void insertBrand(Brand brand) throws RemoteException;
    public void deleteBrand(Brand brand) throws RemoteException;
    
    public Product queryProduct() throws RemoteException;
    public ArrayList<Product> queryAllProducts() throws RemoteException;
    public void insertProduct(Product product) throws RemoteException;
    public void deleteProduct(Product product) throws RemoteException;

    
    public Record queryRecord() throws RemoteException;
    public ArrayList<Record> queryAllRecords() throws RemoteException;
    public void insertRecord(Record record) throws RemoteException;
    public void deleteRecord(Record record) throws RemoteException;

    public Service queryService() throws RemoteException;
    public ArrayList<Service> querryAllServices() throws RemoteException;
    public void insertService(Service service) throws RemoteException;
    public void deleteService(Service service) throws RemoteException;
}
