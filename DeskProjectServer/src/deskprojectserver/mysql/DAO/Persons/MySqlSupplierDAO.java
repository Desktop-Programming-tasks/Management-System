/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Persons;

import deskprojectserver.Classes.Persons.Supplier;
import deskprojectserver.Database.DAO.Persons.SupplierDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class MySqlSupplierDAO extends SupplierDAO{
    private static final String INSERT_SQL="INSERT INTO `Supplier`(`JuridicalPerson_Person_idPerson`) "
            + "VALUES (?)";
    private static final String GET_ONE_SQL=
            "SELECT `JuridicalPerson_Person_idPerson` FROM `Supplier` "
            + "WHERE JuridicalPerson_Person_idPerson=?";
    @Override
    public void insertSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        MySqlHandler.getInstance().getDb().execute(INSERT_SQL,supplier.getId());
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
    public Supplier getSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier sup= null;
        QueryResult qr=MySqlHandler.getInstance().getDb().query(GET_ONE_SQL,id);
        while(qr.getRs().next()){
            sup = new Supplier(null, null, null, null, null);
        }
        return sup;
    }

    @Override
    public ArrayList<Supplier> getAllSuppliers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
