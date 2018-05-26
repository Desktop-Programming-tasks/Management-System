/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author ecsanchesjr
 */
public class DuplicatedEntryException extends Exception {

    public DuplicatedEntryException() {
        super("Entry already registered in database");
    }
    
}
