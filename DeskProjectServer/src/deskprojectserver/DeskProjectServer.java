/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Enums.RecordType;
import Classes.Persons.Employee;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.ServiceType;
import Classes.Transactions.Transaction;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import deskprojectserver.Database.DAOBuilder;
import Classes.Constants.RecordTypeConstants;
import java.util.ArrayList;
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
        Product product = DAOBuilder.getInstance().getProductDAO().getProduct("01", true);
        Product product2 = DAOBuilder.getInstance().getProductDAO().getProduct("02", true);
        product.setQuantity(2);
        product2.setQuantity(1);
        System.out.println(product);
        System.out.println(product2);

        Employee emp = (Employee) DAOBuilder.getInstance().getPersonDAO().getPerson("000.000.000-03");
        System.out.println(emp);

        Person p = DAOBuilder.getInstance().getPersonDAO().getPerson("0000-03");
        System.out.println(p);

        ArrayList<Transaction> trasactions = new ArrayList<>();
        trasactions.add(product);
        trasactions.add(product2);
        
        Record record = new Record(emp, p, trasactions, RecordTypeConstants.SALE);
        System.out.println(record);
        try {
            DAOBuilder.getInstance().getRegisterDAO().insertFullRegisterAndTransactions(record);
        } catch (OutOfStockException ex) {
           ex.printStackTrace();
        }
        
    }

}
