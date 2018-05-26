/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.DBExceptions.DatabaseErrorException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class LocationsDAO {
    public abstract ArrayList<String> getStates() throws DatabaseErrorException;
    public abstract ArrayList<String> getCities(String state) throws DatabaseErrorException;
    
}
