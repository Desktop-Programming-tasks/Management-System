/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

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
import desktoproject.Globals;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public abstract class PersonDAO {
    public static void insertPerson(Person person) throws RemoteException, DuplicatedEntryException, DuplicatedLoginException, DatabaseErrorException {
        Globals.getInstance().getChannel().insertPerson(person);
    }
    
    public static Person queryPerson(String id) throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryPerson(id);
    }
    
    public static ArrayList<Person> queryAllPersons() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllPersons();
    }
    
    public static ArrayList<Employee> queryAllEmployees() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllEmployees();
    }
    
    public static ArrayList<Supplier> queryAllSuppliers() throws RemoteException, NoResultsException, DatabaseErrorException {
        return Globals.getInstance().getChannel().queryAllSuppliers();
    }
    
    public static void updatePerson(Person person) throws RemoteException, DuplicatedLoginException, NoResultsException, DatabaseErrorException {
        Globals.getInstance().getChannel().updatePerson(person);
    }
    
    public static void deletePerson(Person person) throws RemoteException, NoResultsException, DatabaseErrorException, OperationNotAllowed {
        Globals.getInstance().getChannel().deletePerson(person);
    }
    
    public static ArrayList<Employee> searchEmployees(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchEmployees(id);
    }
    
    public static ArrayList<Supplier> searchSuppliers(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchSupliers(id);
    }
    
    public static ArrayList<Person> searchPersons(String id) throws RemoteException, DatabaseErrorException {
        return Globals.getInstance().getChannel().searchPersons(id);
    }
    
    public static void promoteEmployee(Employee employee) throws RemoteException, DatabaseErrorException, DuplicatedLoginException {
        Globals.getInstance().getChannel().promoteEmployee(employee);
    }
    
    public static void promoteSupplier(Supplier supplier) throws RemoteException, DatabaseErrorException, DuplicatedEntryException {
        Globals.getInstance().getChannel().promoteSupplier(supplier);
    }
    
    public static ArrayList<Person> queryAllLegalPersons() throws RemoteException, NoResultsException, DatabaseErrorException {
        ArrayList<Person> allPersons = Globals.getInstance().getChannel().queryAllPersons();
        ArrayList<Person> legalPersons = filterLegalPersons(allPersons);
        return legalPersons;
    }
    
    public static ArrayList<Person> queryAllJuridicalPersons() throws RemoteException, NoResultsException, DatabaseErrorException {
        ArrayList<Person> allPersons = Globals.getInstance().getChannel().queryAllPersons();
        ArrayList<Person> juridicalPersons = filterJuridicalPersons(allPersons);
        return juridicalPersons;
    }
    
    public static ArrayList<Person> searchLegalPersons(String id) throws RemoteException, DatabaseErrorException, DatabaseErrorException {
        ArrayList<Person> allPersons = Globals.getInstance().getChannel().searchPersons(id);
        ArrayList<Person> legalPersons = filterLegalPersons(allPersons);
        return legalPersons;
    }
    
    public static ArrayList<Person> searchJuridicalPersons(String id) throws RemoteException, DatabaseErrorException {
        ArrayList<Person> allPersons = Globals.getInstance().getChannel().searchPersons(id);
        ArrayList<Person> juridicalPersons = filterJuridicalPersons(allPersons);
        return juridicalPersons;
    }
    
    private static ArrayList<Person> filterLegalPersons(ArrayList<Person> persons) {
        ArrayList<Person> legalPersons = new ArrayList<>();
           
        persons.forEach(person -> {
            if(person.getClass().equals(LegalPerson.class)) {
                legalPersons.add(person);
            }
        });
       
        return legalPersons;
    }
    
    private static ArrayList<Person> filterJuridicalPersons(ArrayList<Person> persons) {
        ArrayList<Person> juridicalPersons = new ArrayList<>();
        
        persons.forEach(person -> {
            if(person.getClass().equals(JuridicalPerson.class)) {
                juridicalPersons.add(person);
            }
        });
        
        return juridicalPersons;
    }
}
