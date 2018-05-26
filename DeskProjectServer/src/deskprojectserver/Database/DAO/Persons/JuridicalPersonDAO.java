/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import Classes.Persons.JuridicalPerson;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import java.util.ArrayList;


/**
 *
 * @author gabriel
 */
public abstract class JuridicalPersonDAO {
    public abstract void insertJuridicalPerson(JuridicalPerson jp) throws DuplicatedEntryException,DatabaseErrorException;
    public abstract void updateJuridicalPerson(JuridicalPerson jp) throws DatabaseErrorException,NoResultsException;
    public abstract void removeJuridcalPerson(JuridicalPerson jp) throws DatabaseErrorException,NoResultsException;
    public abstract JuridicalPerson getJuridicalPerson(String id) throws DatabaseErrorException, NoResultsException;
    public abstract ArrayList<JuridicalPerson> getAllJuridicalPersons() throws DatabaseErrorException;
}
