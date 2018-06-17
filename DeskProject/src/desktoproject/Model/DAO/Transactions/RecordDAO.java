/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Transactions;

import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import desktoproject.Globals;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public abstract class RecordDAO {
    public static void insertRecord(Record record) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, OutOfStockException {
        Globals.getInstance().getChannel().insertRecord(record);
    }
    
    public static Record queryRecord(String id) throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryRecord(id);
    }
    
    public static ArrayList<Record> queryAllRecords() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllRecords();
    }
    
    public static ArrayList<Record> queryRecodsSale() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryRecordsSale();
    }
    
    public static ArrayList<Record> queryRecodsBuy() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryRecordsBuy();
    }
    
    public static ArrayList<Record> searchRecords(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchRecords(id);
    }
    
    public static ArrayList<Record> searchRecordsSale(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchRecordsSale(id);
    }
    
    public static ArrayList<Record> searchRecordsBuy(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchRecordsBuy(id);
    }
}
