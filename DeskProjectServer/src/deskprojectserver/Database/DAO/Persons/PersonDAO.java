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
import deskprojectserver.Enums.EmployeeType;
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
    public Person getPerson(String id) throws Exception{
        Person p=basicGetPerson(id);
        p.setAddress(addressDAO.getAddress(p));
        
        LegalPerson lp = legalPersonDAO.getLegalPerson(id);
        if(lp!=null){
            Employee emp = employeeDAO.getEmployee(id);
            if(emp!=null){
                return new Employee(emp.getLogin(),
                        emp.getPassword(),emp.getEmployeeType(),lp.getRG(), p.getName(),
                        p.getAddress(), p.getTelephones(), p.getId());
            }
            return new LegalPerson(lp.getRG(), p.getName(), p.getAddress(),p.getTelephones(),
                    p.getId());
        }
        JuridicalPerson jp = juridicalDAO.getJuridicalPerson(id);
        if(jp != null){
            return new JuridicalPerson(p.getName(),p.getAddress(), p.getTelephones(), p.getId());
        
        }
        return p;
    }

    protected abstract void basicInsertPerson(Person p) throws Exception;

    protected abstract void basicUpdatePerson(Person p) throws Exception;

    protected abstract void basicRemovePerson(Person p) throws Exception;

    protected abstract Person basicGetPerson(String id) throws Exception;

    public abstract ArrayList<Person> getAllPersons() throws Exception;
}
