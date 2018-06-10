/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Transactions.Product;
import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.TransactionProductDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MySqlProductTransactionDAO extends TransactionProductDAO {

    private static final String INSERT_SQL
            = "INSERT INTO `Product_has_Registry`"
            + "(`Product_barCodeProduct`, `Registry_idRegistry`, "
            + "`Product_has_RegistryQuantity`, `Product_has_RegistryIndividualPrice`)"
            + " VALUES (?,?,?,?)";

    @Override
    public void insertProductTransaction(Record record, Product product) throws DatabaseErrorException, DuplicatedEntryException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    product.getBarCode(), record.getId(),
                    product.getQuantity(), product.getPrice());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        }
    }

}
