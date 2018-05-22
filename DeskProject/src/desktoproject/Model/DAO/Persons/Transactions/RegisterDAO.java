/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons.Transactions;

import desktoproject.Model.Classes.Transactions.Register;
import desktoproject.Model.Classes.Transactions.Transaction;

/**
 *
 * @author gabriel
 */
public abstract class RegisterDAO {

    public abstract void insertRegister(Register register);

    public void createRegisterAndCommitTransations(Register register, TransactionDAO td) {
        for (Transaction el : register.getTransations()) {
            td.insertTransaction(el);
        }
        insertRegister(register);
    }

    public abstract Register getRegister(String id);
}
