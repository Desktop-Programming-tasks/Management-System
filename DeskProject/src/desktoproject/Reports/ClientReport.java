/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Persons.Person;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ecsanchesjr
 */
public class ClientReport extends Report {

    private final ArrayList<Person> persons;

    public ClientReport(String file, ArrayList<Person> persons) {
        this.file = file;
        this.persons = persons;
    }

    @Override
    protected JasperPrint generatePrint() throws JRException {
        return (JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(persons)));
    }

}
