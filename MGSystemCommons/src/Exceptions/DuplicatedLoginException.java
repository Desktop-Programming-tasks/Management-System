/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author gabriel
 */
public class DuplicatedLoginException extends Exception {

    public DuplicatedLoginException() {
        super("Login already inserted!");
    }
}
