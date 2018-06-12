/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Service;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author gabriel
 */
public abstract class RegisterDAO {

    TransactionProductDAO tProductDAO;
    TransactionServiceDAO tServiceDAO;

    public RegisterDAO(TransactionProductDAO tProductDAO, TransactionServiceDAO tServiceDAO) {
        this.tProductDAO = tProductDAO;
        this.tServiceDAO = tServiceDAO;
    }

    public void insertFullRegisterAndTransactions(Record record) throws DuplicatedEntryException, DatabaseErrorException, OutOfStockException {
        record.setRegisterDate(Calendar.getInstance().getTime());
        record.setId(record.hashCode());
        basicInsertRecord(record);
        for (Transaction t : record.getTransations()) {
            if (t instanceof Product) {
                tProductDAO.completeInsertProductTransaction(record, (Product) t);
            } else if (t instanceof Service) {
                tServiceDAO.insertServiceTransaction(record, (Service) t);
            }
        }
    }

    public Record getRegister(String id) throws NoResultsException, DatabaseErrorException {
        Record record = basicGetRecord(id);
        record.getTransations().addAll(tProductDAO.getAllRecordProducts(record));
        return record;
    }

    protected abstract Record basicGetRecord(String id) throws DatabaseErrorException, NoResultsException;

    protected abstract void basicInsertRecord(Record record) throws DuplicatedEntryException, DatabaseErrorException;

    public abstract ArrayList<Record> getAllRecords() throws DatabaseErrorException;

    public abstract ArrayList<Record> getLikeRecords(String id) throws DatabaseErrorException;

}
