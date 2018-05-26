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
public class NoResultsException extends Exception {

    public NoResultsException() {
        super("No results to query");
    }
}
