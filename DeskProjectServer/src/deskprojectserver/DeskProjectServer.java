/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Transactions.Record;
import Exceptions.DatabaseErrorException;
import Exceptions.NoResultsException;
import deskprojectserver.Database.DAOBuilder;

/**
 *
 * @author gabriel
 */
public class DeskProjectServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatabaseErrorException, NoResultsException {
        for(Record record : DAOBuilder.getInstance().getRegisterDAO().getLikeRecords("03")){
            System.out.println(record);
        }
        System.out.println("dsadsada");
        for(Record record : DAOBuilder.getInstance().getRegisterDAO().getAllRecords()){
            System.out.println(record);
        }
    }

}
