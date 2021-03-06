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
import deskprojectserver.Observable.Observables.ObservablesHolder;
import deskprojectserver.Utils.FormatUtils;
import deskprojectserver.Utils.QueryExecuter;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlServiceTypeDAO extends ServiceTypeDAO {

    private static final String NAME = "nameServiceType";
    private static final String PRICE = "priceServiceType";
    private static final String ID = "idServiceType";
    private static final String IS_ACTIVE = "isActiveServiceType";
    private static final String INSERT_SQL = "INSERT INTO "
            + "`ServiceType`(`nameServiceType`, `priceServiceType`,`isActiveServiceType`)"
            + "VALUES (?,?,?)";
    private static final String GET_ONE_SQL = "SELECT `idServiceType`,`nameServiceType`, `priceServiceType` "
            + ",`isActiveServiceType` FROM `ServiceType` WHERE nameServiceType=?"
            + " OR idServiceType=?";
    private static final String GET_ALL_SQL = "SELECT `idServiceType`,`nameServiceType`, `priceServiceType` "
            + ",`isActiveServiceType` FROM `ServiceType` WHERE isActiveServiceType";
    private static final String UPDATE_SQL = "UPDATE `ServiceType` "
            + "SET `nameServiceType`=?,`priceServiceType`=?,`isActiveServiceType`=? WHERE idServiceType=?";
    private static final String GET_LIKE_SQL = "SELECT `idServiceType`,`nameServiceType`, `priceServiceType`, "
            + " `isActiveServiceType` FROM `ServiceType` WHERE nameServiceType LIKE ? AND isActiveServiceType";

    @Override
    protected void insertBasicServiceType(ServiceType st) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, st.getName(), st.getPrice(),
                    true);
            ObservablesHolder.getServiceType().setChanged();
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateServiceType(ServiceType st) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL, st.getName(), st.getPrice(),
                    st.isActive(), st.getId());
            ObservablesHolder.getServiceType().setChanged();
        } catch (MySQLIntegrityConstraintViolationException ex) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public ServiceType getServiceType(String id) throws DatabaseErrorException, NoResultsException {
        ServiceType st = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id,id);
            while (qr.getResultSet().next()) {
                st = new ServiceType(qr.getResultSet().getInt(ID), qr.getResultSet().getString(NAME),
                        qr.getResultSet().getFloat(PRICE), qr.getResultSet().getBoolean(IS_ACTIVE));
                st.setActive(qr.getResultSet().getBoolean(IS_ACTIVE));
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
        return getServiceTypesGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }

    @Override
    public ArrayList<ServiceType> getLikeServiceTypes(String id) throws DatabaseErrorException {
        return getServiceTypesGeneric(new QueryExecuter() {
            @Override
            public QueryResult execute() throws DatabaseErrorException {
                try {
                    return MySqlHandler.getInstance().getDb().query(GET_LIKE_SQL,
                            FormatUtils.setLikeParam(id));
                } catch (ClassNotFoundException | SQLException e) {
                    throw new DatabaseErrorException();
                }
            }
        });
    }

    private ArrayList<ServiceType> getServiceTypesGeneric(QueryExecuter exec) throws DatabaseErrorException {
        ArrayList<ServiceType> services = new ArrayList<>();
        try {
            QueryResult qr = exec.execute();
            while (qr.getResultSet().next()) {
                services.add(new ServiceType(qr.getResultSet().getInt(ID), qr.getResultSet().getString(NAME),
                        qr.getResultSet().getFloat(PRICE), qr.getResultSet().getBoolean(IS_ACTIVE)));
            }
            qr.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();    
            throw new DatabaseErrorException();
        }
        return services;
    }

}
