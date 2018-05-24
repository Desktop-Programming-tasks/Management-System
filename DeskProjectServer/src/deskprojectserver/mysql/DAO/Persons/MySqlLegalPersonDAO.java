/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.LegalPerson;
import deskprojectserver.Database.DAO.Persons.LegalPersonDAO;
import deskprojectserver.Database.Database;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlLegalPersonDAO extends LegalPersonDAO{
    private static final String INSERT_SQL="INSERT INTO `LegalPerson`(`rgLegalPerson`, `Person_idPerson`) "
                        + "VALUES (?,?)";
    @Override
    public void insertLegalPerson(LegalPerson lp) throws SQLException, ClassNotFoundException{
        MySqlHandler.getInstance().getDb().execute(INSERT_SQL,lp.getRG(),lp.getCPF());
    }

    @Override
    public void updateLegalPerson(LegalPerson lp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeLegalPerson(LegalPerson lp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LegalPerson getLegalPerson(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LegalPerson> getAllLegalPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
