/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.Database.DAO.Persons.JuridicalPersonDAO;
import deskprojectserver.Database.Database;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlJuridicaPersonDAO extends JuridicalPersonDAO{
    private static final String INSERT_SQL = "INSERT INTO `JuridicalPerson`(`Person_idPerson`) VALUES (?)";
    @Override
    public void insertJuridicalPerson(JuridicalPerson jp) throws SQLException, ClassNotFoundException {
        MySqlHandler.getInstance().getDb().execute(INSERT_SQL, jp.getCNPJ());
    }

    @Override
    public void updateJuridicalPerson(JuridicalPerson jp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeJuridcalPerson(JuridicalPerson jp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JuridicalPerson getJuridicalPerson(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<JuridicalPerson> getAllJuridicalPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
