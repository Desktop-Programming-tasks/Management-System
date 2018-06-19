/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

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
import Exceptions.OutOfStockException;
import Exceptions.UnavailableBrandException;
import RMI.RemoteMethods;
import deskprojectserver.Database.DAOBuilder;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ecsanchesjr
 */
public class RemoteServer implements RemoteMethods {

    @Override
    public ArrayList<Brand> queryAllBrands() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getBrandDAO().getAllBrands();
    }

    @Override
    public void insertBrand(Brand brand) throws RemoteException, DuplicatedEntryException, DatabaseErrorException {
        DAOBuilder.getInstance().getBrandDAO().insertBrand(brand);
    }

    @Override
    public void updateBrand(Brand brand) throws RemoteException, DatabaseErrorException, NoResultsException {
        DAOBuilder.getInstance().getBrandDAO().updateBrand(brand);
    }

    @Override
    public void deleteBrand(Brand brand) throws RemoteException, NoResultsException, DatabaseErrorException {
        DAOBuilder.getInstance().getBrandDAO().removeBrand(brand);
    }

    @Override
    public ArrayList<String> queryStates() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getLocationsDAO().getStates();
    }

    @Override
    public ArrayList<String> queryCities(String state) throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getLocationsDAO().getCities(state);
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
    public ArrayList<Supplier> queryAllSuppliers() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getAllSuppliers();
    }

    @Override
    public ArrayList<Employee> queryAllEmployees() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getAllEmployees();
    }

    @Override
    public Employee queryEmployeeByLogin(String login) throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getEmployeeWithLogin(login);
    }

    @Override
    public void insertPerson(Person person) throws RemoteException, DuplicatedEntryException, DuplicatedLoginException, DatabaseErrorException {
        DAOBuilder.getInstance().getPersonDAO().insertPerson(person);
    }

    @Override
    public void updatePerson(Person person) throws RemoteException, DuplicatedLoginException, NoResultsException, DatabaseErrorException {
        DAOBuilder.getInstance().getPersonDAO().updatePerson(person);
    }

    @Override
    public void deletePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException {
        DAOBuilder.getInstance().getPersonDAO().removePerson(person);
    }

    @Override
    public void unpromotePerson(Person person) throws RemoteException, DatabaseErrorException, NoResultsException, OperationNotAllowed {
        DAOBuilder.getInstance().getPersonDAO().unPromotePerson(person);
    }

    @Override
    public void promotePerson(Person person) throws RemoteException, DatabaseErrorException, DuplicatedLoginException, DuplicatedEntryException {
        DAOBuilder.getInstance().getPersonDAO().promotePerson(person);
    }

    @Override
    public Product queryProduct(String id) throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getProductDAO().getProduct(id, true);
    }

    @Override
    public ArrayList<Product> queryAllProducts() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getProductDAO().getAllProducts();
    }

    @Override
    public void insertProduct(Product product) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, UnavailableBrandException {
        DAOBuilder.getInstance().getProductDAO().insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws RemoteException, UnavailableBrandException, DatabaseErrorException, NoResultsException, DuplicatedEntryException {
        DAOBuilder.getInstance().getProductDAO().updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) throws RemoteException, NoResultsException, DatabaseErrorException {
        try {
            DAOBuilder.getInstance().getProductDAO().inactivateProduct(product);
        } catch (UnavailableBrandException | DuplicatedEntryException ex) {
            //
        }
    }

    @Override
    public Record queryRecord(String id) throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getRecordDAO().getRegister(id);
    }

    @Override
    public void insertRecord(Record record) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, OutOfStockException {
        DAOBuilder.getInstance().getRecordDAO().insertFullRegisterAndTransactions(record);
    }

    @Override
    public ArrayList<Record> queryAllRecords() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getRecordDAO().getAllRecords();
    }

    @Override
    public ArrayList<Record> queryRecordsSale() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getRecordDAO().getAllSaleRecords();
    }

    @Override
    public ArrayList<Record> queryRecordsBuy() throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getRecordDAO().getAllPurchaseRecords();
    }

    @Override
    public ArrayList<Employee> searchEmployees(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getLikeEmployees(id);
    }

    @Override
    public ArrayList<Supplier> searchSupliers(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getLikeSuppliers(id);
    }

    @Override
    public ArrayList<Product> searchProduct(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getProductDAO().getLikeProducts(id);
    }

    @Override
    public ArrayList<Person> searchPersons(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getPersonDAO().getLikePersons(id);
    }

    @Override
    public ArrayList<ServiceType> searchServiceTypes(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getServiceTypeDAO().getLikeServiceTypes(id);
    }

    @Override
    public ArrayList<Record> searchRecords(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getRecordDAO().getLikeRecords(id);
    }

    @Override
    public ArrayList<Record> searchRecordsSale(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getRecordDAO().getLikeSaleRecords(id);
    }

    @Override
    public ArrayList<Record> searchRecordsBuy(String id) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getRecordDAO().getLikePurchaseRecords(id);
    }

    @Override
    public Service queryService() throws RemoteException, NoResultsException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertService(Service service) throws RemoteException, DuplicatedEntryException, DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateService(Service service) throws RemoteException, DatabaseErrorException, NoResultsException {
        DAOBuilder.getInstance().getTransactionServiceDAO().updateService(service);
    }
    
    @Override
    public ArrayList<Service> queryServices(String employeeName, int status, String serviceTypeName) throws RemoteException, NoResultsException, DatabaseErrorException {
        return DAOBuilder.getInstance().getTransactionServiceDAO().getAllServices(employeeName, status, serviceTypeName);
    }

    @Override
    public void insertServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException {
        DAOBuilder.getInstance().getServiceTypeDAO().insertServiceType(st);
    }

    @Override
    public void updateServiceType(ServiceType st) throws RemoteException, DatabaseErrorException, DuplicatedEntryException {
        DAOBuilder.getInstance().getServiceTypeDAO().updateServiceType(st);
    }

    @Override
    public void inactivateServiceType(ServiceType st) throws RemoteException, DatabaseErrorException {
        DAOBuilder.getInstance().getServiceTypeDAO().inactivateServiceType(st);
    }

    @Override
    public ServiceType queryServiceType(String id) throws RemoteException, DatabaseErrorException, NoResultsException {
        return DAOBuilder.getInstance().getServiceTypeDAO().getServiceType(id);
    }

    @Override
    public ArrayList<ServiceType> queryAllServiceTypes() throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getServiceTypeDAO().getAllServiceTypes();
    }
}
