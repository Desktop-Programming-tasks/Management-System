/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Transactions;

import deskprojectserver.Classes.Transactions.Record;
import deskprojectserver.Classes.Transactions.Transaction;


/**
 *
 * @author gabriel
 */
public abstract class RegisterDAO {

    public abstract void insertRegister(Record register);

    public void createRegisterAndCommitTransations(Record register, TransactionDAO td) {
        for (Transaction el : register.getTransations()) {
            td.insertTransaction(el);
        }
        insertRegister(register);
    }

    public abstract Record getRegister(String id);
}
