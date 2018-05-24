/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.Database.DAO.Persons.PersonDAO;
import deskprojectserver.Database.Database;
import deskprojectserver.mysql.MySqlHandler;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlPersonDAO extends PersonDAO{
    private final static String INSERT_SQL="INSERT INTO `Person`(`idPerson`, "
            + "`namePerson`, `tel1Person`, `tel2Person`)"
            + " VALUES (?,?,?,?)";
    @Override
    public void setDAOs() {
        
        super.setLegalPersonDAO(new MySqlLegalPersonDAO());
        super.setJuridicalDAO(new MySqlJuridicaPersonDAO());
        super.setEmployeeDAO(new MySqlEmployeeDAO());
        super.setAddressDAO(new MySqlAddressDAO());
    }

    @Override
    public void basicInsertPerson(Person p) throws Exception {
        MySqlHandler.getInstance().getDb().execute(INSERT_SQL, p.getId(),p.getName(),
                p.getTelephones().get(0),p.getTelephones().get(1));
    }

    @Override
    public void basicUpdatePerson(Person p) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void basicRemovePerson(Person p) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person getPerson(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Person> getAllPersons() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
