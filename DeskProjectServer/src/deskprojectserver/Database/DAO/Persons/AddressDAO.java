/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.Address;
import deskprojectserver.Classes.Persons.Person;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class AddressDAO {
    public abstract void insertAddress(Person person) throws Exception;
    public abstract void removeAddress(Person person) throws Exception;
    public abstract void updateAddress(Person person) throws Exception;
    public abstract Address getAddress(Person person) throws Exception;
    public abstract ArrayList<Address> getAllAddresses() throws Exception;  
}
