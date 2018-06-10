/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import Classes.Transactions.Record;
import Classes.Transactions.Service;

/**
 *
 * @author gabriel
 */
public abstract class TransactionServiceDAO {

    public abstract void insertServiceTransaction(Record record, Service service);
}
