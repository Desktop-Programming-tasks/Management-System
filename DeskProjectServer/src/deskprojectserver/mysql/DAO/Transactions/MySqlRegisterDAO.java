/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.RegisterDAO;
import deskprojectserver.Database.DAO.Transactions.TransactionProductDAO;
import deskprojectserver.Database.DAO.Transactions.TransactionServiceDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MySqlRegisterDAO extends RegisterDAO {

    private static final String INSERT_SQL = "INSERT INTO `Registry`(`idRegistry`, `dateRegistry`, "
            + "`valueRegistry`, `typeRegistry`, "
            + "`Person_customer`, `Person_employee`) "
            + "VALUES (?,?,?,?,?,?)";

    public MySqlRegisterDAO() {
        super(new MySqlProductTransactionDAO(), null);
    }

    @Override
    public Record getRegister(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void basicInsertRecord(Record record) throws DuplicatedEntryException, DatabaseErrorException {
        record.setRegisterDate(Calendar.getInstance().getTime());
        record.setId(record.hashCode());
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    record.getId(), record.getRegisterDate(), record.getTotalprice(), 1, record.getCustomer().getDocumentId(),
                    record.getAssignedEmployee().getDocumentId());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }

}
