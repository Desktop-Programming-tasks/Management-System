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
public class UnavailableBrandException extends Exception{

    public UnavailableBrandException() {
        super("Brand not available to be inserted!");
    }
    
}
