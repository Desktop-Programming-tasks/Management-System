/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

import desktoproject.Globals;
import desktoproject.Model.Classes.Persons.Person;
import java.rmi.RemoteException;

/**
 *
 * @author ecsanchesjr
 */
public abstract class PersonDAO {
    public static void insertPerson(Person person) throws RemoteException {
        Globals.getInstance().getChannel().insertPerson(person);
    }
    
    public static Person queryPerson(String id) throws RemoteException {
        return Globals.getInstance().getChannel().queryPerson(id);
    }
}
