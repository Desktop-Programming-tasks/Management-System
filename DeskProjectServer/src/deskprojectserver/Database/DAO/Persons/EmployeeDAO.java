/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import Classes.Persons.Employee;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class EmployeeDAO {
    public abstract void insertEmployee(Employee employee) throws DuplicatedEntryException,DatabaseErrorException;
    public abstract void updateEmployee(Employee employee) throws DatabaseErrorException,NoResultsException, DuplicatedLoginException;
    public abstract void removeEmployee(Employee employee) throws DatabaseErrorException,NoResultsException ;
    public abstract Employee getEmployee(String id) throws DatabaseErrorException,NoResultsException ;
    public abstract ArrayList<Employee> getAllEmployees() throws DatabaseErrorException;
    public abstract ArrayList<Employee> getLikeEmployees(String id) throws DatabaseErrorException;
}
