/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Persons.Employee;
import Classes.Persons.Person;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Persons.EmployeeDAO;
import deskprojectserver.Database.DAO.Persons.PersonDAO;
import static deskprojectserver.Utils.ActivationStatus.ACTIVE_STATE;
import static deskprojectserver.Utils.ActivationStatus.INACTIVE_STATE;
import deskprojectserver.Utils.FormatUtils;
import deskprojectserver.Utils.QueryExecuter;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlPersonDAO extends PersonDAO {

    protected final static String ID = "idPerson";
    protected final static String ID_DOCUMENT = "idDocumentPerson";
    protected final static String NAME = "namePerson";
    protected final static String TEL_1 = "tel1Person";
    protected final static String TEL_2 = "tel2Person";
    private final static String IS_ACTIVE = "isActivePerson";

    private final static String INSERT_SQL = "INSERT INTO "
            + "`Person`(`idDocumentPerson`, `namePerson`, `tel1Person`, `tel2Person`"
            + ", `isActivePerson`) "
            + "VALUES (?,?,?,?,?)";

    private final static String GET_SINGLE_SQL = "SELECT `idPerson`,`idDocumentPerson`, `namePerson`, "
            + "`tel1Person`, `tel2Person`,`isActivePerson` FROM `Person` "
            + "WHERE idDocumentPerson=? AND isActivePerson";

    private final static String GET_SINGLE_SQL_INACT = ""
            + "SELECT `idPerson`,`idDocumentPerson`, `namePerson`, "
            + "`tel1Person`, `tel2Person`,`isActivePerson` FROM `Person` "
            + "WHERE idDocumentPerson=?";

    private final static String GET_ALL_ID = "SELECT `idDocumentPerson` FROM `Person` "
            + "WHERE isActivePerson";

    private final static String GET_LIKE_ID = "SELECT `idDocumentPerson` FROM `Person` WHERE "
            + " namePerson LIKE ? AND isActivePerson";

    private final static String UPDATE_SQL = "UPDATE `Person` SET"
            + "`idDocumentPerson`=?,`namePerson`=?,`tel1Person`=?,"
            + "`tel2Person`=?,`isActivePerson`=? WHERE idPerson=?";

    public MySqlPersonDAO() {
        super(new MySqlAddressDAO(), new MySqlEmployeeDAO(),
                new MySqlLegalPersonDAO(), new MySqlJuridicaPersonDAO(),
                new MySqlSupplierDAO());
    }

    @Override
    public void basicInsertPerson(Person p) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, p.getDocumentId(), p.getName(),
                    p.getTelephones().get(0), p.getTelephones().get(1), ACTIVE_STATE);
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void basicUpdatePerson(Person p) throws DatabaseErrorException, NoResultsException {
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL, p.getDocumentId(), p.getName(),
                    p.getTelephones().get(0), p.getTelephones().get(1),
                    p.isActive() ? ACTIVE_STATE : INACTIVE_STATE,
                    p.getId());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Person basicGetPerson(String id, boolean justActive) throws DatabaseErrorException, NoResultsException {
        Person p = new Person(null, null, null, id) {
        };
        try {
            QueryResult qr;
            if (justActive) {
                qr = MySqlHandler.getInstance().getDb().query(GET_SINGLE_SQL, id);
            } else {
                qr = MySqlHandler.getInstance().getDb().query(GET_SINGLE_SQL_INACT, id);
            }
            ArrayList<String> tels = new ArrayList<>();
            while (qr.getResultSet().next()) {
                p.setId(qr.getResultSet().getInt(ID));
                p.setName(qr.getResultSet().getString(NAME));
                p.setDocumentId(id);
                tels.add(qr.getResultSet().getString(TEL_1));
                tels.add(qr.getResultSet().getString(TEL_2));
                p.setActive(qr.getResultSet().getBoolean(IS_ACTIVE));
                p.setTelephones(tels);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
        if (p.getName() == null) {
            throw new NoResultsException();
        }
        return p;
    }

    private ArrayList<Person> getPersonsGeneric(QueryExecuter exec) throws DatabaseErrorException {
        ArrayList<Person> persons = new ArrayList<>();
        try {
            QueryResult qr = exec.execute();
            while (qr.getResultSet().next()) {
                try {
                    persons.add(getPerson(qr.getResultSet().getString(ID_DOCUMENT)));
                } catch (NoResultsException e) {
                    //
                }
            }
            qr.closeAll();
        } catch (SQLException e) {
            throw new DatabaseErrorException();
        }
        return persons;
    }

    @Override
    public ArrayList<Person> getAllPersons() throws DatabaseErrorException {
        return getPersonsGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_ALL_ID);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }

    @Override
    public ArrayList<Person> getLikePersons(String id) throws DatabaseErrorException {
        return getPersonsGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_LIKE_ID,
                            FormatUtils.setLikeParam(id));
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }
}
