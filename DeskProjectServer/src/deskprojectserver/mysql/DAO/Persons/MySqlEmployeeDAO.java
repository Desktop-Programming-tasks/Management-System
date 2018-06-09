/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Enums.EmployeeType;
import Classes.Persons.Employee;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.DuplicatedLoginException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Persons.EmployeeDAO;
import deskprojectserver.Utils.FormatUtils;
import deskprojectserver.Utils.QueryExecuter;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import jdk.nashorn.internal.ir.ForNode;

/**
 *
 * @author gabriel
 */
public class MySqlEmployeeDAO extends EmployeeDAO {
    
    private static final String LOGIN = "loginEmployee";
    private static final String PASSWORD = "passwordEmployee";
    private static final String EMP_TYPE = "EmployeeType_idEmployeeType";
    private static final String ID = "LegalPerson_Person_idPerson";
    private static final String INSERT_SQL = "INSERT INTO `Employee`(`loginEmployee`, "
            + "`passwordEmployee`, `EmployeeType_idEmployeeType`, "
            + "`LegalPerson_Person_idPerson`) "
            + "VALUES (?,?,?,?)";
    private static final String GET_ONE_SQL = "SELECT `loginEmployee`, `passwordEmployee`, "
            + "`EmployeeType_idEmployeeType`, `LegalPerson_Person_idPerson` FROM "
            + "`Employee` WHERE LegalPerson_Person_idPerson=?";
    private static final String GET_ALL_SQL = "SELECT `loginEmployee`, `passwordEmployee`, "
            + "`EmployeeType_idEmployeeType`, `LegalPerson_Person_idPerson` FROM "
            + "`Employee`";
    private static final String GET_LIKE_SQL = "SELECT `loginEmployee`, `passwordEmployee`, `EmployeeType_idEmployeeType`, `LegalPerson_Person_idPerson` FROM `Employee`,`Person` WHERE Employee.LegalPerson_Person_idPerson =\n"
            + "Person.idPerson AND Person.namePerson LIKE ?";
    private static final String REMOVE_SQL = "DELETE FROM `Employee` "
            + "WHERE LegalPerson_Person_idPerson=?";
    private static final String UPDATE_SQL = "UPDATE `Employee` "
            + "SET `loginEmployee`=?,`passwordEmployee`=?"
            + " WHERE LegalPerson_Person_idPerson=?";
    
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
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }
    
    @Override
    public void updateEmployee(Employee employee) throws DatabaseErrorException, NoResultsException, DuplicatedLoginException {
        getEmployee(employee.getDocumentId());
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL,
                    employee.getLogin(), employee.getPassword(), employee.getDocumentId());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedLoginException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }
    
    @Override
    public void removeEmployee(Employee employee) throws NoResultsException, DatabaseErrorException {
        getEmployee(employee.getDocumentId());
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_SQL, employee.getDocumentId());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }
    
    @Override
    public Employee getEmployee(String id) throws DatabaseErrorException, NoResultsException {
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getResultSet().next()) {
                return new Employee(qr.getResultSet().getString(LOGIN),
                        qr.getResultSet().getString(PASSWORD),
                        (qr.getResultSet().getInt(EMP_TYPE) == 1) ? EmployeeType.MANAGER : EmployeeType.COMMOM,
                        null, null, null, null, id);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        throw new NoResultsException();
        
    }
    
    private ArrayList<Employee> getEmployeesGeneric(QueryExecuter executer) throws DatabaseErrorException {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            QueryResult qr = executer.execute();
            while (qr.getResultSet().next()) {
                if (qr.getResultSet().getInt(EMP_TYPE) == 1) {
                    Employee emp
                            = new Employee(
                                    qr.getResultSet().getString(LOGIN),
                                    qr.getResultSet().getString(PASSWORD),
                                    EmployeeType.MANAGER, null, null, null, null,
                                    qr.getResultSet().getString(ID));
                    employees.add(emp);
                } else {
                    Employee emp
                            = new Employee(
                                    qr.getResultSet().getString(LOGIN),
                                    qr.getResultSet().getString(PASSWORD),
                                    EmployeeType.COMMOM, null, null, null, null,
                                    qr.getResultSet().getString(ID));
                    employees.add(emp);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseErrorException();
        }
        return employees;
    }
    
    @Override
    public ArrayList<Employee> getAllEmployees() throws DatabaseErrorException {
        return getEmployeesGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }
    
    @Override
    public ArrayList<Employee> getLikeEmployees(String id) throws DatabaseErrorException {
        return getEmployeesGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_LIKE_SQL,
                            FormatUtils.setLikeParam(id));
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }
    
}
