/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Transactions;

import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.OutOfStockException;
import desktoproject.Globals;
import java.rmi.RemoteException;

/**
 *
 * @author ecsanchesjr
 */
public abstract class RecordDAO {
    public static void insertRecord(Record record) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, OutOfStockException {
        Globals.getInstance().getChannel().insertRecord(record);
    }
}
