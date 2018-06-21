/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Transactions.Product;
import desktoproject.Controller.Query.StockController;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author ecsanchesjr
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JRException {
        // TODO code application logic here
        
        ArrayList<Product> products = new ArrayList<>();
        
        products.add(new Product("212313221"));
        products.add(new Product("212313221"));
        products.add(new Product("212313221"));
        products.add(new Product("212313221"));
        
        
        new StockReport("StockReport.jrxml", products).generateReport();
    }
    
}
