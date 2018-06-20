/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

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
        
        new ClientReport("StockReport.jrxml", new ArrayList<>()).generateReport();
    }
    
}
