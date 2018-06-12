/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public interface RecordRemote extends Remote {
    public Record queryRecord(String id) throws  RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Record> queryAllRecords() throws RemoteException, NoResultsException, DatabaseErrorException;
    public ArrayList<Record> queryLikeRecords(String clientName) throws RemoteException, DatabaseErrorException;
    public void insertRecord(Record record) throws RemoteException, DuplicatedEntryException, DatabaseErrorException, OutOfStockException;
    public void deleteRecord(Record record) throws RemoteException, NoResultsException, DatabaseErrorException;
}
