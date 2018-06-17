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
    public static void main(String[] args) throws DatabaseErrorException, NoResultsException {
        Record record = DAOBuilder.getInstance().getRegisterDAO().getRegister("796298712");
        Service service = (Service) record.getTransations().get(1);
        DAOBuilder.getInstance().getTransactionServiceDAO().updateService(service);

//        Employee emp = (Employee) DAOBuilder.getInstance().getPersonDAO().getPerson("000.000.000-03");
//        Person cust = DAOBuilder.getInstance().getPersonDAO().getPerson("000.000.000-00");
//        ServiceType st = DAOBuilder.getInstance().getServiceTypeDAO().getServiceType("teste");
//        System.out.println(st);
//        Product product = DAOBuilder.getInstance().getProductDAO().getProduct("02", true);
//        product.setQuantity(100);
//        Service service = new Service(new Date(), new Date(), ServiceStatus.REFUSED, emp, st);
//        ArrayList<Transaction> transactions = new ArrayList<>();
//        transactions.add(service);
//        transactions.add(product);
//        //transactions
//        Record record = new Record(emp, cust, transactions, RecordTypeConstants.SALE);
//        try {
//            DAOBuilder.getInstance().getRegisterDAO().insertFullRegisterAndTransactions(record);
//        } catch (DuplicatedEntryException ex) {
//            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (OutOfStockException ex) {
//            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
