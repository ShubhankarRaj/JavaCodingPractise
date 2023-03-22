package com.unitedcaterers.serverwithoutlockcookie;

import com.unitedcaterers.UCServerFactory;
import com.unitedcaterers.db.DataBase;
import com.unitedcaterers.db.DataBaseImpl;
import com.unitedcaterers.util.UCException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Notice that in this package (com.unitedcaterers.serverwithoutcookie)
 * RMIUCServer implements UCServerFactory, while in com.unitedcaterers.server package
 * RMIUCServer implements UCServer.
 *
 * UCServerFactory has only one method - getClientProxy(), which returns a remote object
 * that implements the desired business funtionality (UCServer or DataBase),
 * while UCServer implements all
 * the business functionality itself.
 *
 */

public class RMIUCServerImpl extends UnicastRemoteObject implements UCServerFactory {

    private DataBase db = null;

    public RMIUCServerImpl(String dbfilename, String magiccode) throws RemoteException, UCException {
        try {
            db = new DataBaseImpl(dbfilename, magiccode);
            ClientProxy.setDataBase(db);
        } catch (IOException ioe) {
            throw new UCException("Unable to connect to the database. : " + ioe.getMessage());
        }

    }

    @Override
    public ClientProxy getClientProxy() throws RemoteException, UCException {
        return new ClientProxy();
    }
}
