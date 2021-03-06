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
            try {
                Person aux = basicGetPerson(p.getDocumentId(), false);
                p.setId(aux.getId());
                if (aux.isActive()) {
                    throw new DuplicatedEntryException();
                } else {
                    p.setActive(true);
                    basicUpdatePerson(p);
                }
            } catch (NoResultsException ex) {
                //
            }
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
            p = basicGetPerson(id, true);
            p.setAddress(addressDAO.getAddress(p));
        } catch (NoResultsException a) {
            throw a;
        }
        try {
            LegalPerson lp = legalPersonDAO.getLegalPerson(id);
            try {
                Employee emp = employeeDAO.getEmployee(id);
                Employee empAux = new Employee(emp.getLogin(), emp.getPassword(),
                        emp.getEmployeeType(), lp.getRG(), p.getId(), p.getName(),
                        p.getAddress(), p.getTelephones(), p.getDocumentId());
                empAux.setActive(p.isActive());
                return empAux;
            } catch (NoResultsException b) {
                LegalPerson lpAux = new LegalPerson(lp.getRG(), p.getId(), p.getName(), p.getAddress(), p.getTelephones(),
                        p.getDocumentId());
                lpAux.setActive(p.isActive());
                return lpAux;
            }
        } catch (NoResultsException c) {
            try {
                juridicalDAO.getJuridicalPerson(id);
                try {
                    Supplier supplier = supplierDAO.getSupplier(id);
                    Supplier supplierAux = new Supplier(supplier.getAvaliableBrands(), p.getId(), p.getName(), p.getAddress(), p.getTelephones(), p.getDocumentId());
                    supplierAux.setActive(p.isActive());
                    return supplierAux;
                } catch (NoResultsException d) {
                    JuridicalPerson jpAux = new JuridicalPerson(p.getId(), p.getName(), p.getAddress(), p.getTelephones(), p.getDocumentId());
                    jpAux.setActive(p.isActive());
                    return jpAux;
                }
            } catch (NoResultsException f) {
                throw f;
            }
        }
    }

    public void removePerson(Person p) throws DatabaseErrorException, NoResultsException {
        if (p instanceof Employee) {
            employeeDAO.removeEmployee((Employee) p);
        } else if (p instanceof Supplier) {
            supplierDAO.removeSupplier((Supplier) p);
        }
        inactivatePerson(p);
    }

    private ArrayList<Employee> getEmployeesGeneric(EmployeeRequester requester) throws DatabaseErrorException {
        ArrayList<Employee> employees = requester.request();
        try {
            for (int i = 0; i < employees.size(); i++) {
                employees.set(i, (Employee) getPerson(employees.get(i).getDocumentId()));
            }
        } catch (DatabaseErrorException | NoResultsException e) {
            //
        }
        return employees;
    }

    public ArrayList<Employee> getAllEmployees() throws DatabaseErrorException {
        return getEmployeesGeneric(new EmployeeRequester() {
            @Override
            public ArrayList<Employee> request() throws DatabaseErrorException {
                return employeeDAO.getAllEmployees();
            }
        });
    }

    public ArrayList<Employee> getLikeEmployees(String id) throws DatabaseErrorException {
        return getEmployeesGeneric(new EmployeeRequester() {
            @Override
            public ArrayList<Employee> request() throws DatabaseErrorException {
                return employeeDAO.getLikeEmployees(id);
            }
        });
    }

    private ArrayList<Supplier> getSuppliersGeneric(SupplierRequester requester) throws DatabaseErrorException {
        ArrayList<Supplier> suppliers = requester.request();
        try {
            for (int i = 0; i < suppliers.size(); i++) {
                suppliers.set(i, (Supplier) getPerson(suppliers.get(i).getDocumentId()));
            }
        } catch (NoResultsException e) {
            //
        }
        return suppliers;
    }

    public ArrayList<Supplier> getAllSuppliers() throws DatabaseErrorException {
        return getSuppliersGeneric(new SupplierRequester() {
            @Override
            public ArrayList<Supplier> request() throws DatabaseErrorException {
                return supplierDAO.getAllSuppliers();
            }
        });
    }

    public ArrayList<Supplier> getLikeSuppliers(String id) throws DatabaseErrorException {
        return getSuppliersGeneric(new SupplierRequester() {
            @Override
            public ArrayList<Supplier> request() throws DatabaseErrorException {
                return supplierDAO.getLikeSuppliers(id);
            }
        });
    }

    public void updatePerson(Person p) throws DatabaseErrorException, NoResultsException, DuplicatedLoginException {
        basicUpdatePerson(p);
        addressDAO.updateAddress(p);
        if (p instanceof LegalPerson) {
            legalPersonDAO.updateLegalPerson((LegalPerson) p);
            if (p instanceof Employee) {
                try {
                    employeeDAO.updateEmployee((Employee) p);
                } catch (NoResultsException e) {
                    //Não é funcionário
                }
            }
        } else if (p instanceof JuridicalPerson) {
            juridicalDAO.updateJuridicalPerson((JuridicalPerson) p);
            if (p instanceof Supplier) {
                try {
                    supplierDAO.updateSupplier((Supplier) p);
                } catch (NoResultsException e) {
                    //Não é supplier
                }
            }
        }
    }

    private void inactivatePerson(Person p) throws DatabaseErrorException, NoResultsException {
        if (p instanceof LegalPerson) {
            legalPersonDAO.removeLegalPerson((LegalPerson) p);
        } else if (p instanceof JuridicalPerson) {
            juridicalDAO.removeJuridcalPerson((JuridicalPerson) p);
        }
        addressDAO.removeAddress(p);
        p.setActive(false);
        basicUpdatePerson(p);

    }

    public void promotePerson(Person p) throws DatabaseErrorException, DuplicatedLoginException, DuplicatedEntryException {
        if (p instanceof Employee) {
            legalToEmployee((Employee) (LegalPerson) p);
        } else if (p instanceof Supplier) {
            juridicalToSupplier((Supplier) p);
        }
    }

    public void unPromotePerson(Person p) throws OperationNotAllowed, DatabaseErrorException, NoResultsException {
        if (p instanceof Employee) {
            employeeDAO.removeEmployee((Employee) p);
        } else if (p instanceof Supplier) {
            supplierDAO.removeSupplier((Supplier) p);
        } else {
            throw new OperationNotAllowed();
        }
    }

    private void juridicalToSupplier(Supplier sp) throws DatabaseErrorException, DuplicatedEntryException {
        supplierDAO.insertSupplier(sp);
    }

    private void legalToEmployee(Employee emp) throws DatabaseErrorException, DuplicatedLoginException {
        try {
            employeeDAO.insertEmployee(emp);
        } catch (DuplicatedEntryException e) {
            throw new DuplicatedLoginException();
        }
    }

    protected abstract void basicInsertPerson(Person p) throws DatabaseErrorException, DuplicatedEntryException;

    protected abstract void basicUpdatePerson(Person p) throws DatabaseErrorException, NoResultsException;

    protected abstract Person basicGetPerson(String id, boolean justActive) throws DatabaseErrorException, NoResultsException;

    public abstract ArrayList<Person> getAllPersons() throws DatabaseErrorException;

    public abstract ArrayList<Person> getLikePersons(String id) throws DatabaseErrorException;

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    private abstract class EmployeeRequester {

        public abstract ArrayList<Employee> request() throws DatabaseErrorException;
    }

    private abstract class SupplierRequester {

        public abstract ArrayList<Supplier> request() throws DatabaseErrorException;
    }

    public Employee getEmployeeWithLogin(String login) throws DatabaseErrorException, NoResultsException {
        Employee newEmp = employeeDAO.getEmployeeByLogin(login);
        return (Employee) getPerson(newEmp.getCPF());
    }
}
