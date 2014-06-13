package com.unitedcaterers.server;

import java.util.*;
import java.util.logging.Logger;

import javax.swing.*;
/**
 * This class provides the startup method for the Socket Server application.
 * @author Enthuware
 * @version 1.0
 */
public class SocketServerMain {
    private static Logger logger = Logger.getLogger("com.unitedcaterers.server.SocketServerMain");
    public static ServerPropertiesDialog pd = new ServerPropertiesDialog(null);
    public static void startup() {
        Properties props = null;
        
        boolean retryflag = true;
        
        while(retryflag) {
            props = pd.loadProperties("unitedcaterers.properties");
            if(props == null) break;
            try {
                
                logger.fine("Creating SocketUCServer with arguments: "+props.getProperty("server.dbfile")+" , "+props.getProperty("server.dbmagiccode")+"  at port : "+props.getProperty("server.port"));
                
                //Create the remote object
                SocketUCServerImpl theserver = new SocketUCServerImpl(props.getProperty("server.dbfile"), props.getProperty("server.dbmagiccode"), Integer.parseInt(props.getProperty("server.port")));
                
                logger.fine("SocketUCServer bound on port : "+props.getProperty("server.port"));
                JOptionPane.showMessageDialog(null, "SocketUCServer is now bound and available for clients on port "+props.getProperty("server.port"));
                retryflag = false;
            } catch (Exception e) {
                System.out.println("SocketUCServer got exception while instantiation : " + e.getMessage());
                e.printStackTrace();
                int choice = JOptionPane.showConfirmDialog(null, "Unable to startup the server because of exception : "+e.getMessage()+". Do you want to try again?", "United Caterers App", JOptionPane.YES_NO_OPTION);
                retryflag = (choice == JOptionPane.YES_OPTION);
                if(!retryflag) System.exit(0);
            }
        }
    }
    
    
}


