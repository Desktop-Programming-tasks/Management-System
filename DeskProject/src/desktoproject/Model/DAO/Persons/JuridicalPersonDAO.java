/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

import desktoproject.Model.Classes.Persons.JuridicalPerson;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class JuridicalPersonDAO {
    public abstract void insertJuridicalPerson(JuridicalPerson jp);
    public abstract void updateJuridicalPerson(JuridicalPerson jp);
    public abstract void removeJuridcalPerson(JuridicalPerson jp);
    public abstract JuridicalPerson getJuridicalPerson(String id);
    public abstract ArrayList<JuridicalPerson> getAllJuridicalPersons();
}
