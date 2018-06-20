/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Transactions.Product;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ecsanchesjr
 */
public class StockReport extends Report {

    private final ArrayList<Product> products;

    public StockReport(String file, ArrayList<Product> products) {
        this.file = file;
        this.products = products;
    }
    
    @Override
    protected JasperPrint generatePrint() throws JRException {
        return (JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(products)));
    }
    
}
