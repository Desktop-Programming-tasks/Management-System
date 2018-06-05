/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Transactions.Brand;
import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import Exceptions.DuplicatedEntryException;
import Exceptions.NoResultsException;
import Exceptions.UnavailableBrandException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import deskprojectserver.Database.DAO.Transactions.BrandDAO;
import deskprojectserver.Database.DAO.Transactions.ProductDAO;
import deskprojectserver.Utils.FormatUtils;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.Commons.MySqlBrandDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlProductDAO extends ProductDAO {
    
    private static final String BAR_CODE = "barCodeProduct";
    private static final String NAME = "nameProduct";
    private static final String PRICE = "priceProduct";
    private static final String QUANTITY = "quantityProduct";
    private static final String BRAND_NAME = "Brand_nameBrand";
    private static final String INSERT_SQL = "INSERT INTO `Product`(`barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand`) "
            + "VALUES (?,?,?,?,?)";
    
    private static final String GET_ALL_SQL = "SELECT `barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE 1";
    private static final String GET_ONE_SQL = "SELECT `barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE barCodeProduct=?";
    private static final String GET_LIKE_SQL = "SELECT `barCodeProduct`, `nameProduct`, "
            + "`priceProduct`, `quantityProduct`, `Brand_nameBrand` "
            + "FROM `Product` WHERE nameProduct LIKE ?";
    private static final String REMOVE_SQL = "DELETE FROM `Product` WHERE barCodeProduct=?";
    private static final String UPDATE_SQL = "UPDATE `Product` "
            + "SET `nameProduct`=?,"
            + "`priceProduct`=?,`quantityProduct`=?,"
            + "`Brand_nameBrand`=? WHERE barCodeProduct=?";
    
    @Override
    public void insertProduct(Product product) throws DatabaseErrorException, DuplicatedEntryException, UnavailableBrandException {
        BrandDAO bdao = new MySqlBrandDAO();
        try {
            bdao.checkIfExists(product.getBrand());
        } catch (DatabaseErrorException | NoResultsException e) {
            throw new UnavailableBrandException();
        }
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    product.getBarCode(), product.getName(), product.getPrice(),
                    product.getQuantityInStock(), product.getBrand().getName());
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEntryException();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }
    
    @Override
    public void updateProduct(Product product) throws DatabaseErrorException, NoResultsException, DuplicatedEntryException, UnavailableBrandException {
        try {
            getProduct(product.getBarCode());
        } catch (NoResultsException e) {
            throw e;
        }
        try {
            BrandDAO brand = new MySqlBrandDAO();
            brand.checkIfExists(product.getBrand());
        } catch (NoResultsException e) {
            throw new UnavailableBrandException();
        }
        try {
            MySqlHandler.getInstance().getDb().execute(UPDATE_SQL,
                    product.getName(), product.getPrice(), product.getQuantityInStock(),
                    product.getBrand().getName(), product.getBarCode());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }
    
    @Override
    public void removeProduct(Product product) throws DatabaseErrorException, NoResultsException {
        getProduct(product.getBarCode());
        try {
            MySqlHandler.getInstance().getDb().execute(REMOVE_SQL, product.getBarCode());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
    }
    
    @Override
    public Product getProduct(String id) throws DatabaseErrorException, NoResultsException {
        Product product = null;
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ONE_SQL, id);
            while (qr.getResultSet().next()) {
                product = new Product(
                        qr.getResultSet().getString(BAR_CODE),
                        new Brand(qr.getResultSet().getString(BRAND_NAME)),
                        qr.getResultSet().getFloat(PRICE),
                        qr.getResultSet().getString(NAME));
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        if (product == null) {
            throw new NoResultsException();
        }
        return product;
    }
    
    @Override
    public ArrayList<Product> getAllProducts() throws DatabaseErrorException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_SQL);
            while (qr.getResultSet().next()) {
                Product product = new Product(
                        qr.getResultSet().getString(BAR_CODE),
                        new Brand(qr.getResultSet().getString(BRAND_NAME)),
                        qr.getResultSet().getFloat(PRICE), qr.getResultSet().getString(NAME));
                product.setQuantityInStock(qr.getResultSet().getInt(QUANTITY));
                products.add(product);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return products;
    }
    
    @Override
    public ArrayList<Product> getLikeProducts(String id) throws DatabaseErrorException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_LIKE_SQL,
                    FormatUtils.setLikeParam(id));
            while (qr.getResultSet().next()) {
                Product product = new Product(
                        qr.getResultSet().getString(BAR_CODE),
                        new Brand(qr.getResultSet().getString(BRAND_NAME)),
                        qr.getResultSet().getFloat(PRICE), qr.getResultSet().getString(NAME));
                product.setQuantityInStock(qr.getResultSet().getInt(QUANTITY));
                products.add(product);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseErrorException();
        }
        return products;
    }
}
