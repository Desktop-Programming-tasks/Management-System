/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Model.DAO.Transactions.RecordDAO;
import java.rmi.RemoteException;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author gabriel
 */
public class testeTransa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NoResultsException, DatabaseErrorException, JRException {
        new TransactionsReport(RecordDAO.queryAllRecords()).generatePrint();
    }
    
}
