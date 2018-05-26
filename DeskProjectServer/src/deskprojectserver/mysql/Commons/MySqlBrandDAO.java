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
import deskprojectserver.mysql.MySqlHandler;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlBrandDAO extends BrandDAO{
    private static final String INSERT_SQL="INSERT INTO `Brand`(`nameBrand`) VALUES (?)";
    private static final String GET_ONE_SQL="SELECT `idBrand`, `nameBrand` "
            + "FROM `Brand` WHERE nameBrand=?";
    private static final String ID = "idBrand";
    private static final String NAME ="nameBrand";
    @Override
    public void insertBrand(Brand brand) throws DatabaseErrorException,DuplicatedEntryException {
        try{
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL, brand.getName());
        }
        catch(MySQLIntegrityConstraintViolationException e){
            throw new DuplicatedEntryException();
        }
        catch(Exception e){
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void updateBrand(Brand brand) throws DatabaseErrorException,NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeBrand(Brand brand) throws DatabaseErrorException,NoResultsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Brand getBrand(String id) throws DatabaseErrorException,NoResultsException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Brand> getAllBrands() throws DatabaseErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
