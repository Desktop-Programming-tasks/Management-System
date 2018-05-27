/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.Database.DAO.Transactions.ProductDAO;
import deskprojectserver.mysql.Commons.MySqlBrandDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlLocationsDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;
import deskprojectserver.mysql.DAO.Transactions.MySqlProductDAO;

/**
 *
 * @author gabriel
 */
public class DAOBuilder {
    private final PersonDAO pDAO;
    private final LocationsDAO lDAO;
    private final BrandDAO bDAO;
    private final ProductDAO productDAO;
    
    private DAOBuilder() {
        pDAO= new MySqlPersonDAO();
        lDAO= new MySqlLocationsDAO();
        bDAO= new MySqlBrandDAO();
        productDAO= new MySqlProductDAO();
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
        
    public static DAOBuilder getInstance() {
        return DAOBuilderHolder.INSTANCE;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }
    
    
    private static class DAOBuilderHolder {

        private static final DAOBuilder INSTANCE = new DAOBuilder();
    }
}
