/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Transactions.Product;
import desktoproject.Utils.Misc;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ecsanchesjr
 */
public class StockReport extends Report {

    private final ArrayList<Product> products;

    public StockReport(ArrayList<Product> products) {
        super("StockReport.jasper");
        this.products = products;
    }

    @Override
    protected JasperPrint generatePrint() throws JRException {
        return (JasperFillManager.fillReport(report, null, new StockDataSource()));
    }

    public class StockDataSource implements JRDataSource {

        private int counter;

        public StockDataSource() {
            this.counter = -1;
        }

        @Override
        public boolean next() throws JRException {
            if (counter < products.size() - 1) {
                counter++;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object getFieldValue(JRField jrf) throws JRException {
            switch (jrf.getName()) {
                case "name": {
                    return products.get(counter).getName();
                }
                case "barCode": {
                    return products.get(counter).getBarCode();
                }
                case "quantityInStock": {
                    return String.valueOf(products.get(counter).getQuantityInStock());
                }
                case "brand": {
                    return products.get(counter).getBrand().getName();
                }
                case "price": {
                    return Misc.changeToComma(String.valueOf(products.get(counter).getPrice()));
                }
                default:
                    return "";
            }
        }
    }
}
