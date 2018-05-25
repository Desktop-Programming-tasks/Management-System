/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
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
