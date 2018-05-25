/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Classes.Persons.Person;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import deskprojectserver.Database.DAO.Persons.PersonDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.ResultSet;
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

    public MySqlPersonDAO() {
        super(new MySqlAddressDAO(), new MySqlEmployeeDAO(),
                new MySqlLegalPersonDAO(), new MySqlJuridicaPersonDAO(),
                new MySqlSupplierDAO());
    }

    @Override
    public void basicInsertPerson(Person p) throws DatabaseErrorException,DuplicatedEntryException {
        try{
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, p.getId(), p.getName(),
                p.getTelephones().get(0), p.getTelephones().get(1));
        }
        catch(MySQLIntegrityConstraintViolationException e){
            throw new DuplicatedEntryException();
        }
        catch(Exception e){
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void basicUpdatePerson(Person p) throws DatabaseErrorException,NoResultsException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void basicRemovePerson(Person p) throws DatabaseErrorException,NoResultsException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Person> getAllPersons() throws DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public Person basicGetPerson(String id)  throws DatabaseErrorException,NoResultsException{
        Person p = new Person(null, null, null, id) {
        };
        try{
        QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_SINGLE_SQL, id);
        ArrayList<String> tels = new ArrayList<>();
        while (qr.getRs().next()) {
            p.setName(qr.getRs().getString(NAME));
            p.setId(id);
            tels.add(qr.getRs().getString(TEL_1));
            tels.add(qr.getRs().getString(TEL_2));
            p.setTelephones(tels);
        }
        qr.closeAll(); 
       if(p.getName()==null){
            throw new NoResultsException();
        }
        return p;
        }
        catch(Exception e){
            throw new DatabaseErrorException();
        }
    }

}
