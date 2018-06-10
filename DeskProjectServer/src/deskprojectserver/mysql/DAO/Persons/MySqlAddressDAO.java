/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Persons.Address;
import Classes.Persons.Person;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import deskprojectserver.Database.DAO.Persons.AddressDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MySqlAddressDAO extends AddressDAO {

    private static final String STREET = "streetAddress";
    private static final String NUMBER = "numberAddress";
    private static final String DISTRICT = "districtAddress";
    private static final String CITY = "City_nameCity";
    private static final String STATE = "City_State_nameState";

    private static final String INSERT_SQL
            = "INSERT INTO `Address`(`Person_idPerson`, `streetAddress`, `numberAddress`, `districtAddress`, "
            + "`City_nameCity`, `City_State_nameState`) "
            + "VALUES (?,?,?,?,?,?)";
    private static final String GET_ONE_SQL
            = "SELECT `Person_idPerson`, `streetAddress`, `numberAddress`, "
            + "`districtAddress`, `City_nameCity`, `City_State_nameState` "
            + "FROM `Address` WHERE Person_idPerson=?";
    private static final String UPDATE_SQL
            = "UPDATE `Address` SET `streetAddress`=?,"
            + "`numberAddress`=?,`districtAddress`=?,"
            + "`City_nameCity`=?,`City_State_nameState`=?"
            + "WHERE Person_idPerson=?";
    private static final String REMOVE_SQL = "DELETE FROM `Address` WHERE Person_idPerson=?";

    @Override
    public void insertAddress(Person person) throws DatabaseErrorException {
        Address ads = person.getAddress();
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, person.getDocumentId(),
                    ads.getStreet(), ads.getNumber(), ads.getDistrict(), ads.getCity(), ads.getState());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void removeAddress(Person person) throws DatabaseErrorException {
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_SQL, person.getDocumentId());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateAddress(Person person) throws DatabaseErrorException, NoResultsException {
        getAddress(person);
        Address temp = person.getAddress();
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL,
                    temp.getStreet(), temp.getNumber(), temp.getDistrict(), temp.getCity(),
                    temp.getState(), person.getDocumentId());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Address getAddress(Person person) throws DatabaseErrorException, NoResultsException {
        Address address = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, person.getDocumentId());

            while (qr.getResultSet().next()) {
                address = new Address(
                        qr.getResultSet().getString(STREET), qr.getResultSet().getInt(NUMBER), qr.getResultSet().getString(DISTRICT),
                        qr.getResultSet().getString(CITY), qr.getResultSet().getString(STATE));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        if (address == null) {
            throw new NoResultsException();
        }
        return address;

    }

    @Override
    public ArrayList<Address> getAllAddresses() throws DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
