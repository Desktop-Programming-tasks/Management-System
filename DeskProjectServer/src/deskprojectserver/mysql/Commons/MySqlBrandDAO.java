/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.Commons;

import Classes.Transactions.Brand;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
import deskprojectserver.Database.DAO.Transactions.BrandDAO;
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

    private static final String INSERT_SQL = "INSERT INTO `Brand`(`nameBrand`) VALUES (?)";
    private static final String GET_ONE_SQL = "SELECT `idBrand`, `nameBrand` "
            + "FROM `Brand` WHERE idBrand=?";
    private static final String GET_ALL_SQL = "SELECT `idBrand`, `nameBrand` "
            + "FROM `Brand` WHERE 1";
    private static final String ID = "idBrand";
    private static final String NAME = "nameBrand";

    @Override
    public void insertBrand(Brand brand) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, brand.getName());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateBrand(Brand brand) throws DatabaseErrorException, NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeBrand(Brand brand) throws DatabaseErrorException, NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Brand getBrand(String id) throws DatabaseErrorException, NoResultsException {
        Brand brand = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getResultSet().next()) {
                brand = new Brand(qr.getResultSet().getInt(ID),
                        qr.getResultSet().getString(NAME));
            }
            if (brand == null) {
                throw new NoResultsException();
            }
            qr.closeAll();
            return brand;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }

    }

    @Override
    public ArrayList<Brand> getAllBrands() throws DatabaseErrorException {
        ArrayList<Brand> brands = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
            while (qr.getResultSet().next()) {
                brands.add(new Brand(qr.getResultSet().getInt(ID), qr.getResultSet().getString(NAME)));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return brands;
    }

}
