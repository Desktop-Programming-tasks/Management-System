/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.Commons;

import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.Utils.ActivationStatus;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MySqlBrandDAO extends BrandDAO {

    private static final String INSERT_SQL
            = "INSERT INTO `Brand`(`nameBrand`, `isActiveBrand`)"
            + " VALUES (?,?)";
    private static final String GET_ALL_SQL = "SELECT `idBrand`, `nameBrand`, `isActiveBrand` "
            + "FROM `Brand` WHERE 1";

    private static final String NAME = "nameBrand";
    private static final String ID = "idBrand";
    private static final String IS_ACTIVE = "isActiveBrand";
    private static final String UPDATE_SQL = "UPDATE `Brand` "
            + "SET `nameBrand`=?,`isActiveBrand`=?"
            + " WHERE idBrand=?";
    private static final String CHECK_SQL = "SELECT `nameBrand` "
            + "FROM `Brand` WHERE nameBrand=?";
    private static final String GET_ONE = "SELECT `idBrand`, `nameBrand`, `isActiveBrand` "
            + "FROM `Brand` WHERE nameBrand=? AND isActiveBrand";
    private static final String GET_ONE_INACTIVE = "SELECT `idBrand`, `nameBrand`, `isActiveBrand` "
            + "FROM `Brand` WHERE nameBrand=?";

    @Override
    public void insertBrand(Brand brand) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, brand.getName(),
                    ActivationStatus.ACTIVE_STATE);
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateBrand(Brand brand) throws DatabaseErrorException, NoResultsException {
        getBrand(brand.getName(), false);
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL,
                    brand.getName(), brand.isActive(), brand.getId());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void removeBrand(Brand brand) throws DatabaseErrorException, NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Brand> getAllBrands() throws DatabaseErrorException {
        ArrayList<Brand> brands = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
            while (qr.getResultSet().next()) {
                brands.add(new Brand(qr.getResultSet().getInt(ID),
                        qr.getResultSet().getString(NAME), true));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return brands;
    }

    @Override
    public void checkIfExists(Brand brand) throws DatabaseErrorException, NoResultsException {
        try {
            QueryResult qr = MySqlHandler.getInstance().
                    getDb().query(CHECK_SQL, brand.getName());
            while (qr.getResultSet().next()) {
                return;
            }
            throw new NoResultsException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    protected Brand getBrand(String id, boolean justActive) throws DatabaseErrorException, NoResultsException {
        QueryResult qr;
        if (justActive) {
            try {
                qr = MySqlHandler.getInstance().getDb().query(GET_ONE, id);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new DatabaseErrorException();
            }
        } else {
            try {
                qr = MySqlHandler.getInstance().getDb().query(GET_ONE_INACTIVE, id);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new DatabaseErrorException();
            }
        }
        Brand brand = null;
        try {
            while (qr.getResultSet().next()) {
                brand = new Brand(qr.getResultSet().getInt(ID),
                        qr.getResultSet().getString(NAME),
                        qr.getResultSet().getBoolean(IS_ACTIVE));
            }
        } catch (SQLException ex) {
            throw new DatabaseErrorException();
        }
        if (brand == null) {
            throw new NoResultsException();
        }
        return brand;

    }
}
