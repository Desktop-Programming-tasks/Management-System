/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class TransactionProductDAO {
    public abstract void insertProductTransaction(Record record,Product product) 
            throws DuplicatedEntryException,DatabaseErrorException;
}
