/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Transactions.ServiceType;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.ServiceTypeDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlServiceTypeDAO extends ServiceTypeDAO {
    private static final String NAME="nameServiceType";
    private static final String PRICE="priceServiceType";
    private static final String ID="idServiceType";
    private static final String INSERT_SQL = "INSERT INTO "
            + "`ServiceType`(`nameServiceType`, `priceServiceType`) "
            + "VALUES (?,?)";
    private static final String GET_ONE_SQL = "SELECT `idServiceType`,`nameServiceType`, `priceServiceType` "
            + "FROM `ServiceType` WHERE nameServiceType=?";
    private static final String GET_ALL_SQL="SELECT `idServiceType`,`nameServiceType`, `priceServiceType` "
            + "FROM `ServiceType`";

    @Override
    public void insertServiceType(ServiceType st) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, st.getName(), st.getPrice());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateServiceType(ServiceType st) throws DatabaseErrorException, DuplicatedEntryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceType getServiceType(String id) throws DatabaseErrorException, NoResultsException {
        ServiceType st = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getResultSet().next()) {
                st = new ServiceType(qr.getResultSet().getInt(ID),qr.getResultSet().getString(NAME),
                        qr.getResultSet().getFloat(PRICE));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        if (st == null) {
            throw new NoResultsException();
        }
        return st;
    }

    @Override
    public ArrayList<ServiceType> getAllServiceTypes() throws DatabaseErrorException {
        ArrayList<ServiceType> services = new ArrayList<>();
        try{
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
            while(qr.getResultSet().next()){
                services.add(new ServiceType(qr.getResultSet().getInt(ID),qr.getResultSet().getString(NAME),
                        qr.getResultSet().getFloat(PRICE)));
            }
        }
        catch(ClassNotFoundException | SQLException e){
            throw new DatabaseErrorException();
        }
        return services;
    }

}
