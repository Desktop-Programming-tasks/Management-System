/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.mysql.Commons.MySqlBrandDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlLocationsDAO;
import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;

/**
 *
 * @author gabriel
 */
public class DAOBuilder {
    private final PersonDAO pDAO;
    private final LocationsDAO lDAO;
    private final BrandDAO bDAO;
    
    private DAOBuilder() {
        pDAO= new MySqlPersonDAO();
        lDAO= new MySqlLocationsDAO();
        bDAO= new MySqlBrandDAO();
    }

    public PersonDAO getPersonDAO() {
        return pDAO;
    }

    public LocationsDAO getlDAO() {
        return lDAO;
    }

    public BrandDAO getbDAO() {
        return bDAO;
    }
        
    public static DAOBuilder getInstance() {
        return DAOBuilderHolder.INSTANCE;
    }
    
    private static class DAOBuilderHolder {

        private static final DAOBuilder INSTANCE = new DAOBuilder();
    }
}
