/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import deskprojectserver.Classes.Transactions.Transaction;
import java.util.ArrayList;
/**
 *
 * @author gabriel
 */
public abstract class TransactionDAO {
    public abstract void insertTransaction(Transaction mov);
    public abstract ArrayList<Transaction> getTransactions(String RegisterID);
}