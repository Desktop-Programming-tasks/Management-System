/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Persons.Address;
import Classes.Persons.Person;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Persons.PersonDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlPersonDAO extends PersonDAO {

    protected final static String ID = "idPerson";
    protected final static String NAME = "namePerson";
    protected final static String TEL_1 = "tel1Person";
    protected final static String TEL_2 = "tel2Person";

    private final static String INSERT_SQL = "INSERT INTO `Person`(`idPerson`, "
            + "`namePerson`, `tel1Person`, `tel2Person`)"
            + " VALUES (?,?,?,?)";
    private final static String GET_SINGLE_SQL = "SELECT `idPerson`, `namePerson`, "
            + "`tel1Person`, `tel2Person` FROM `Person` "
            + "WHERE idPerson=?";
    private final static String GET_ALL_SQL = "SELECT `idPerson`, `namePerson`,"
            + " `tel1Person`, `tel2Person` FROM `Person` WHERE 1";

    public MySqlPersonDAO() {
        super(new MySqlAddressDAO(), new MySqlEmployeeDAO(),
                new MySqlLegalPersonDAO(), new MySqlJuridicaPersonDAO(),
                new MySqlSupplierDAO());
    }

    @Override
    public void basicInsertPerson(Person p) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, p.getId(), p.getName(),
                    p.getTelephones().get(0), p.getTelephones().get(1));
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void basicUpdatePerson(Person p) throws DatabaseErrorException, NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Person> getAllPersons() throws DatabaseErrorException {
        ArrayList<Person> persons = new ArrayList<>();

        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
            while (qr.getResultSet().next()) {
                ArrayList<String> telephones = new ArrayList<>();
                telephones.add(qr.getResultSet().getString(TEL_1));
                telephones.add(qr.getResultSet().getString(TEL_2));
                Person p;
                p = new BasicPerson(qr.getResultSet().getString(NAME), null,
                        telephones, qr.getResultSet().getString(ID));
                try {
                    p.setAddress(super.getAddressDAO().getAddress(p));
                } catch (DatabaseErrorException | NoResultsException e) {
                    //
                }
                persons.add(p);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return persons;
    }

    @Override
    public Person basicGetPerson(String id) throws DatabaseErrorException, NoResultsException {
        Person p = new Person(null, null, null, id){};
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_SINGLE_SQL, id);
            ArrayList<String> tels = new ArrayList<>();
            while (qr.getResultSet().next()) {
                p.setName(qr.getResultSet().getString(NAME));
                p.setId(id);
                tels.add(qr.getResultSet().getString(TEL_1));
                tels.add(qr.getResultSet().getString(TEL_2));
                p.setTelephones(tels);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        if (p.getName() == null) {
            throw new NoResultsException();
        }
        return p;
    }

    private class BasicPerson extends Person implements Serializable{

        public BasicPerson(String name, Address address, ArrayList<String> telephones, String Id) {
            super(name, address, telephones, Id);
        }

    }
}
