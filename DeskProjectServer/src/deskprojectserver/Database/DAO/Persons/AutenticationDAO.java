/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import Classes.Enums.Autentication;
import Exceptions.DatabaseErrorException;

/**
 *
 * @author ecsanchesjr
 */
public abstract class AutenticationDAO {

    public boolean validatePassword(String password, String passwordDB) {
        return password.equals(passwordDB);
    }
    
    public abstract Autentication autenticate(String login, String password) throws DatabaseErrorException;
}
