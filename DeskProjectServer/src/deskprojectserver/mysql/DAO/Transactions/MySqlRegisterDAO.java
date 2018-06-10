/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Transactions.Record;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.RegisterDAO;
import deskprojectserver.Database.DAO.Transactions.TransactionProductDAO;
import deskprojectserver.Database.DAO.Transactions.TransactionServiceDAO;
import deskprojectserver.Utils.FormatUtils;
import deskprojectserver.Utils.QueryExecuter;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MySqlRegisterDAO extends RegisterDAO {

    private static final String ID = "idRegistry";
    private static final String DATE = "dateRegistry";
    private static final String VALUE_RECORD = "valueRegistry";
    private static final String TYPE = "typeRegistry";
    private static final String CUSTOMER = "Person_customer";
    private static final String EMPLOYEE = "Person_employee";
    private static final String INSERT_SQL = "INSERT INTO `Registry`(`idRegistry`, `dateRegistry`, "
            + "`valueRegistry`, `typeRegistry`, "
            + "`Person_customer`, `Person_employee`) "
            + "VALUES (?,?,?,?,?,?)";
    private static final String GET_ONE_SQL = "SELECT `idRegistry`, `dateRegistry`, "
            + "`valueRegistry`, `typeRegistry`, `Person_customer`, "
            + "`Person_employee` FROM `Registry` WHERE idRegistry=?";

    private static final String GET_ALL_ID_SQL = "SELECT `idRegistry`  FROM `Registry`";
    private static final String GET_ALL_ID_LIKE_SQL = "SELECT `idRegistry`  FROM `Registry`"
            + " WHERE Person_customer LIKE ?";

    public MySqlRegisterDAO() {
        super(new MySqlProductTransactionDAO(), null);
    }

    @Override
    protected void basicInsertRecord(Record record) throws DuplicatedEntryException, DatabaseErrorException {
        record.setRegisterDate(Calendar.getInstance().getTime());
        record.setId(record.hashCode());
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    record.getId(), record.getRegisterDate(), record.getTotalprice(), record.getType(), record.getCustomer().getDocumentId(),
                    record.getAssignedEmployee().getDocumentId());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    protected Record basicGetRecord(String id) throws NoResultsException, DatabaseErrorException {
        Record record = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getResultSet().next()) {
                Employee emp = (Employee) new MySqlPersonDAO().
                        getPerson(qr.getResultSet().getString(EMPLOYEE));
                Person customer = new MySqlPersonDAO().getPerson(
                        qr.getResultSet().getString(CUSTOMER));

                record = new Record(qr.getResultSet().getInt(ID),
                        emp,
                        qr.getResultSet().getDate(DATE),
                        qr.getResultSet().getFloat(VALUE_RECORD),
                        customer, new ArrayList<>(),
                        qr.getResultSet().getInt(TYPE));

            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
        if (record == null) {
            throw new NoResultsException();
        }
        return record;
    }

    @Override
    public ArrayList<Record> getAllRecords() throws DatabaseErrorException {
        return getGenericRecords(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_ALL_ID_SQL);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }

    @Override
    public ArrayList<Record> getLikeRecords(String id) throws DatabaseErrorException {
        ArrayList<Record> records = getAllRecords();
        records.removeIf((Record t) -> {
            if (t.getCustomer().getName().contains(id)) {
                return false;
            }
            return true;
        });
        return records;
    }

    private ArrayList<Record> getGenericRecords(QueryExecuter executer) throws DatabaseErrorException {
        ArrayList<Record> records = new ArrayList<>();
        QueryResult qr = executer.execute();
        try {
            while (qr.getResultSet().next()) {
                try {
                    records.add(getRegister(qr.getResultSet().getString(ID)));
                } catch (NoResultsException ex) {
                    //
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseErrorException();
        }
        return records;
    }
}
