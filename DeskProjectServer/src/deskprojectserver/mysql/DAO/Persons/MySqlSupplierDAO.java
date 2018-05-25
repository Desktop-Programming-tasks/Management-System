/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Classes.Persons.Supplier;
import deskprojectserver.DBExceptions.DatabaseErrorException;
import deskprojectserver.DBExceptions.DuplicatedEntryException;
import deskprojectserver.DBExceptions.NoResultsException;
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

    @Override
    public void insertSupplier(Supplier supplier) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, supplier.getId());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (Exception e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSupplier(Supplier supplier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Supplier getSupplier(String id) throws DatabaseErrorException, NoResultsException {
        Supplier sup = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getRs().next()) {
                sup = new Supplier(null, null, null, null, null);
            }
            if(sup==null){
                throw new NoResultsException();
            }
            return sup;
        } catch (Exception e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public ArrayList<Supplier> getAllSuppliers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
