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
        ArrayList<Service> services = DAOBuilder.getInstance().getTransactionServiceDAO().
                getAllServices("Fu", 1, "teste");
        for(Service sv : services){
            System.out.println(sv);
        }
    }

}
