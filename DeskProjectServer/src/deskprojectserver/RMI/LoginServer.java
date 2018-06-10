/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.RMI;

import RMI.RemoteLogin;
import java.rmi.RemoteException;

/**
 *
 * @author ecsanchesjr
 */
public class LoginServer implements RemoteLogin {

    @Override
    public boolean tryLogin(String login, String password) throws RemoteException {
        return true;
    }
    
}
