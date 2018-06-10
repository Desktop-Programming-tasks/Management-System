/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import Classes.Enums.Autentication;
import Exceptions.DatabaseErrorException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ecsanchesjr
 */
public interface RemoteLogin extends Remote {
    
    public final static String RMI_LOGIN = "RMI_LOGIN_CHANNEL";
    public final static int RMI_PORT = 1100;
    
    public Autentication tryLogin(String login, String password) throws RemoteException, DatabaseErrorException;
}
