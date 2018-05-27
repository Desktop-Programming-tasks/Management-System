/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import Classes.Persons.Employee;
import Classes.Persons.JuridicalPerson;
import Classes.Persons.LegalPerson;
import Classes.Persons.Person;
import Classes.Persons.Supplier;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import Exceptions.OperationNotAllowed;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class PersonDAO {

    private final AddressDAO addressDAO;
    private final EmployeeDAO employeeDAO;
    private final LegalPersonDAO legalPersonDAO;
    private final JuridicalPersonDAO juridicalDAO;
    private final SupplierDAO supplierDAO;

    public PersonDAO(AddressDAO addressDAO, EmployeeDAO employeeDAO, LegalPersonDAO legalPersonDAO, JuridicalPersonDAO juridicalDAO, SupplierDAO supplierDAO) {
        this.addressDAO = addressDAO;
        this.employeeDAO = employeeDAO;
        this.legalPersonDAO = legalPersonDAO;
        this.juridicalDAO = juridicalDAO;
        this.supplierDAO = supplierDAO;
    }

    public void insertPerson(Person p) throws DatabaseErrorException, DuplicatedEntryException, DuplicatedLoginException {
        try {
            basicInsertPerson(p);
        } catch (DuplicatedEntryException e) {
            throw e;
        }
        if (p instanceof LegalPerson) {
            try {
                legalPersonDAO.insertLegalPerson((LegalPerson) p);
            } catch (DuplicatedEntryException e) {
                throw e;
            }
            if (p instanceof Employee) {
                try {
                    employeeDAO.insertEmployee((Employee) p);
                } catch (DuplicatedEntryException e) {
                    throw new DuplicatedLoginException();
                }
            }
        } else if (p instanceof JuridicalPerson) {
            try {
                juridicalDAO.insertJuridicalPerson((JuridicalPerson) p);
                if (p instanceof Supplier) {
                    supplierDAO.insertSupplier((Supplier) p);
                }
            } catch (DatabaseErrorException | DuplicatedEntryException e) {
                throw e;
            }
        }
        try {
            addressDAO.insertAddress(p);
        } catch (DatabaseErrorException e) {
            throw e;
        }
    }

    public Person getPerson(String id) throws DatabaseErrorException, NoResultsException {
        Person p;
        try {
            p = basicGetPerson(id);
            p.setAddress(addressDAO.getAddress(p));
        } catch (NoResultsException a) {
            throw a;
        }
        try {
            LegalPerson lp = legalPersonDAO.getLegalPerson(id);
            try {
                Employee emp = employeeDAO.getEmployee(id);
                return new Employee(emp.getLogin(), emp.getPassword(), emp.getEmployeeType(), lp.getRG(), p.getName(), p.getAddress(), p.getTelephones(), p.getId());
            } catch (NoResultsException b) {
                return new LegalPerson(lp.getRG(), p.getName(), p.getAddress(), p.getTelephones(),
                        p.getId());
            }
        } catch (NoResultsException c) {
            try {
                juridicalDAO.getJuridicalPerson(id);
                try {
                    Supplier supplier = supplierDAO.getSupplier(id);
                    return new Supplier(supplier.getAvaliableBrands(), p.getName(), p.getAddress(), p.getTelephones(), p.getId());
                } catch (NoResultsException d) {
                    return new JuridicalPerson(p.getName(), p.getAddress(), p.getTelephones(), p.getId());
                }
            } catch (NoResultsException f) {
                throw f;
            }
        }
    }
    
    public void removePerson(Person p) throws DatabaseErrorException, NoResultsException, OperationNotAllowed {
        if (p instanceof Employee) {
            employeeDAO.removeEmployee((Employee) p);
        }
        else if(p instanceof Supplier){
            supplierDAO.removeSupplier((Supplier) p);
        }
        else{
            throw new OperationNotAllowed();
        }
    }

    protected abstract void basicInsertPerson(Person p) throws DatabaseErrorException, DuplicatedEntryException;

    protected abstract void basicUpdatePerson(Person p) throws DatabaseErrorException, NoResultsException;

    protected abstract Person basicGetPerson(String id) throws DatabaseErrorException, NoResultsException;

    public abstract ArrayList<Person> getAllPersons() throws DatabaseErrorException;
}
