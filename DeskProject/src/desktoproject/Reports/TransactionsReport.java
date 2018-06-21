/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Constants.RecordTypeConstants;
import Classes.Transactions.Record;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
        System.out.println(report == null);
        return (JasperFillManager.fillReport(report, null, new TransactionDataSource()));
    }

    private class TransactionDataSource implements JRDataSource {

        private int counter;

        public TransactionDataSource() {
            this.counter = -1;
        }

        @Override
        public boolean next() throws JRException {
            if (counter < records.size() - 1) {
                counter++;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object getFieldValue(JRField jrf) throws JRException {
            switch (jrf.getName()) {
                case "id":
                    return Integer.toString(records.get(counter).getId());
                case "date":
                    return records.get(counter).getRegisterDate().toString();
                case "employee":
                    return records.get(counter).getAssignedEmployee().getName();
                case "customer":
                    return records.get(counter).getCustomer().getName();
                case "price":
                    return Float.toString(records.get(counter).getTotalprice());
                case "type":
                    if (records.get(counter).getType() == RecordTypeConstants.PURCHASE) {
                        return "Compra";
                    } else {
                        return "Venda";
                    }
                default:
                    return "";

            }
        }

    }

}
