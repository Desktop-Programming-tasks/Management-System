/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.Employee;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class EmployeeDAO {
    public abstract void insertEmployee(Employee employee) throws DuplicatedEntryException,DatabaseErrorException;
    public abstract void updateEmployee(Employee employee) throws DatabaseErrorException,NoResultsException;
    public abstract void removeEmployee(Employee employee) throws DatabaseErrorException,NoResultsException ;
    public abstract Employee getEmployee(String id) throws DatabaseErrorException,NoResultsException ;
    public abstract ArrayList<Employee> getAllEmployees() throws DatabaseErrorException;
}
