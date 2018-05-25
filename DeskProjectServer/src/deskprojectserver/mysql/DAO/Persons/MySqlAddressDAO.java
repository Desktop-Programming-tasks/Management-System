/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.Address;
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.Database.DAO.Persons.AddressDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlAddressDAO extends AddressDAO{
    private static final String STREET="streetAddress";
    private static final String NUMBER="numberAddress";
    private static final String DISTRICT = "districtAddress";
    private static final String CITY="City_nameCity";
    private static final String STATE="City_State_nameState";
    
    private static final String INSERT_SQL =
            "INSERT INTO `Address`(`Person_idPerson`, `streetAddress`, `numberAddress`, `districtAddress`, "
            + "`City_nameCity`, `City_State_nameState`) "
            + "VALUES (?,?,?,?,?,?)";
    private static final String GET_ONE_SQL=
            "SELECT `Person_idPerson`, `streetAddress`, `numberAddress`, "
            + "`districtAddress`, `City_nameCity`, `City_State_nameState` "
            + "FROM `Address` WHERE Person_idPerson=?";
    @Override
    public void insertAddress(Person person) throws SQLException, ClassNotFoundException {
        Address ads = person.getAddress();
        MySqlHandler.getInstance().getDb().execute(INSERT_SQL, person.getId(),
                ads.getStreet(), ads.getNumber(),ads.getBlock(),ads.getCity(),ads.getState());
    }

    @Override
    public void removeAddress(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAddress(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address getAddress(Person person) throws SQLException, ClassNotFoundException {
        QueryResult qr=MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, person.getId());
        Address address=null;
        while(qr.getRs().next()){
            address= new Address(
                    qr.getRs().getString(STREET), qr.getRs().getInt(NUMBER), qr.getRs().getString(DISTRICT),
                    qr.getRs().getString(CITY),qr.getRs().getString(STATE));
        }
        qr.closeAll();
        return address;
    }

    @Override
    public ArrayList<Address> getAllAddresses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
