/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql.DAO.Transactions;

import Classes.Enums.ServiceStatus;
import Classes.Transactions.Record;
import Classes.Transactions.Service;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import deskprojectserver.Database.DAO.Transactions.TransactionServiceDAO;
import deskprojectserver.Utils.QueryResult;
import deskprojectserver.mysql.DAO.Persons.MySqlPersonDAO;
import deskprojectserver.mysql.MySqlHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MySqlServiceTransactionDAO extends TransactionServiceDAO {
    
    private static final String RECORD_ID = "Registry_idRegistry";
    private static final String ST_ID = "ServiceType_idServiceType";
    private static final String ST_STATUS_ID = "ServiceStatus_idServiceStatus";
    private static final String START_DATE = "startDateSt_has_Registry";
    private static final String ESTIMATED_DATE = "estimatedDateSt_has_Registry";
    private static final String FINAL_DATE = "finalDateSt_has_Registry";
    private static final String EMPLOYEE_ID = "Person_idEmployee";
    private static final String PRICE = "IndividualPrice_St_has_Registry";
    
    private static final String INSERT_SQL = "INSERT INTO `St_has_Registry`"
            + "(`ServiceType_idServiceType`, `Registry_idRegistry`, "
            + "`ServiceStatus_idServiceStatus`, `startDateSt_has_Registry`, "
            + "`estimatedDateSt_has_Registry`, `finalDateSt_has_Registry`, "
            + "`Person_idEmployee`,`IndividualPrice_St_has_Registry`) "
            + "VALUES (?,?,?,?,?,?,?,?)";
    private static final String GET_ALL_REG_SQL = "SELECT `ServiceType_idServiceType`,"
            + " `Registry_idRegistry`, `ServiceStatus_idServiceStatus`,"
            + " `startDateSt_has_Registry`, `estimatedDateSt_has_Registry`,"
            + " `finalDateSt_has_Registry`,"
            + " `Person_idEmployee`,"
            + " `IndividualPrice_St_has_Registry` "
            + "FROM `St_has_Registry` WHERE Registry_idRegistry=?";
    
    @Override
    public void insertServiceTransaction(Record record, Service service) throws DatabaseErrorException {
        try {
            MySqlHandler.getInstance().getDb().execute(INSERT_SQL,
                    service.getServiceType().getId(),
                    record.getId(), ServiceStatus.enumToInt(ServiceStatus.ON_ESTIMATE),
                    service.getStartDate(), service.getEstimatedDate(),
                    service.getFinishDate(), service.getAssignedEmployee().getId(),
                    service.getPrice());
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new DatabaseErrorException();
        }
    }
    
    @Override
    public ArrayList<Service> getAllRecordServices(Record record) throws DatabaseErrorException {
        ArrayList<Service> services = new ArrayList<>();
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_ALL_REG_SQL, record.getId());
            while (qr.getResultSet().next()) {
                Service sv = new Service(
                        qr.getResultSet().getDate(START_DATE), qr.getResultSet().getDate(ESTIMATED_DATE),
                        ServiceStatus.intToEnum(qr.getResultSet().getInt(ST_STATUS_ID)),
                        new MySqlPersonDAO().getPerson(getEmployeeId(qr.getResultSet().getString(EMPLOYEE_ID))),
                        new MySqlServiceTypeDAO().getServiceType(
                                qr.getResultSet().getString(ST_ID)));
                services.add(sv);
            }
            qr.closeAll();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        } catch (NoResultsException ex) {
            Logger.getLogger(MySqlServiceTransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }
    
    private String getEmployeeId(String id) throws DatabaseErrorException {
        final String GET_CPF = "SELECT `idPerson`, `idDocumentPerson`"
                + " FROM `Person` WHERE idPerson=?";
        final String CPF = "idDocumentPerson";
        
        try {
            QueryResult qr = MySqlHandler.getInstance().getDb().query(GET_CPF, id);
            while (qr.getResultSet().next()) {
                return qr.getResultSet().getString(CPF);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
        
        return null;
    }
}
