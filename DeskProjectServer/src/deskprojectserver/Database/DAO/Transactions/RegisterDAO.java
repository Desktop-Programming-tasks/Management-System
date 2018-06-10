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
import Exceptions.OutOfStockException;
import java.util.ArrayList;

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
        basicInsertRecord(record);
        for (Transaction t : record.getTransations()) {
            if (t instanceof Product) {
                tProductDAO.completeInsertProductTransaction(record, (Product) t);
            } else if (t instanceof Service) {
                tServiceDAO.insertServiceTransaction(record, (Service) t);
            }
        }
    }

    public abstract Record getRegister(String id);

    protected abstract void basicInsertRecord(Record record) throws DuplicatedEntryException,DatabaseErrorException;

}
