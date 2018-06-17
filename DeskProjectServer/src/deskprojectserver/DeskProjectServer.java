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
    public static void main(String[] args) throws DatabaseErrorException, NoResultsException, DuplicatedEntryException {
////        Record record = DAOBuilder.getInstance().getRegisterDAO().getRegister("796298712");
////        Service service = (Service) record.getTransations().get(1);
////        DAOBuilder.getInstance().getTransactionServiceDAO().updateService(service);
//
//        Employee emp = (Employee) DAOBuilder.getInstance().getPersonDAO().getPerson("000.000.000-03");
//        Person cust = DAOBuilder.getInstance().getPersonDAO().getPerson("000.000.000-00");
//        ServiceType st = DAOBuilder.getInstance().getServiceTypeDAO().getServiceType("teste");
//        System.out.println(st);
//        Service service = new Service(new Date(), new Date(), ServiceStatus.REFUSED, emp, st);
//        ArrayList<Transaction> transactions = new ArrayList<>();
//        service.setMessage("batata");
//        transactions.add(service);
//        //transactions
//        Record record = new Record(emp, cust, transactions, RecordTypeConstants.SALE);
//        try {
//            DAOBuilder.getInstance().getRegisterDAO().insertFullRegisterAndTransactions(record);
//        } catch (DuplicatedEntryException ex) {
//            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (OutOfStockException ex) {
//            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Record record = DAOBuilder.getInstance().getRegisterDAO().getRegister("2021780312");
        for(Transaction t: record.getTransations()){
            System.out.println(((Service)t).getId());
        }
        ServiceType st = new ServiceType("batata", 12310);
        DAOBuilder.getInstance().getServiceTypeDAO().insertServiceType(st);
        Service s = (Service) record.getTransations().get(0);
        s.setFinishDate(new Date());
        s.setMessage("batata");
        s.setStatus(ServiceStatus.REFUSED);
        s.setServiceType(new ServiceType(2, "dasdas", 3213210, true));
        DAOBuilder.getInstance().getTransactionServiceDAO().updateService(s);
    }

}
