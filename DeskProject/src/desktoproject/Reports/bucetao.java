/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject;

import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import desktoproject.Model.DAO.Transactions.RecordDAO;
import desktoproject.Reports.TransactionsReport;
import java.rmi.RemoteException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author gabriel
 */
public class bucetao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NoResultsException, DatabaseErrorException, JRException {
        // TODO code application logic here
        ArrayList<Record> records = RecordDAO.queryAllRecords();
        for(Record r : records){
            System.out.println(r);
            TransactionsReport tr = new TransactionsReport(records);
            tr.generateReport();
        }
    }
    
}
