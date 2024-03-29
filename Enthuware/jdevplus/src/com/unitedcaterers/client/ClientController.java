package com.unitedcaterers.client;

import com.unitedcaterers.UCServer;
import com.unitedcaterers.UCServerFactory;
import com.unitedcaterers.client.gui.UCClientPropertiesDialog;

//This is required only because the client can be a local client as well.
//Otherwise, the client package should not have a direct dependency on classes
//in server packages.
import com.unitedcaterers.server.UCServerImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * This class is the controller of the client application.
 * All the user actions are handled by this class.
 * Depending on user action (for example, user clicking on a menu item), it uses appropriate server method and performs the necessary action.
 * Depending on the result of the operation, if the view has to change, it updates the model and notifies the observers thus refreshing the view.
 */
public class ClientController implements ActionListener {

    private static Logger logger = Logger.getLogger("com.unitedcaterers.client.ClientController");
    /**
     * The application frame.
     */
    private ClientFrame appFrame = null;
    /**
     * The mainModel. It contains all the required data for the view. Methods of this class modify it and notify observers.
     */
    private ClientModel mainModel = null;
    /**
     * The current UCServerInterface. It can be changed dynamically. You can have a option in Menu which allows the user to connect to remote or local database. All you need to do is create an appropriate DBAdapter and set it here.
     */
    private UCServer currentServer = null;
    /**
     * The current search parameters. It is used to refresh the table.
     */
    private String currentQuery = "";
    private int currentGuests;
    private String currentLocation;
    /**
     * Denotes whether the current server is a local (in process) server or not.
     */
    private boolean localflag = false;
    /**
     * This dialog is used to capture connection properties.
     */
    private UCClientPropertiesDialog pd;
    /**
     * This variable is required only because we have multiple implementations of the project: rmi and socket.
     * In your actual project, you will not need this.
     */
    public static String rmiorsocket = "rmi"; //other values are "socket" and "none"

    /**
     * This constructor takes a ClientFrame and the clientType, which should be rmi, socket, or none. If none is passed, it denotes that a no remote server is to be used. It does the following two things -
     *  - creates an object of class ClientModel, which contains the data needed by the ClientFrame to display to the user.
     *  - sets the clientframe as an observer to this model and notifies it.
     *
     */
    public ClientController(ClientFrame clientFrame, String clientType) {
        this.appFrame = clientFrame;
        pd = new UCClientPropertiesDialog(appFrame);
        this.localflag = "none".equals(clientType);
        ClientController.rmiorsocket = clientType;
        this.appFrame.addWindowListener(new WindowAdapter() {

            //closes the server side component only if the client is a non-networked standalone client before exiting the JVM
            @Override
            public void windowClosing(WindowEvent we) {
                if (currentServer != null && ClientController.this.localflag) {
                    //We know that in case of (non-networked) local mode, currentServer actually
                    //points to UCServerImpl object.
                    //In case of networked mode, we don't know the actually class of the object
                    //referred to by currentServer
                    ((UCServerImpl) currentServer).close();
                }
                System.exit(0);
            }
        });


        connectToServer(localflag);

        mainModel = new ClientModel();
        appFrame.setController(this);

        //The following two lines use Observer - Observable pattern provided by Java
        //appFrame is (conceptually) an Observer and mainModel is Observable.
        //Note that appFrame itself does not implement java.util.Observer, it actually is a facade for two Observers - JTable and MessagePanel, which implement Observer
        appFrame.setModel(mainModel);

        //This ensures that the view is updated after initialization.
        mainModel.notifyObservers(new Boolean(true));
    }

    /**
     * It collects the required parameters from the user and then either connects to a
     * local DB file or to a remote UCServer depending upon localFlag.
     * @param pLocalflag If true, it means it is a standalone client
     */
    private void connectToServer(boolean pLocalflag) {
        try {
            pd.setLocal(pLocalflag);
            Properties props = pd.loadProperties("unitedcaterers.properties");
            if (props == null) {
                return; //should not happen
            }

            if (pLocalflag) {
                UCServer newServer = new UCServerImpl(props.getProperty("client.localdbfile"),
                        props.getProperty("client.localdbmagiccode"));
                //close the existing server if any
                if (this.currentServer != null && this.localflag) {
                    ((UCServerImpl) this.currentServer).close();
                }
                this.currentServer = newServer;
                this.localflag = true;
                JOptionPane.showMessageDialog(appFrame, "Opened Local Database file.", "UC Client", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String host = props.getProperty("client.serverhost");
                String port = props.getProperty("client.serverport");
                UCServer newServer = null;
                if ("socket".equals(rmiorsocket)) {
                    String name = host + ":" + port;
                    logger.info("ClientController - Socket version - connecting to " + name);

                    //Note: Reflection is used here only because we have two types of Server implmentations- RMI and Socket
                    //and we do not want this class to have a compile time dependency on the actual implementation class.
                    //In your actual project, however, you will have just one line:
                    //
                    //   newServer = new SocketClientConnector(host, Integer.parseInt(port));
                    //

                    Class c = Class.forName("com.unitedcaterers.client.SocketClientConnector");
                    Constructor constr = c.getConstructor(new Class[]{String.class, Integer.TYPE});
                    newServer = (UCServer) constr.newInstance(new Object[]{host, new Integer(Integer.parseInt(port))});

                    if (this.currentServer != null && this.localflag) {
                        ((UCServerImpl) this.currentServer).close();
                    }
                    JOptionPane.showMessageDialog(appFrame, "Connected to SocketServer at "+name, "UC Client", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String name = "rmi://" + host + ":" + port + "/RemoteUCServer";
                    logger.info("ClientController - RMI version - connecting to " + name);
                    Remote remoteObj =  Naming.lookup(name);

                    //Now here, you have two choices. If you are running the server from
                    //com.unitedcaterers.server package (i.e. lock functionality with lock cookie),
                    //the remoteObj actually refers to UCServer interface.
                    //So you need to use the following line -

                    //newServer = (UCServer) remoteObj;

                    //and if you are running the server from com.unitedcaterers.serverwithoutcookie package,
                    //the remoteObj actually refers to UCServerFactory interface, in
                    //which case you need to do the following -


                     UCServerFactory ucsFactory = (UCServerFactory) remoteObj;
                     newServer  = ucsFactory.getClientProxy();

                     //close the currentServer only if it is a local one, in which case
                     //we know that it is an object of class UCServerImpl.
                    if (this.currentServer != null && this.localflag) {
                        ((UCServerImpl) this.currentServer).close();
                    }
                    JOptionPane.showMessageDialog(appFrame, "Connected to RMI Server at "+name, "UC Client", JOptionPane.INFORMATION_MESSAGE);
                }
                this.currentServer = newServer;
                this.localflag = false;

            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(appFrame, "Exception occured in connecting to the server: " + e.getMessage(), "UC Message", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Exception in connecting to server :" + e.getMessage(), e);
        }
    }

    /**
     * All the action events in the application GUI, upon which some action has to be taken, 
     * data has to be changed, or the view has to be changed, come to this method.
     * It identifies the action and delegates the control to appropriate doXXX method.
     *
     * @param ae The action event. ActionCommand identifies the action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand(); //ActionCommand is just a string that we use to determine the intention of the user. Its value is set in ControlPanel
        System.out.println("In  ClientController actionPerformed " + action);

        if ("EXIT".equals(action)) {
            doExit();
        } else if ("CONNECT_LOCAL".equals(action)) {
            new Thread() {

                @Override
                public void run() {
                    connectToServer(true);
                }
            }.start();
        } else if ("CONNECT_REMOTE".equals(action)) {
            new Thread() {

                @Override
                public void run() {
                    connectToServer(false);
                }
            }.start();
        } else if ("DISCONNECT".equals(action)) {
            if (this.currentServer != null && this.localflag) {
                ((UCServerImpl) this.currentServer).close();
            }
            //Note that there is nothing to do if the client is connected to a remote server.
            currentServer = null;
        } else if ("CLEAR_CATERERS".equals(action)) {
            //The following statements can also be refactored into a method doClearCaterers() as in cases further below.
            String[][] data = new String[0][0]; //This is done for simplicity. You can also make it a static final variable and reuse it instead instantiating a new String array everytime.
            mainModel.setDisplayRows(data);
            mainModel.notifyObservers();
            mainModel.getMessageModel().updateModel(
                    "Please use File Menu to search for caterers.");
            mainModel.getMessageModel().notifyObservers();

        } else if ("VIEWALL_CATERERS".equals(action)) {
            doViewAllCaterers();
        } else if (action.equals("SEARCH_CATERERS")) {
            doSearchCaterers();
        } else if (action.startsWith("SEARCH_CATERERS_WITH_PARAMS")) {
            int i1 = action.indexOf(":");
            int i2 = action.indexOf(",");
            int cap = -1;
            if (i2 > i1 + 1) {
                cap = Integer.parseInt(action.substring(i1 + 1, i2));
            }
            String loc = null;
            if (action.length() > i2 + 1) {
                loc = action.substring(i2 + 1);
            }
            doSearchCaterers(cap, loc);
        } else if (action.indexOf("BOOK_CATERER") != -1) {
            doBookCaterer(action); // ActionCommand for this would be like: BOOK_CATERER:12
        } else if ("APP_HELP".equals(action)) {
            JOptionPane.showMessageDialog(appFrame, "To learn how to use the application please see \\docs\\userguide.txt ", "Help", JOptionPane.INFORMATION_MESSAGE);
        } else if ("ABOUT".equals(action)) {
            JOptionPane.showMessageDialog(appFrame, "Copyright, Enthuware Inc.", "About", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Books a caterer.
     * .It identifies the caterer by looking at the row no. attached to the action parameter ( BOOK_CATERER:12, here 12 is the row index).
     * .It calls the bookCaterer method on the server.
     * .It pops up appropriate messages if something is wrong.
     * .Finally, it refreshes the display.
     *
     * @param action The the complete action command that has row index attached to it.
     */
    public void doBookCaterer(String action) {
        if(currentServer == null){
            JOptionPane.showMessageDialog(appFrame, "Please connect to a server before booking.", "Book Caterer", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int x = action.indexOf(":");
        String[] data = null;
        if (x != -1 && x < action.length()) {
            int index = Integer.parseInt(action.substring(x + 1).trim());
            data = mainModel.getDisplayRows()[index];
        } else {
            int ind = appFrame.getTablePanel().getSelectedIndex();
            if (ind == -1) {
                JOptionPane.showMessageDialog(appFrame, "Please select a caterer from the list of caterers first.", "Book Caterer", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            data = mainModel.getDisplayRows()[ind];
        }

        String customerid = JOptionPane.showInputDialog(appFrame, "Please enter the customerid :", "Book Caterer", JOptionPane.INFORMATION_MESSAGE);
        if (customerid != null) {
            //you may also add validation code for customerid here. For example, you can check if it contains any spaces or junk characters
            try {
                boolean status = currentServer.bookCaterer(customerid, data);
                if (status) {
                    JOptionPane.showMessageDialog(appFrame, "Thank you, " + data[1] + " has been booked.", "Book Caterer", JOptionPane.INFORMATION_MESSAGE);
                    refreshView(currentQuery, currentGuests, currentLocation);
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Sorry, Unable to book this caterer.", "Book Caterer", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(appFrame, "Unable to book the caterer. " + e.getMessage(), "Book Caterer", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    /**
     * Closes the connections and exits
     */
    public void doExit() {

        //MsgDialog md = new MsgDialog(appFrame, "Exit System", "Do you really want to exit?", new String[]{"Yes", "No"}, new int[]{1, 2});
        //md.setSize(200, 100);
        int choice = JOptionPane.showConfirmDialog(appFrame, "Do you really want to exit?", "Exit System", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (currentServer != null && ClientController.this.localflag) {  //this is done so that any open file is closed properly before exiting
                ((UCServerImpl) currentServer).close();
            }
            //Note that there is nothing to do if it is connected to a remote client except exiting.
            System.exit(0);
        }
    }

    /**
     * It pops up standard imputdialog boxes to get the search criteria
     * and then uses the searchCaterer(...) methods to retrive the matching caterers.
     * It calls refreshView() method that updates the model and notifies observers
     * thereby updating the view frame.
     */
    public void doSearchCaterers() {
        Object obj = JOptionPane.showInputDialog(appFrame, "How many number of guests do you want to cater to?(Click Cancel if no preference)", "Max Guests", JOptionPane.INFORMATION_MESSAGE, null, null, "50");
        int maxguests = -1;
        if (obj != null) {
            try {
                maxguests = Integer.parseInt(obj.toString().trim());
                currentGuests = maxguests;
            } catch (Exception e) {
                //do nothing or pop up error message.
            }
        }

        String location = null;
        obj = JOptionPane.showInputDialog(appFrame, "Which location are you in?(Click Cancel if no preference)", "View Routes", JOptionPane.INFORMATION_MESSAGE, null, null, "Iselin");
        if (obj != null) {
            location = obj.toString().trim();
            currentLocation = location;
        }

        doSearchCaterers(maxguests, currentLocation);

    }

    private void doSearchCaterers(int maxguests, String location) {
        try {

            if (location != null && maxguests != -1) {
                currentQuery = "viewbyguestslocation";
                refreshView(currentQuery, maxguests, location);
            } else if (location != null) {
                currentQuery = "viewbylocation";
                refreshView(currentQuery, -1, location);
            } else if (maxguests != -1) {
                currentQuery = "viewbyguests";
                refreshView(currentQuery, maxguests, "");
            } else {
                currentQuery = "viewall";
                refreshView(currentQuery, -1, "");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(appFrame, "Exception occured in processing request : " + e.getMessage(), "UC Message", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.WARNING, "Exception in processing request.", e);
        }

    }

    private void doViewAllCaterers() {
        currentQuery = "viewall";
        refreshView(currentQuery, -1, "");
    }

    /**
     * This is just a helper method that calls appropriate searchXXX method.
     * @param query
     * @param maxguests
     * @param location
     */
    private void refreshView(String query, int maxguests, String location) {
        if (currentServer == null) {
            JOptionPane.showMessageDialog(appFrame, "Please connect to a server first. ", "Unconnected", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            String[][] data = new String[0][0];
            if ("viewall".equals(query)) {
                data = currentServer.getAllCaterers();

            } else if ("viewbyguests".equals(query)) {
                data = currentServer.searchCaterersByMaxGuests(maxguests);
            } else if ("viewbylocation".equals(query)) {
                data = currentServer.searchCaterersByLocation(location);
            } else if ("viewbyguestslocation".equals(query)) {
                data = currentServer.searchCaterersByMaxGuestsAndLocation(maxguests, location);
            }
            mainModel.setDisplayRows(data);
            mainModel.notifyObservers();
            mainModel.getMessageModel().updateModel(
                    "Please double click on a caterer to book.");
            mainModel.getMessageModel().notifyObservers();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(appFrame,
                    "Exception occured in processing request : " + e.getMessage(),
                    "UC Message", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.WARNING, "Exception in processing request", e);
        }

    }
}
