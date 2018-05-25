/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.Employee;
import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.Classes.Persons.LegalPerson;
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.Classes.Persons.Supplier;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class PersonDAO {

    private AddressDAO addressDAO;
    private EmployeeDAO employeeDAO;
    private LegalPersonDAO legalPersonDAO;
    private JuridicalPersonDAO juridicalDAO;
    private SupplierDAO supplierDAO;

    public PersonDAO(AddressDAO addressDAO, EmployeeDAO employeeDAO, LegalPersonDAO legalPersonDAO, JuridicalPersonDAO juridicalDAO, SupplierDAO supplierDAO) {
        this.addressDAO = addressDAO;
        this.employeeDAO = employeeDAO;
        this.legalPersonDAO = legalPersonDAO;
        this.juridicalDAO = juridicalDAO;
        this.supplierDAO = supplierDAO;
    }
    public void insertPerson(Person p) throws Exception {
        basicInsertPerson(p);
        if (p instanceof LegalPerson) {
            legalPersonDAO.insertLegalPerson((LegalPerson) p);
            if (p instanceof Employee) {
                employeeDAO.insertEmployee((Employee) p);
            }
        } else if (p instanceof JuridicalPerson) {
            juridicalDAO.insertJuridicalPerson((JuridicalPerson) p);
            if (p instanceof Supplier) {
                supplierDAO.insertSupplier((Supplier) p);
            }
        }
        addressDAO.insertAddress(p);
    }
    public Person getPerson(){
        Person p = null;
        return p;
    }

    protected abstract void basicInsertPerson(Person p) throws Exception;

    protected abstract void basicUpdatePerson(Person p) throws Exception;

    protected abstract void basicRemovePerson(Person p) throws Exception;

    protected abstract Person basicGetPerson(String id) throws Exception;

    public abstract ArrayList<Person> getAllPersons() throws Exception;

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void setLegalPersonDAO(LegalPersonDAO legalPersonDAO) {
        this.legalPersonDAO = legalPersonDAO;
    }

    public void setJuridicalDAO(JuridicalPersonDAO juridicalDAO) {
        this.juridicalDAO = juridicalDAO;
    }

    public void setSupplierDAO(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

}
