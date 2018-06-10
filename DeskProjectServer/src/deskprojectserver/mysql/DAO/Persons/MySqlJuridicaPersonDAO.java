/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Persons.JuridicalPerson;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Persons.JuridicalPersonDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MySqlJuridicaPersonDAO extends JuridicalPersonDAO {

    private static final String INSERT_SQL = "INSERT INTO `JuridicalPerson`(`Person_idPerson`) VALUES (?)";
    private static final String GET_ONE_SQL = "SELECT `Person_idPerson` "
            + "FROM `JuridicalPerson` WHERE Person_idPerson=?";
    private static final String REMOVE_SQL = "DELETE FROM `JuridicalPerson` WHERE "
            + "Person_idPerson=?";

    @Override
    public void insertJuridicalPerson(JuridicalPerson jp) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, jp.getCNPJ());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateJuridicalPerson(JuridicalPerson jp) throws DatabaseErrorException, NoResultsException {
        getJuridicalPerson(jp.getDocumentId());
    }

    @Override
    public void removeJuridcalPerson(JuridicalPerson jp) throws DatabaseErrorException {
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_SQL, jp.getCNPJ());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public JuridicalPerson getJuridicalPerson(String id) throws DatabaseErrorException, NoResultsException {
        JuridicalPerson jp = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);

            while (qr.getResultSet().next()) {
                jp = new JuridicalPerson(null, null, null, null);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        if (jp == null) {
            throw new NoResultsException();
        }
        return jp;

    }

    @Override
    public ArrayList<JuridicalPerson> getAllJuridicalPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
