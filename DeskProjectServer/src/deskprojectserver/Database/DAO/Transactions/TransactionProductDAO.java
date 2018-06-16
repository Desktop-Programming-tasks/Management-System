/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Constants.RecordTypeConstants;
import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.OutOfStockException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class TransactionProductDAO {

    protected ProductDAO productDao;

    public TransactionProductDAO(ProductDAO productDao) {
        this.productDao = productDao;
    }

    public abstract void insertProductTransaction(Record record, Product product)
            throws DuplicatedEntryException, DatabaseErrorException;

    public abstract void checkIfAvailable(Product product)
            throws DatabaseErrorException, OutOfStockException;

    public abstract void updateStock(Product product, int quantity) throws DatabaseErrorException;

    public abstract ArrayList<Product> getAllRecordProducts(Record record) throws DatabaseErrorException;

    public void completeInsertProductTransaction(Record record, Product product) throws DatabaseErrorException, OutOfStockException, DuplicatedEntryException {
        if (record.getType() == RecordTypeConstants.SALE) {
            checkIfAvailable(product);
        }
        insertProductTransaction(record, product);
    }

}
