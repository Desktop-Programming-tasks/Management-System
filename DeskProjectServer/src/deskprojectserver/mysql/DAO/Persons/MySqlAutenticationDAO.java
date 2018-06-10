/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Enums.Autentication;
import Exceptions.DatabaseErrorException;
import deskprojectserver.Database.DAO.Persons.AutenticationDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ecsanchesjr
 */
public class MySqlAutenticationDAO extends AutenticationDAO {

    private final String PASS = "passwordEmployee";
    
    private final String GET_WITH_LOGIN = "SELECT `loginEmployee`, `passwordEmployee` FROM `Employee` WHERE loginEmployee = ?";
    
    @Override
    public Autentication autenticate(String login, String password) throws DatabaseErrorException {
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_WITH_LOGIN, login);
            ResultSet result = qr.getResultSet();
            while(result.next()) {
                String passwordDB = result.getString(PASS);
                if(validatePassword(password, passwordDB)) {
                    return Autentication.SUCCESS;
                } else {
                    return Autentication.WRONG_PASSWORD;
                }
            }
            return Autentication.USERNAME_NOT_FOUND;
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }
}
