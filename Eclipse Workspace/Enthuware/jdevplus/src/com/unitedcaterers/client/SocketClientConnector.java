package com.unitedcaterers.client;

import java.io.*;
import java.net.*;

import com.unitedcaterers.util.*;
import com.unitedcaterers.server.Request;
import com.unitedcaterers.server.Response;
import com.unitedcaterers.UCServer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the client side counterpart of the socket based UCServer. In a way, this serves the same
 * purpose as a stub class of the RMI server.
 * This class's responsibilty is to handle the remoting mechanism (marshalling and unmarshalling) of the request and response.
 */

public class SocketClientConnector implements UCServer {
    private static Logger logger = Logger.getLogger("com.unitedcaterers.client.SocketClientConnector");
    private String host = "127.0.0.1";
    private int port = 9090;
    
    public SocketClientConnector() {
    }
    
    /**
     * @param newHost The host name/ip on which the SocketServerDBAdapter is running.
     * @param newPort The port on which the SocketServerDBAdapter is listening.
     */
    public SocketClientConnector(String newHost, int newPort) {
        this.host = newHost;
        this.port = newPort;
    }
    
    @Override
    public String[][] searchCaterersByMaxGuests(int maxguests) throws UCException, java.rmi.RemoteException {
        Request req = new Request();
        req.setAction("searchCaterersByMaxGuests");
        Object[] oa = new Object[]{ new Integer(maxguests) };
        req.setParams(oa);
        return processResponse(req);
    }
    
    @Override
    public String[][] searchCaterersByMaxGuestsAndLocation(int maxguests, String location) throws UCException, java.rmi.RemoteException {
        Request req = new Request();
        req.setAction("searchCaterersByMaxGuestsAndLocation");
        Object[] oa = new Object[]{ new Integer(maxguests) , location};
        req.setParams(oa);
        return processResponse(req);
    }
    
    @Override
    public String[][] searchCaterersByLocation(String location) throws UCException, java.rmi.RemoteException {
        Request req = new Request();
        req.setAction("searchCaterersByLocation");
        Object[] oa = new Object[]{ location};
        req.setParams(oa);
        return processResponse(req);
    }
    
    @Override
    public String[][] getAllCaterers() throws UCException, java.rmi.RemoteException {
        Request req = new Request();
        req.setAction("getAllCaterers");
        Object[] oa = new Object[]{};
        req.setParams(oa);
        return processResponse(req);
    }
    
    private String[][] processResponse(Request req) throws UCException, java.rmi.RemoteException {
        String[][] result = new String[0][0];
        Response res = null;
        try {
            res = this.sendRequest(req);
        } catch(Exception e) {
            logger.log(Level.WARNING, "Exception thrown by sendRequest()", e);
            return result;
        }
        
        int status = res.getStatus();
        
        if( status == Response.COMPLETED ) {
            return ((String[][]) res.getValue() );
        } else if(status == Response.UNKNOWN) {
            Object error = res.getError();
            if(error instanceof UCException) {
                throw ( ( UCException ) error);
            }
            if(error instanceof java.rmi.RemoteException) {
                throw ( ( java.rmi.RemoteException ) error);
            } else if(error instanceof Throwable) {
                throw new java.rmi.RemoteException("Exception in executing socket server method", (Throwable) error);
            } else {
                logger.severe("Exception in executing socket server method : "+error);
                throw new java.rmi.RemoteException("Exception in executing socket server method : "+error);
            }
        } else {
            logger.fine(""+res.getValue());
        }
        return result;
        
    }
    
    @Override
    public boolean bookCaterer(String customerid, String[] origdata) throws UCException, java.rmi.RemoteException {
        Request req = new Request();
        req.setAction("bookCaterer");
        Object[] oa = new Object[]{ customerid, origdata };
        req.setParams(oa);
        
        Response res = null;
        try {
            res = this.sendRequest(req);
        } catch(Exception e) {
            logger.log(Level.WARNING,"Exception in processing request bookCaterer()", e);
            throw new java.rmi.RemoteException("Exception in processing request bookCaterer()", e);
        }
        
        int status = res.getStatus();
        
        if( status == Response.COMPLETED ) {
            return (((Boolean)res.getValue()).booleanValue());
        } else if(status == Response.UNKNOWN) {
            Object error = res.getError();
            if(error instanceof UCException) {
                throw ( ( UCException ) error);
            }
            if(error instanceof java.rmi.RemoteException) {
                throw ( ( java.rmi.RemoteException ) error);
            } else if(error instanceof Throwable) {
                throw new java.rmi.RemoteException("Exception in executing bookCaterer", (Throwable) error);
            } else {
                logger.severe("Exception in executing socket server method : "+error);
                throw new java.rmi.RemoteException("Exception in executing bookCaterer : "+error);
            }
        } else {
            logger.severe("Exception in executing socket server method : "+res.getError());
            return false;
        }
        
    }
    
    
    private Response sendRequest(Request req) throws Exception {
        Socket s =  null;
        try {
            s = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream( s.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( s.getInputStream() );            
            oos.writeObject(req);
            oos.flush();
            //oos.close();
            

            
            Response res = (Response) ois.readObject();
            return res;
        } catch(Exception e) {
            throw e;
        } finally {
            if(s != null) s.close();
        }
        
    }
}
