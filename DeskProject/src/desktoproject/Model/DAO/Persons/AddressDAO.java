/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

import desktoproject.Model.Classes.Persons.Address;
import desktoproject.Model.Classes.Persons.Person;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class AddressDAO {
    public abstract void insertAddress(Address address);
    public abstract void removeAddress(Address address);
    public abstract void updateAddress(Address address);
    public abstract Address getAddress(Person person);
    public abstract ArrayList<Address> getAllAddresses();  
}
