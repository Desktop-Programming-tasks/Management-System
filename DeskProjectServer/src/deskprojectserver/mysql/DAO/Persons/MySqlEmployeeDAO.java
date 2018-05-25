/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Classes.Persons.Employee;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import deskprojectserver.Database.DAO.Persons.EmployeeDAO;
import deskprojectserver.Enums.EmployeeType;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlEmployeeDAO extends EmployeeDAO {

    private static final String LOGIN = "loginEmployee";
    private static final String PASSWORD = "passwordEmployee";
    private static final String EMP_TYPE = "EmployeeType_idEmployeeType";

    private static final String INSERT_SQL = "INSERT INTO `Employee`(`loginEmployee`, "
            + "`passwordEmployee`, `EmployeeType_idEmployeeType`, "
            + "`LegalPerson_Person_idPerson`) "
            + "VALUES (?,?,?,?)";
    private static final String GET_ONE_SQL = "SELECT `loginEmployee`, `passwordEmployee`, "
            + "`EmployeeType_idEmployeeType`, `LegalPerson_Person_idPerson` FROM "
            + "`Employee` WHERE LegalPerson_Person_idPerson=?";

    @Override
    public void insertEmployee(Employee employee) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            if (employee.getEmployeeType() == EmployeeType.COMMOM) {
                MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                        employee.getLogin(), employee.getPassword(),
                        2, employee.getCPF());
            } else if (employee.getEmployeeType() == EmployeeType.MANAGER) {
                MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                        employee.getLogin(), employee.getPassword(),
                        1, employee.getCPF());
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (Exception e) {
            throw new DatabaseErrorException();
        }
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
    public Employee getEmployee(String id) throws DatabaseErrorException, NoResultsException {
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getRs().next()) {
                return new Employee(qr.getRs().getString(LOGIN),
                        qr.getRs().getString(PASSWORD),
                        (qr.getRs().getInt(EMP_TYPE) == 1) ? EmployeeType.MANAGER : EmployeeType.COMMOM,
                        null, null, null, null, null);
            }
            qr.closeAll();
            throw new NoResultsException();
        }
        catch(Exception e){
            throw new DatabaseErrorException();
        }

    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
