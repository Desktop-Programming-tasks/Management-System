/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Transactions.Record;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author gabriel
 */
public class TransactionsReport extends Report {

    private final ArrayList<Record> records;

    public TransactionsReport(ArrayList<Record> records) {
        super("TransactionsReport.jrxml");
        this.records = records;
    }

    @Override
    protected JasperPrint generatePrint() throws JRException {
        return (JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(records)));
    }

}
