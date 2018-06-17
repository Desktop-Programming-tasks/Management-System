/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database;

import Classes.Transactions.Record;
import Classes.Transactions.Service;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import deskprojectserver.Database.DAO.Persons.AutenticationDAO;
import deskprojectserver.Database.DAO.Persons.LocationsDAO;
import deskprojectserver.Database.DAO.Persons.PersonDAO;
import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.Database.DAO.Transactions.ProductDAO;
import deskprojectserver.Database.DAO.Transactions.RecordDAO;
import deskprojectserver.Database.DAO.Transactions.ServiceTypeDAO;
import deskprojectserver.Database.DAO.Transactions.TransactionServiceDAO;
import deskprojectserver.mysql.Commons.MySqlBrandDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlAutenticationDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlLocationsDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlProductDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlRecordDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlServiceTransactionDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlServiceTypeDAO;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class DAOBuilder {

    private final PersonDAO pDAO;
    private final LocationsDAO lDAO;
    private final BrandDAO bDAO;
    private final ProductDAO productDAO;
    private final ServiceTypeDAO serviceTypeDAO;
    private final AutenticationDAO autenticationDAO;
    private final RecordDAO recordDAO;
    private final TransactionServiceDAO transactionServiceDAO;

    private DAOBuilder() {
        pDAO = new MySqlPersonDAO();
        lDAO = new MySqlLocationsDAO();
        bDAO = new MySqlBrandDAO();
        productDAO = new MySqlProductDAO();
        serviceTypeDAO = new MySqlServiceTypeDAO();
        autenticationDAO = new MySqlAutenticationDAO();
        recordDAO = new MySqlRecordDAO();
        transactionServiceDAO = new ServiceTransactionHolder(new MySqlServiceTransactionDAO());
    }

    public PersonDAO getPersonDAO() {
        return pDAO;
    }

    public LocationsDAO getLocationsDAO() {
        return lDAO;
    }

    public BrandDAO getBrandDAO() {
        return bDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public ServiceTypeDAO getServiceTypeDAO() {
        return serviceTypeDAO;
    }

    public AutenticationDAO getAutenticationDAO() {
        return autenticationDAO;
    }

    public RecordDAO getRecordDAO() {
        return recordDAO;
    }

    public static DAOBuilder getInstance() {
        return DAOBuilderHolder.INSTANCE;
    }

    private static class DAOBuilderHolder {

        private static final DAOBuilder INSTANCE = new DAOBuilder();
    }

    public TransactionServiceDAO getTransactionServiceDAO() {
        return transactionServiceDAO;
    }

    private class ServiceTransactionHolder extends TransactionServiceDAO {

        TransactionServiceDAO tServiceDao;

        public ServiceTransactionHolder(TransactionServiceDAO tServiceDao) {
            this.tServiceDao = tServiceDao;
        }

        @Override
        public void insertServiceTransaction(Record record, Service service) throws DatabaseErrorException {
            throw new UnsupportedOperationException("Operation not Allowed, invalid access"); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ArrayList<Service> getAllRecordServices(Record record) throws DatabaseErrorException {
            throw new UnsupportedOperationException("Operation not Allowed, invalid access"); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void updateService(Service service) throws DatabaseErrorException, NoResultsException {
            tServiceDao.updateService(service);
        }

        @Override
        public ArrayList<Service> getAllServices() throws DatabaseErrorException {
            return tServiceDao.getAllServices();
        }
    }

}
