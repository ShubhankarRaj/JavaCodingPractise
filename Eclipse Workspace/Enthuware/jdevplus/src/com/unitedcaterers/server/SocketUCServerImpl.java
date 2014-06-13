package com.unitedcaterers.server;

import com.unitedcaterers.util.UCException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

import java.util.logging.Level;

/**
 * The purpose of this class is to accept client connections and broker the requests and responses.
 * First, it opens up a server socket on the given port and listens for client requests.
 * When a client wants to make a request, the client instantiates a com.unitedcaterers.server.Request
 * object and initializes it with the method name that is to be called on the server and
 * appropriate parameters.  The client then connects to the server, and transmits this Request object
 * to the server over the outputstream using Object serialization, which is received by this class.
 * It unserializes the Request object, determines the method name and parameters and then calls that method
 * on UCServer object by Reflection API. It then creates a com.unitedcaterers.server.Response object
 * and initializes it with the result of the method call. It then serializes the Response object and
 * sends it back to the client over the same socket connection.
 *
 * This approach is a little inefficient in terms of speed because we are creating a new socket connection from the client
 * for each method call. However, you can do some extra coding to use the same socket connection for all requests
 * from a client. On the other hand, maintaining a dedicated thread for each client is inefficient in terms of
 * the number of simultaneous clients that can be supported. A choince should be made
 * depending on the usage pattern. For example, a web server usually does not keep a dedicated thread
 * per client. The browser usually makes a new connection for each request. This is because there are thousands
 * of clients that the webserver needs to service simultaneously and it is not practical to keep a dedicated
 * thread for each client.
 *
 * @author Enthuware
 * @version 1.0
 */
public class SocketUCServerImpl extends UCServerImpl {

    private volatile ServerSocket serverSocket;
    private static final int MAXCLIENTS = 5;

    public SocketUCServerImpl(String dbfilename, String dbmagiccode, int port) throws UCException, RemoteException {
        super(dbfilename, dbmagiccode);
        try {
            serverSocket = new ServerSocket(port, MAXCLIENTS);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to create server socket : " + e.getMessage(), e);
            throw new UCException("Unable to create server socket : " + e.getMessage());
        }
        Thread listener = new ListenerThread();
        listener.start();
        System.out.println("SocketUCServer started...");
    }

    /**
     * The inner class is just a way to execute a client request in a separate thread.
     * @author Enthuware
     * @version 1.0
     */
    class ProcessorThread extends Thread {

        volatile Socket client;

        ProcessorThread(Socket clientConnection) {
            client = clientConnection;
        }

        @Override
        public void run() {
            processClient(client);
        }
    }

    /**
     * This inner class is a helper class that implements the logic of listening for client connections.
     * @author Enthuware
     * @version 1.0
     */
    class ListenerThread extends Thread {

        @Override
        public void run() {
            while (true) {
                Socket clientConnection = null;
                try {
                    clientConnection = serverSocket.accept();
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Exception in accepting connection from client." + e.getMessage(), e);
                }

                if (clientConnection != null) {
                    new ProcessorThread(clientConnection).start();
                }
            }
        }
    }

    /**
     * This is a helper method that determines what method to call on the UCServer object based on
     * the data sent by the client in Request object.
     * @param req
     * @return
     */
    private Method getMethod(Request req) {
        Object[] params = req.getParams();
        String action = req.getAction();
        Class[] ca = null;
        Method m = null;
        if (params != null) {
            ca = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Integer) {
                    ca[i] = Integer.TYPE;
                } else if (params[i] instanceof Byte) {
                    ca[i] = Byte.TYPE;
                } else if (params[i] instanceof Short) {
                    ca[i] = Short.TYPE;
                } else if (params[i] instanceof Character) {
                    ca[i] = Character.TYPE;
                } else if (params[i] instanceof Long) {
                    ca[i] = Long.TYPE;
                } else if (params[i] instanceof Float) {
                    ca[i] = Float.TYPE;
                } else if (params[i] instanceof Double) {
                    ca[i] = Double.TYPE;
                } else if (params[i] instanceof Boolean) {
                    ca[i] = Boolean.TYPE;
                } else {
                    ca[i] = params[i].getClass();
                }
            }
        }

        try {
            m = this.getClass().getMethod(action, ca);
        } catch (NoSuchMethodException nme) {
            System.out.println("Unknown action " + req.getAction());
            nme.printStackTrace();
        }

        return m;

    }

    public static void main(String[] args) throws Exception {
        SocketUCServerImpl ss = new SocketUCServerImpl("ucdbdb.db", "UCBD", 1100);

        Class c = ss.getClass();
        Method m = c.getMethod("searchByMaxGuests", new Class[]{java.lang.Integer.class});
        logger.fine("" + m);
    }

    /**
     * The method does all the marshalling and unmarshalling of Request and Response for a client request.
     * @param clientSocket
     */
    private void processClient(Socket clientSocket) {
        boolean fatalerror = false;
        InputStream is = null;
        ObjectOutputStream oos = null;
        Response res = new Response();

        try {
            is = clientSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            oos = new ObjectOutputStream(clientSocket.getOutputStream());

            Request req = (Request) ois.readObject();

            Method m = getMethod(req);
            if (m != null) {
                Object obj = m.invoke(this, req.getParams());
                res.setStatus(Response.COMPLETED);
                res.setValue(obj);
            } else {
                res.setStatus(Response.NOT_COMPLETED);
                res.setValue("Unknown or Unsupported Action");
            }

            oos.writeObject(res);
            oos.flush();
            oos.close();
            ois.close();
            clientSocket.close();
        } catch (InvocationTargetException ive) {
            Throwable t = ive.getCause();
            try {
                res.setStatus(Response.UNKNOWN);
                res.setValue(t.getMessage());
                res.setError(t);
                oos.writeObject(res);
                oos.flush();
                oos.close();
                is.close();
            } catch (Exception inner) {
                logger.log(Level.WARNING, "Cant' do anything. INNER Exception is:--------------");
                inner.printStackTrace();
                logger.log(Level.WARNING, "OUTER Exception is:--------------");
                ive.printStackTrace();
            }

        } catch (Exception e) {
            try {
                res.setStatus(Response.UNKNOWN);
                res.setValue("Some Exception Occured in processing request.");
                res.setError(e);
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.writeObject(res);
                oos.flush();
                oos.close();
                is.close();
            } catch (Exception inner) {
                logger.log(Level.WARNING, "Cant' do anything. INNER Exception is:--------------");
                inner.printStackTrace();
                logger.log(Level.WARNING, "OUTER Exception is:--------------");
                e.printStackTrace();
            }

        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                System.out.println("Unable to close connection");
                e.printStackTrace();
            }
        }

    }
}