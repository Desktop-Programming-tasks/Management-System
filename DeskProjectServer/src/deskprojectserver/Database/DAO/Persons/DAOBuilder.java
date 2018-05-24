/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;

/**
 *
 * @author gabriel
 */
public class DAOBuilder {
    private PersonDAO pDAO;
    
    private DAOBuilder() {
        pDAO= new MySqlPersonDAO();
    }

    public PersonDAO getPersonDAO() {
        return pDAO;
    }
    
    
    public static DAOBuilder getInstance() {
        return DAOBuilderHolder.INSTANCE;
    }
    
    private static class DAOBuilderHolder {

        private static final DAOBuilder INSTANCE = new DAOBuilder();
    }
}
