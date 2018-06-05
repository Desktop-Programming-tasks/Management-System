/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver;

import Classes.Transactions.Product;
import Exceptions.DatabaseErrorException;
import deskprojectserver.Database.DAOBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class DeskProjectServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (Product p : DAOBuilder.getInstance().getProductDAO().getLikeProducts("zé")) {
                System.out.println(p);
            }
        } catch (DatabaseErrorException ex) {
            Logger.getLogger(DeskProjectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
