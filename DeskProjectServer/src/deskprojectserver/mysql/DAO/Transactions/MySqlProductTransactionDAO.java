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
import Exceptions.NoResultsException;
import Exceptions.OutOfStockException;
import Exceptions.UnavailableBrandException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.TransactionProductDAO;
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
public class MySqlProductTransactionDAO extends TransactionProductDAO {

    private static final String BAR_CODE = "Product_barCodeProduct";
    private static final String RECORD_ID = "Registry_idRegistry";
    private static final String QUANTITY = "Product_has_RegistryQuantity";
    private static final String INDIVIDUAL_PRICE = "Product_has_RegistryIndividualPrice";
    private static final String INSERT_SQL
            = "INSERT INTO `Product_has_Registry`"
            + "(`Product_barCodeProduct`, `Registry_idRegistry`, "
            + "`Product_has_RegistryQuantity`, `Product_has_RegistryIndividualPrice`)"
            + " VALUES (?,?,?,?)";
    private static final String GET_ALL_REG_SQL = "SELECT `Product_barCodeProduct`, "
            + "`Registry_idRegistry`, `Product_has_RegistryQuantity`, "
            + "`Product_has_RegistryIndividualPrice` "
            + "FROM `Product_has_Registry` WHERE Registry_idRegistry=?";

    public MySqlProductTransactionDAO() {
        super(new MySqlProductDAO());
    }

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

    @Override
    public void checkIfAvailable(Product product) throws DatabaseErrorException, OutOfStockException {
        try {
            Product productAux = productDao.getProduct(product.getBarCode(), true);
            System.out.println(productAux.getQuantityInStock());
            if (product.getQuantity() > productAux.getQuantityInStock()) {
                throw new OutOfStockException(product);
            }
        } catch (NoResultsException ex) {
            throw new OutOfStockException(product);
        }
    }

    @Override
    public void updateStock(Product product, int quantity) throws DatabaseErrorException {
        try {
            Product productAux = productDao.getProduct(product.getBarCode(), true);
            product.setQuantityInStock(productAux.getQuantityInStock()
                    + quantity);
            productDao.updateProduct(product);
        } catch (NoResultsException ex) {
            //
        } catch (UnavailableBrandException | DuplicatedEntryException ex) {
            Logger.getLogger(MySqlProductTransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Product> getAllRecordProducts(Record record) throws DatabaseErrorException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_REG_SQL, record.getId());
            while (qr.getResultSet().next()) {
                Product product = productDao.getProduct(qr.getResultSet().getString(BAR_CODE), false);
                product.setPrice(qr.getResultSet().getFloat(INDIVIDUAL_PRICE));
                product.setQuantity(qr.getResultSet().getInt(QUANTITY));
                products.add(product);
            }
            qr.closeAll();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseErrorException();
        } catch (NoResultsException ex) {
            //
        }
        return products;
    }

}
