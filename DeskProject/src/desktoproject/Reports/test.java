/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Model.DAO.Persons.PersonDAO;
import java.rmi.RemoteException;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author viniciusmn
 */
public class test {
    public static void main(String[] args) throws RemoteException, NoResultsException, DatabaseErrorException, JRException {
        new ClientReport(PersonDAO.queryAllPersons()).generateReport();
    }
}
