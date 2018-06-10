/*Çp6
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Persons.LegalPerson;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Persons.LegalPersonDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlLegalPersonDAO extends LegalPersonDAO {

    private static final String INSERT_SQL = "INSERT INTO `LegalPerson`(`rgLegalPerson`, `Person_idPerson`) "
            + "VALUES (?,?)";
    private static final String GET_ONE_SQL = "SELECT `rgLegalPerson`, `Person_idPerson` "
            + "FROM `LegalPerson` WHERE Person_idPerson=?";
    private static final String UPDATE_SQL = "UPDATE `LegalPerson` "
            + "SET `rgLegalPerson`=? WHERE Person_idPerson=?";
    private static final String REMOVE_SQL = "DELETE FROM `LegalPerson` WHERE Person_idPerson=?";
    private static final String RG = "rgLegalPerson";
    private static final String ID = "Person_idPerson";

    @Override
    public void insertLegalPerson(LegalPerson lp) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, lp.getRG(), lp.getCPF());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateLegalPerson(LegalPerson lp) throws NoResultsException, DatabaseErrorException {
        getLegalPerson(lp.getDocumentId());
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL, lp.getRG(), lp.getDocumentId());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void removeLegalPerson(LegalPerson lp) throws DatabaseErrorException {
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_SQL, lp.getCPF());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public LegalPerson getLegalPerson(String id) throws NoResultsException, DatabaseErrorException {
        LegalPerson lp = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getResultSet().next()) {
                lp = new LegalPerson(
                        qr.getResultSet().getString(RG), null, null, null, null);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        if (lp == null) {
            throw new NoResultsException();
        }
        return lp;
    }

    @Override
    public ArrayList<LegalPerson> getAllLegalPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
