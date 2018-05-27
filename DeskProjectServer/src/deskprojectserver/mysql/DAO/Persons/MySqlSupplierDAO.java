/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import Classes.Persons.Supplier;
import Classes.Transactions.Brand;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Persons.SupplierDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlSupplierDAO extends SupplierDAO {

    private static final String INSERT_SQL = "INSERT INTO `Supplier`(`JuridicalPerson_Person_idPerson`) "
            + "VALUES (?)";
    private static final String GET_ONE_SQL
            = "SELECT `JuridicalPerson_Person_idPerson` FROM `Supplier` "
            + "WHERE JuridicalPerson_Person_idPerson=?";
    private static final String REMOVE_SQL = "DELETE FROM `Supplier` "
            + "WHERE JuridicalPerson_Person_idPerson=?";
    private static final String INSERT_SUPPLIER_BRAND_SQL = "INSERT INTO "
            + "`Brand_has_Supplier`(`Supplier_JuridicalPerson_Person_idPerson`, "
            + "`Brand_nameBrand`) VALUES (?,?)";
    private static final String GET_ONE_BRANDS_SQL = "SELECT `Brand_nameBrand`"
            + " FROM `Brand_has_Supplier` WHERE Supplier_JuridicalPerson_Person_idPerson=?";
    private static final String GET_ALL_SQL = "SELECT `JuridicalPerson_Person_idPerson` "
            + "FROM `Supplier` WHERE 1";
    private static final String BRAND = "Brand_nameBrand";

    @Override
    public void insertSupplier(Supplier supplier) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, supplier.getId());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        try {
            for (Brand brand : supplier.getAvaliableBrands()) {
                MySqlHandler.getInstance().getDb().
                        execute(INSERT_SUPPLIER_BRAND_SQL, supplier.getId(), brand.getName());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSupplier(Supplier supplier) throws DatabaseErrorException, NoResultsException {
        getSupplier(supplier.getId());
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_SQL, supplier.getId());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Supplier getSupplier(String id) throws DatabaseErrorException, NoResultsException {
        Supplier sup = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getResultSet().next()) {
                sup = new Supplier(new ArrayList<>(), null, null, null, null);
            }
            qr.closeAll();
            QueryResult qrB = MySqlHandler.getInstance().getDb().query(GET_ONE_BRANDS_SQL, id);
            while (qrB.getResultSet().next()) {
                sup.getAvaliableBrands().add(new Brand(qrB.getResultSet().
                        getString(BRAND)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            //throw new DatabaseErrorException();
            e.printStackTrace();
        }
        if (sup == null) {
            throw new NoResultsException();
        }
        return sup;

    }

    @Override
    public ArrayList<Supplier> getAllSuppliers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
