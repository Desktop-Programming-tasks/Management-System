/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.DBExceptions;

/**
 *
 * @author gabriel
 */
public class DatabaseErrorException extends Exception{

    public DatabaseErrorException() {
        super("Unknown database error");
    }
    
}
