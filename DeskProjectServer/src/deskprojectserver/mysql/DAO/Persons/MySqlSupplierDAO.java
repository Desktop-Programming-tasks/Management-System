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
public class MySqlSupplierDAO extends SupplierDAO {

    private static final String BRAND = "Brand_nameBrand";
    private static final String ID = "JuridicalPerson_Person_idPerson";

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
    private static final String GET_LIKE_SQL = "SELECT `JuridicalPerson_Person_idPerson` FROM `Supplier`,`Person` WHERE "
            + "Supplier.JuridicalPerson_Person_idPerson=Person.idDocumentPerson AND Person.namePerson LIKE ?";
    private static final String REMOVE_ONE_BRANDS_SQL = "DELETE "
            + "FROM `Brand_has_Supplier` WHERE Supplier_JuridicalPerson_Person_idPerson=?";

    @Override
    public void insertSupplier(Supplier supplier) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, supplier.getDocumentId());
            ObservablesHolder.getSupplier().setChanged();
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        try {
            for (Brand brand : supplier.getAvaliableBrands()) {
                MySqlHandler.getInstance().getDb().
                        execute(INSERT_SUPPLIER_BRAND_SQL, supplier.getDocumentId(), brand.getName());
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) throws DatabaseErrorException, NoResultsException {
        getSupplier(supplier.getDocumentId());
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_ONE_BRANDS_SQL, supplier.getDocumentId());
            for (Brand brand : supplier.getAvaliableBrands()) {
                MySqlHandler.getInstance().getDb().
                        execute(INSERT_SUPPLIER_BRAND_SQL, supplier.getDocumentId(), brand.getName());
            }
            ObservablesHolder.getSupplier().setChanged();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }

    }

    @Override
    public void removeSupplier(Supplier supplier) throws DatabaseErrorException, NoResultsException {
        getSupplier(supplier.getDocumentId());
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_SQL, supplier.getDocumentId());
            ObservablesHolder.getSupplier().setChanged();
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
                sup = new Supplier(new ArrayList<>(), null, null, null, id);
            }
            qr.closeAll();
            QueryResult qrB = MySqlHandler.getInstance().getDb().query(GET_ONE_BRANDS_SQL, id);
            while (qrB.getResultSet().next()) {
                sup.getAvaliableBrands().add(new Brand(qrB.getResultSet().
                        getString(BRAND)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        if (sup == null) {
            throw new NoResultsException();
        }
        return sup;

    }

    private ArrayList<Supplier> getSuppliersGeneric(QueryExecuter executer) throws DatabaseErrorException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try {
            QueryResult qr = executer.execute();
            while (qr.getResultSet().next()) {
                try {
                    suppliers.add(getSupplier(qr.getResultSet().getString(ID)));
                } catch (NoResultsException e) {
                    //
                }
            }
        } catch (SQLException e) {
            throw new DatabaseErrorException();
        }
        return suppliers;
    }

    @Override
    public ArrayList<Supplier> getAllSuppliers() throws DatabaseErrorException {
        return getSuppliersGeneric(new QueryExecuter() {
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
    public ArrayList<Supplier> getLikeSuppliers(String id) throws DatabaseErrorException {
        return getSuppliersGeneric(new QueryExecuter() {
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

}
