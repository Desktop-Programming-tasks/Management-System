/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import deskprojectserver.Classes.Persons.Employee;
import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.Classes.Persons.LegalPerson;
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.Classes.Persons.Supplier;
import deskprojectserver.Classes.Transactions.Brand;
import deskprojectserver.Classes.Transactions.Product;
import deskprojectserver.Classes.Transactions.Record;
import deskprojectserver.Classes.Transactions.Service;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ecsanchesjr
 */
public interface ServerMethods extends Remote {
    public Employee queryEmployee() throws RemoteException;
    public JuridicalPerson queryJuridicalPerson() throws RemoteException;
    public LegalPerson queryLegalPerson() throws RemoteException;
    public Supplier querySupplier() throws RemoteException;
    
    public Brand queryBrand() throws RemoteException;
    public Product queryProduct() throws RemoteException;
    public Record queryRecord() throws RemoteException;
    public Service queryService() throws RemoteException;
    
    public void insertPerson(Person person) throws RemoteException;
    public void insertBrand(Brand brand) throws RemoteException;
    public void insertProduct(Product product) throws RemoteException;
    public void insertService(Service service) throws RemoteException;
    public void insertRecord(Record record) throws RemoteException;
    
    public void deletePerson(Person person) throws RemoteException;
    public void deleteBrand(Brand brand) throws RemoteException;
    public void deleteProduct(Product product) throws RemoteException;
    public void deleteService(Service service) throws RemoteException;
    public void deleteRecord(Record record) throws RemoteException;
}
