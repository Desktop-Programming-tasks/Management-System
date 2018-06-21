/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Reports;

import Classes.Persons.Person;
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
public class ClientReport extends Report {

    private final ArrayList<Person> persons;

    public ClientReport(ArrayList<Person> persons) {
        super("ClientsReport.jrxml");
        this.persons = persons;
    }

    @Override
    protected JasperPrint generatePrint() throws JRException {
        return (JasperFillManager.fillReport(report, null, new ClientDataSource()));
    }

    private class ClientDataSource implements JRDataSource {

        private int counter = -1;

        @Override
        public boolean next() throws JRException {
            if (counter < persons.size() - 1) {
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
                    return persons.get(counter).getName();
                }
                case "documentId": {
                    return persons.get(counter).getDocumentId();
                }
                case "phone": {
                    return persons.get(counter).getTelephones().get(0);
                }
                default:
                    return "";
            }
        }
    }
}
