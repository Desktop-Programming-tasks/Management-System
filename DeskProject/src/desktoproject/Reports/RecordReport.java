/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Classes.Transactions.Transaction;
import desktoproject.Utils.Misc;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author viniciusmn
 */
public class RecordReport extends Report{

    private final Record record;
    
    public RecordReport(Record record) {
        super("RecordReport.jasper");
        this.record = record;
    }

    @Override
    protected JasperPrint generatePrint() throws JRException {
        Map params = new HashMap();
        params.put("ID",String.valueOf(record.getId()));
        params.put("TYPE",record.getType()==1?("Venda"):("Compra"));
        params.put("DATE",Misc.dateToString(record.getRegisterDate()));
        params.put("VALUE",Misc.changeToComma(String.valueOf(record.getTotalprice())));
        params.put("EMPLOYEE_NAME",record.getAssignedEmployee().getName());
        params.put("EMPLOYEE_DOCUMENT",record.getAssignedEmployee().getDocumentId());
        params.put("CLIENT_NAME",record.getCustomer().getName());
        params.put("CLIENT_DOCUMENT",record.getCustomer().getDocumentId());
        
        return (JasperFillManager.fillReport(report, params, new ReportDataSource()));
    }
    
    private class ReportDataSource implements JRDataSource{

        private int counter = -1;
        
        @Override
        public boolean next() throws JRException {
            if (counter < record.getTransations().size() - 1) {
                counter++;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object getFieldValue(JRField jrf) throws JRException {
            switch (jrf.getName()) {
                case "price": {
                    return Misc.changeToComma(String.valueOf(record.getTransations().get(counter).getPrice()));
                }
                case "name": {
                    return record.getTransations().get(counter).getName();
                }
                case "quantity": {
                    Transaction t = record.getTransations().get(counter);
                    return (t instanceof Product)?String.valueOf(t.getQuantity()):"---";
                }
                default:
                    return "";
            }
        }
    
    }
}
