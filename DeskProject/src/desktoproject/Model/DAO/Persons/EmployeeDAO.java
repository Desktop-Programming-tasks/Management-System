/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

import desktoproject.Model.Classes.Persons.Employee;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class EmployeeDAO {
    public abstract void insertEmployee(Employee employee);
    public abstract void updateEmployee(Employee employee);
    public abstract void removeEmployee(Employee employee);
    public abstract Employee getEmployee(String id);
    public abstract ArrayList<Employee> getAllEmployees();
}
