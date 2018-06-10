/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import RMI.Interfaces.BrandRemote;
import RMI.Interfaces.LocationRemote;
import RMI.Interfaces.PersonRemote;
import RMI.Interfaces.ProductRemote;
import RMI.Interfaces.RecordRemote;
import RMI.Interfaces.SearchRemote;
import RMI.Interfaces.ServiceRemote;
import RMI.Interfaces.ServiceTypeRemote;
import java.rmi.Remote;

/**
 *
 * @author ecsanchesjr
 */
public interface RemoteMethods
        extends Remote,
        BrandRemote,
        LocationRemote,
        PersonRemote,
        ProductRemote,
        RecordRemote,
        SearchRemote,
        ServiceRemote,
        ServiceTypeRemote {

    public final static String RMI_BD = "RMI_BD_SERVER";
    public final static int RMI_PORT = 1099;
}
