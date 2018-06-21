/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Persons.Person;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Model.DAO.Persons.PersonDAO;
import java.rmi.RemoteException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author ecsanchesjr
 */
public class NewMain {

    public static void main(String[] args) throws JRException, RemoteException, NoResultsException, DatabaseErrorException {
        ArrayList<Person> persons = PersonDAO.queryAllPersons();
        new ClientReport(persons).generateReport();
    }
    
}
