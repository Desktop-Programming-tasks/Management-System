/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database;

import deskprojectserver.Database.DAO.Persons.LocationsDAO;
import deskprojectserver.Database.DAO.Persons.PersonDAO;
import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.Database.DAO.Transactions.ProductDAO;
import deskprojectserver.Database.DAO.Transactions.RegisterDAO;
import deskprojectserver.Database.DAO.Transactions.ServiceTypeDAO;
import deskprojectserver.mysql.Commons.MySqlBrandDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlLocationsDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlProductDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlRegisterDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlServiceTypeDAO;

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
    private final RegisterDAO registerDAO;

    private DAOBuilder() {
        pDAO = new MySqlPersonDAO();
        lDAO = new MySqlLocationsDAO();
        bDAO = new MySqlBrandDAO();
        productDAO = new MySqlProductDAO();
        serviceTypeDAO = new MySqlServiceTypeDAO();
        registerDAO = new MySqlRegisterDAO();
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

    public RegisterDAO getRegisterDAO() {
        return registerDAO;
    }
    

    public static DAOBuilder getInstance() {
        return DAOBuilderHolder.INSTANCE;
    }

    private static class DAOBuilderHolder {

        private static final DAOBuilder INSTANCE = new DAOBuilder();
    }
}
