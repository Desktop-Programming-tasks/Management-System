/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.JuridicalPerson;
import deskprojectserver.Database.DAO.Persons.JuridicalPersonDAO;
import deskprojectserver.Database.Database;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlJuridicaPersonDAO extends JuridicalPersonDAO{
    private static final String ID= "Person_idPerson";
    private static final String INSERT_SQL = "INSERT INTO `JuridicalPerson`(`Person_idPerson`) VALUES (?)";
    private static final String GET_ONE_SQL="SELECT `Person_idPerson` "
            + "FROM `JuridicalPerson` WHERE Person_idPerson=?";
    @Override
    public void insertJuridicalPerson(JuridicalPerson jp) throws SQLException, ClassNotFoundException {
        MySqlHandler.getInstance().getDb().execute(INSERT_SQL, jp.getCNPJ());
    }

    @Override
    public void updateJuridicalPerson(JuridicalPerson jp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeJuridcalPerson(JuridicalPerson jp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JuridicalPerson getJuridicalPerson(String id) throws SQLException, ClassNotFoundException {
        QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL,id);
        JuridicalPerson jp = null;
        while(qr.getRs().next()){
            jp = new JuridicalPerson(null, null, null, null);
        }
        return jp;
    }

    @Override
    public ArrayList<JuridicalPerson> getAllJuridicalPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
