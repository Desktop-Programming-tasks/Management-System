/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.Address;
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.Database.DAO.Persons.AddressDAO;
import deskprojectserver.Database.Database;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlAddressDAO extends AddressDAO{
    private static final String INSERT_SQL =
            "INSERT INTO `Address`(`Person_idPerson`, `streetAddress`, `numberAddress`, `districtAddress`, "
            + "`City_nameCity`, `City_State_nameState`) "
            + "VALUES (?,?,?,?,?,?)";
    @Override
    public void insertAddress(Person person) throws SQLException, ClassNotFoundException {
        Address ads = person.getAddress();
        Database.getInstance().execute(INSERT_SQL, person.getId(),
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
    public Address getAddress(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Address> getAllAddresses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
