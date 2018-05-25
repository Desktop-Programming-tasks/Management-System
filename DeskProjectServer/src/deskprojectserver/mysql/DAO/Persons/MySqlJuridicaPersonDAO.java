/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import deskprojectserver.Database.DAO.Persons.JuridicalPersonDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlJuridicaPersonDAO extends JuridicalPersonDAO {

    private static final String INSERT_SQL = "INSERT INTO `JuridicalPerson`(`Person_idPerson`) VALUES (?)";
    private static final String GET_ONE_SQL = "SELECT `Person_idPerson` "
            + "FROM `JuridicalPerson` WHERE Person_idPerson=?";

    @Override
    public void insertJuridicalPerson(JuridicalPerson jp) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, jp.getCNPJ());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (Exception e) {
            throw new DatabaseErrorException();
        }
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
    public JuridicalPerson getJuridicalPerson(String id) throws DatabaseErrorException, NoResultsException {
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            JuridicalPerson jp = null;
            while (qr.getRs().next()) {
                jp = new JuridicalPerson(null, null, null, null);
            }
            qr.closeAll();
            if(jp==null){
                throw new NoResultsException();
            }
            return jp;
        } catch (Exception e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public ArrayList<JuridicalPerson> getAllJuridicalPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
