/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.Employee;
import deskprojectserver.Database.DAO.Persons.EmployeeDAO;
import deskprojectserver.Database.Database;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlEmployeeDAO extends EmployeeDAO{
    private static final String INSERT_SQL="INSERT INTO `Employee`(`Person_idPerson`, `loginEmployee`, `passwordEmployee`, `EmployeeType_idEmployeeType`) "
            + "VALUES (?,?,?,?)";
    @Override
    public void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        MySqlHandler.getInstance().getDb().execute(INSERT_SQL, 
                employee.getCPF(),employee.getLogin(),employee.getPassword(),
                employee.getEmployeeType());
    }

    @Override
    public void updateEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee getEmployee(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
