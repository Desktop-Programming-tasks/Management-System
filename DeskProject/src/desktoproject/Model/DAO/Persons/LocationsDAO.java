/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.DAO.Persons;

import desktoproject.Globals;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ecsanchesjr
 */
public abstract class LocationsDAO {
    public static ArrayList<String> getStates() throws RemoteException {
        return Globals.getInstance().getChannel().queryStates();
    }
    
    public static ArrayList<String> getCities(String state) throws RemoteException {
        return Globals.getInstance().getChannel().queryCities(state);
    }
}