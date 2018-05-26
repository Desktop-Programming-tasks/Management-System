/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.LegalPerson;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class LegalPersonDAO {
    public abstract void insertLegalPerson(LegalPerson lp) throws DatabaseErrorException, DuplicatedEntryException;
    public abstract void updateLegalPerson(LegalPerson lp) throws DatabaseErrorException,NoResultsException;
    public abstract void removeLegalPerson(LegalPerson lp) throws DatabaseErrorException,NoResultsException;
    public abstract LegalPerson getLegalPerson(String id)  throws DatabaseErrorException,NoResultsException;
    public abstract ArrayList<LegalPerson> getAllLegalPersons() throws DatabaseErrorException;
}
