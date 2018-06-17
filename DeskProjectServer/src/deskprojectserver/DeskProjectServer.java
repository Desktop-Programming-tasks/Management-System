/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Constants.RecordTypeConstants;
import Classes.Enums.ServiceStatus;
import Classes.Persons.Employee;
import Classes.Persons.Person;
import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Service;
import Classes.Transactions.ServiceType;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import deskprojectserver.Database.DAOBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class DeskProjectServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatabaseErrorException, NoResultsException, DuplicatedEntryException, OutOfStockException {
//        Employee emp = (Employee) DAOBuilder.getInstance().getPersonDAO().getPerson("000.000.000-03");
//        Person cust = DAOBuilder.getInstance().getPersonDAO().getPerson("000.000.000-00");
//        Product product = DAOBuilder.getInstance().getProductDAO().getProduct("00", true);
//        product.setQuantity(200);
//        ArrayList<Transaction> transactions = new ArrayList<>();
//        transactions.add(product);
//        Record record = new Record(emp, cust, transactions, RecordTypeConstants.PURCHASE);
//        DAOBuilder.getInstance().getRegisterDAO().insertFullRegisterAndTransactions(record);
        ArrayList<Record> records = DAOBuilder.getInstance().getRecordDAO().getAllSaleRecords();
        for(Record r : records){
            System.out.println(r);
        }
    }

}
