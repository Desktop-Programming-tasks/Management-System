/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.Classes.Persons.LegalPerson;
import deskprojectserver.Classes.Persons.Person;

/**
 *
 * @author gabriel
 */
public  abstract class PersonDAO {

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

    public PersonDAO() {
        setDAOs();
    }
    public abstract void setDAOs();
    public void insertPerson(Person p) {

        if (p instanceof LegalPerson) {
            LegalPerson lp = (LegalPerson) p;
            legalPersonDAO.insertLegalPerson(lp);
        } else if (p instanceof JuridicalPerson) {

        }
        addressDAO.insertAddress(p, p.getAddress());
    }
}
