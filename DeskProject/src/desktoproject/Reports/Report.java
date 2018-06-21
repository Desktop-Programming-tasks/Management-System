/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ecsanchesjr
 */
public abstract class Report {
    private static final String PACKAGE_PATH = "src/desktoproject/Reports/Layouts/";
    protected String file;
    protected JasperReport report;
    private JasperPrint printReport;
    private JasperViewer viewerReport;
    
    protected abstract JasperPrint generatePrint() throws JRException;
    
    public void show() {
        viewerReport.setVisible(true);
    }
    
    public Report(String file) {
        this.file = file;
    }
    
    public void generateReport() throws JRException {
        JasperDesign design = JRXmlLoader.load(PACKAGE_PATH+file);
        report = JasperCompileManager.compileReport(design);
        printReport = generatePrint();
        viewerReport = new JasperViewer(printReport, false);
        show();
    }
}
