/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import Classes.Enums.Autentication;
import Exceptions.DatabaseErrorException;
import RMI.RemoteLogin;
import deskprojectserver.Database.DAOBuilder;
import java.rmi.RemoteException;

/**
 *
 * @author ecsanchesjr
 */
public class LoginServer implements RemoteLogin {

    @Override
    public Autentication tryLogin(String login, String password) throws RemoteException, DatabaseErrorException {
        return DAOBuilder.getInstance().getAutenticationDAO().autenticate(login, password);
    }
    
}
