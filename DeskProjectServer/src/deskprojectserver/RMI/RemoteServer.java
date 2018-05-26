/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

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
import RMI.ServerMethods;
import deskprojectserver.Database.DAO.Persons.DAOBuilder;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ecsanchesjr
 */
public class RemoteServer implements ServerMethods {
    
    public static void main(String[] args) {
        RemoteServer rmi = new RemoteServer();
        
        try {
            ServerMethods rmiChannel = (ServerMethods) UnicastRemoteObject.exportObject(rmi, 0);
            Registry rmiRegistry = LocateRegistry.createRegistry(1099);
            rmiRegistry.bind("RMI_BD_Server", rmiChannel);
            
            System.out.println("Server ready to receive connections...");
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(RemoteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Person queryPerson(String id) throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getPerson(id);
    }

    @Override
    public ArrayList<Person> queryAllPersons() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getAllPersons();
    }

    @Override
    public void insertPerson(Person person) throws RemoteException, DuplicatedEntryException, DuplicatedLoginException, DatabaseErrorException {
        DAOBuilder.getInstance().getPersonDAO().insertPerson(person);
    }

    @Override
    public void deletePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException, OperationNotAllowed {
        DAOBuilder.getInstance().getPersonDAO().removePerson(person);
    }

    @Override
    public Brand queryBrand() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Brand> queryAllBrands() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertBrand(Brand brand) throws RemoteException, DuplicatedEntryException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBrand(Brand brand) throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product queryProduct() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> queryAllProducts() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertProduct(Product product) throws RemoteException, DuplicatedEntryException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct(Product product) throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Record queryRecord() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Record> queryAllRecords() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertRecord(Record record) throws RemoteException, DuplicatedEntryException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRecord(Record record) throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service queryService() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Service> querryAllServices() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertService(Service service) throws RemoteException, DuplicatedEntryException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteService(Service service) throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> queryStates() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getLocationsDAO().getStates();
    }

    @Override
    public ArrayList<String> queryCities(String state) throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getLocationsDAO().getCities(state);
    }
}
