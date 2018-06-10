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
import deskprojectserver.mysql.DAO.Transactions.MySqlProductTransactionDAO;
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
    public static void main(String[] args) throws DatabaseErrorException, NoResultsException {
        Record rc = DAOBuilder.getInstance().getRegisterDAO().getRegister("56349152");
        System.out.println(rc);
        
    }

}
