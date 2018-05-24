/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Database.DAO.Persons;

import deskprojectserver.Classes.Persons.LegalPerson;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class LegalPersonDAO {
    public abstract void insertLegalPerson(LegalPerson lp);
    public abstract void updateLegalPerson(LegalPerson lp);
    public abstract void removeLegalPerson(LegalPerson lp);
    public abstract LegalPerson getLegalPerson(String id);
    public abstract ArrayList<LegalPerson> getAllLegalPersons();
}
