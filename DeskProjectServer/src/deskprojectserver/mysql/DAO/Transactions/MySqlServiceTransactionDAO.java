/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Transactions.Record;
import Classes.Transactions.Service;
import Exceptions.DatabaseErrorException;
import deskprojectserver.Database.DAO.Transactions.TransactionServiceDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class MySqlServiceTransactionDAO extends TransactionServiceDAO {

    private static final String INSERT_SQL = "INSERT INTO `St_has_Registry`"
            + "(`ServiceType_idServiceType`, `Registry_idRegistry`, "
            + "`ServiceStatus_idServiceStatus`, `startDateSt_has_Registry`, "
            + "`estimatedDateSt_has_Registry`, `finalDateSt_has_Registry`, "
            + "`Person_idEmployee`,`IndividualPrice_St_has_Registry`) "
            + "VALUES (?,?,?,?,?,?,?,?)";

    @Override
    public void insertServiceTransaction(Record record, Service service) throws DatabaseErrorException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    service.getServiceType().getId(),
                    record.getId(), 1, service.getStartDate(), service.getEstimatedDate(),
                    service.getFinishDate(), service.getAssignedEmployee().getId(),
                    service.getPrice());
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new DatabaseErrorException();
        }
    }

}
