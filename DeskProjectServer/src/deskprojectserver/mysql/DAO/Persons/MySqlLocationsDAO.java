/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Exceptions.DatabaseErrorException;
import deskprojectserver.Database.DAO.Persons.LocationsDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlLocationsDAO extends LocationsDAO {

    private final static String STATE = "nameState";
    private final static String CITY = "nameCity";
    private final static String GET_STATES = "SELECT `nameState` FROM `State` WHERE 1";
    private final static String GET_CITIES = "SELECT `nameCity`  FROM `City` "
            + "WHERE State_nameState=?";

    @Override
    public ArrayList<String> getStates() throws DatabaseErrorException {
        ArrayList<String> states = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_STATES);
            while (qr.getResultSet().next()) {
                states.add(qr.getResultSet().getString(STATE));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return states;
    }

    @Override
    public ArrayList<String> getCities(String state) throws DatabaseErrorException {
        ArrayList<String> cities = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_CITIES, state);
            while (qr.getResultSet().next()) {
                cities.add(qr.getResultSet().getString(CITY));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return cities;
    }
}
