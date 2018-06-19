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
import Classes.Transactions.Brand;
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
     * @throws Exceptions.DatabaseErrorException
     * @throws Exceptions.NoResultsException
     * @throws Exceptions.DuplicatedEntryException
     * @throws Exceptions.OutOfStockException
     */
    public static void main(String[] args) throws DatabaseErrorException, NoResultsException, DuplicatedEntryException, OutOfStockException {
        ArrayList<Brand> brands = DAOBuilder.getInstance().getBrandDAO().getLikeBrands("AM");
        for(Brand brand : brands){
            System.out.println(brand);
        }
    }

}
