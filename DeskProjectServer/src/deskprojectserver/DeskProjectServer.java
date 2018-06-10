/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Persons.Person;
import Classes.Transactions.Product;
import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import deskprojectserver.Database.DAOBuilder;
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
    public static void main(String[] args) {
        try {
            Person p = DAOBuilder.getInstance().getPersonDAO().getPerson("844.125.000-98");
            DAOBuilder.getInstance().getPersonDAO().inactivatePerson(p);
            System.out.println(p);
        } catch (DatabaseErrorException ex) {
            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoResultsException ex) {
            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
