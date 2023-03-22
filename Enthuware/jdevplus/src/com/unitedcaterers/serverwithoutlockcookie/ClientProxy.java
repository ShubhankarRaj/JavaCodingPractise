package com.unitedcaterers.serverwithoutlockcookie;

import com.unitedcaterers.UCServer;
import com.unitedcaterers.db.DataBase;
import com.unitedcaterers.db.DuplicateKeyException;
import com.unitedcaterers.db.RecordNotFoundException;
import com.unitedcaterers.db.SecurityException;
import com.unitedcaterers.util.UCException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a client proxy object that is instantiated on the server side for every client.
 * The purpose of this object is to uniquely identify the client so that only the client
 * that has locked the row is able to unlock it.<br><br>
 * Notice that it is very similar to UCServerImpl class in com.unitedcaterers.server package.
 * The differences are -<br>
 * 1. ClientProxy implements UCServerInterface as well as Database interface. The purpose of implementing
 * UCServerInterface is to allow the clients to invoke higher level business methods (which is same as UCServerImpl), while
 * the purpose of implementing DataBase interface is to satify the requirement of the assignment
 * to expose methods of DataBase class to the client.<br>
 * 2. ClientProxy does not instantiate DataBaseImpl. RMIUCServerImpl sets ClientProxy's DataBase reference
 * using ClientProxy.setDataBase() method, which is a static method.<br><br>
 * We have chosen to have our client use the UCServerInterface so that the client does
 * not have to worry about locking/unlocking.<br>
 * 3. ClientProxy maintains an ArrayList of the records locked by the client it represents.
 * If a client tries to unlock a record that it hasn't locked, it throws a SecurtiyException.
 * In case of com.unitedcaterers.server.UCServerImpl, this functionality is directly implemented
 * using lock cookie.
 * <br><br>
 * In your project, you have to make a decision whether you want to the client
 * to call lower level Database methods or higher level UCServerInterface methods. You can
 * implement the client either way. If you use the use the lower level DataBase interface methods,
 * you can get rid of the UCServerInterface altogether.
 * <br><br>
 * Our recommendation is to not use this approach at all. Identifying clients without lock cookie
 * by maintaining a client proxy on the server is an inefficient way and defeats the whole purpose
 * of creating a stateless service. So we recommend to modify the signature of the lock/unlock
 * methods and use the approach implemented in com.unitedcaterers.server package,
 * where UCServer exposes only the business methods to the clients.
 *
 * 
 * @author Enthuware
 */
public class ClientProxy extends UnicastRemoteObject implements UCServer, DataBase, Serializable {

    private volatile static DataBase db = null;
    private volatile static boolean closedFlag = false;
    private static Logger logger = Logger.getLogger("com.unitedcaterers.serverwithoutlockcookie.ClientProxy");
    private ArrayList<Integer> recordsLockedByThisClient = new ArrayList<Integer>();

    public ClientProxy() throws RemoteException, UCException {
    }

    /**
     * This method is called only once by RMIUCServer.
     * @param pDb reference to DataBaseImpl
     */
    public static void setDataBase(DataBase pDb) {
        ClientProxy.db = pDb;
    }

    public static void setclosedFlag(boolean pclosedFlag) {
        ClientProxy.closedFlag = pclosedFlag;
    }

    //Implementation of higher level UCServerInterface follows -
    @Override
    public String[][] searchCaterersByMaxGuests(int maxGuests) throws RemoteException, UCException {
        if (closedFlag) {
            throw new UCException("Connection to the database has been closed. Must restart/recreate the server.");
        }
        String[] criteria = new String[]{null, null, null, "" + maxGuests, null, null};
        return findAndReturnData(criteria);
    }

    @Override
    public String[][] searchCaterersByLocation(String location) throws RemoteException, UCException {
        if (closedFlag) {
            throw new UCException("Connection to the database has been closed. Must restart/recreate the server.");
        }

        String[] criteria = new String[]{null, location, null, null, null, null};
        return findAndReturnData(criteria);

    }

    @Override
    public String[][] searchCaterersByMaxGuestsAndLocation(int maxGuests, String location) throws RemoteException, UCException {
        if (closedFlag) {
            throw new UCException("Connection to the database has been closed. Must restart/recreate the server.");
        }
        String[] criteria = new String[]{null, location, null, "" + maxGuests, null, null};
        return findAndReturnData(criteria);

    }

    @Override
    public String[][] getAllCaterers() throws RemoteException, UCException {
        if (closedFlag) {
            throw new UCException("Connection to the database has been closed. Must restart/recreate the server.");
        }
        String[] criteria = new String[]{null, null, null, null, null, null};
        return findAndReturnData(criteria);
    }

    @Override
    public boolean bookCaterer(String customerid, String[] originalData) throws RemoteException, UCException {
        if (closedFlag) {
            throw new UCException("Connection to the database has been closed. Must restart/recreate the server.");
        }
        //you might also want to implement server side data validation on customerid here.
        boolean status = false;

        int recordNo = -100;
        try {
            recordNo = Integer.parseInt(originalData[0]);

            //Notice that we are using "withoutCookie" methods in this implementation
            //Since each client has a corresponding ClientProxy object on server, there is no
            //need for lock cookie now.
            lockWithoutCookie(recordNo);


            String[] data = db.read(recordNo);

            //here, first we checkif the row already has a customerid (data[5] should be empty)
            //and then we check whether the any data of the record has changed
            //before we got the lock
            if (data[5] == null || data[5].trim().length() == 0) {
                
                boolean datachanged = false;
                for (int n = 1; n < originalData.length; n++) {
                    //First element of original data is the record number so second element of originalData matches with the first element of data.
                    if (!originalData[n].trim().equals(data[n - 1])) {
                        datachanged = true;
                        break;
                    }
                }

                if (datachanged) {
                    //row will be unlocked in finally clause
                    throw new UCException("The caterer data was updated. Please refresh your view and try again.");
                }
                data[5] = customerid;
                try {
                    db.updateWithoutCookie(recordNo, data);
                } catch (SecurityException ex) {
                    Logger.getLogger(ClientProxy.class.getName()).log(Level.SEVERE, null, ex);
                }
                status = true;
            } else if (data[5].trim().equals(customerid)) {
                status = true;
            } else {
                throw new UCException("This caterer is already booked.");
            }

            return status;

        } catch (RecordNotFoundException re) {
            throw new UCException("Unable to book the caterer because it does not exist.");
        } finally {
            try {
                unlockWithoutCookie(recordNo);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Serious problem in unlocking. Should not happen.", e);
            }
        }

    }

    /**
     * This is an additional method (not specified in the DataBase interface) that
     * is very useful for the application. It returns the actual data in String[][] format
     * instead of just the record numbers that match the given criteria. The first
     * value of the String[] is the record number the rest are the fields.
     * @param criteria A string array of same length as the number of fields.
     * @return An array of String arrays containing record data.
     */
    public String[][] findAndReturnData(String[] criteria) throws UCException {
        if (closedFlag) {
            throw new UCException("Connection to the database has been closed. Must restart/recreate the server.");
        }
        String[][] retval = null;
        try {
            //Here is an important situation that you need to understand.
            //Let's say User A's search results in two records: 3, 5
            //Now, after getting the record numbers using the DataBase.find() method,
            //User A, tries to retrieve the record data using DataBase.read(recordNo)
            //method. It reads record 3, and before it could read record 5, User B
            //goes ahead and updates(or even deletes) record 5. So when User A, tries
            //to retrieve record 5, either it will not satisfy the seach criteria
            //(if the record was updated by User B) or it will get an exception (if
            //the record was deleted by User B)

            //There are two approaches to solve this problem:

            //You can lock the db to make sure that User A finds and reads the records
            //without anybody else modifying the records.
            //This is a passimistic approach because we are assuming that somebody
            //might modify the records while User A is reading them.

            //There is another way to accomplish this without locking the db.
            //After getting the record numbers using the find(criteria)
            //method, you can read the records one by one. But you must match each record
            //with the critiria again to verify that the record has not been changed.
            //You also need to catch the exceptions in case the record was deleted.
            //This is optimistic approach because here you are assuming that records
            //will not be modified while User A is reading them.

            db.lockWithoutCookie(-1);
            int[] ia = db.find(criteria);
            retval = new String[ia.length][];
            for (int i = 0; i < ia.length; i++) {
                String[] data = db.read(ia[i]);
                retval[i] = new String[data.length + 1];
                retval[i][0] = "" + ia[i];
                for (int j = 0; j < data.length; j++) {
                    retval[i][j + 1] = data[j];
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception in findAndReturnData()", e);
            return retval;
        } finally {
            try {
                db.unlockWithoutCookie(-1);
            } catch (RecordNotFoundException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return retval;

    }
    //Methods of DataBase interface follow -

    @Override
    public String[] read(int recNo) throws RecordNotFoundException {
        return db.read(recNo);
    }

    @Override
    public void update(int recNo, String[] data, String lockkey)
            throws RecordNotFoundException, SecurityException {
        db.update(recNo, data, lockkey);
    }

    @Override
    public void delete(int recNo, String lockkey)
            throws RecordNotFoundException, SecurityException {
        db.delete(recNo, lockkey);
    }

    @Override
    public int[] find(String[] criteria) {
        return db.find(criteria);
    }

    @Override
    public int create(String[] data) throws DuplicateKeyException {
        return db.create(data);
    }

    @Override
    public String lock(int recNo) throws RecordNotFoundException {
        return db.lock(recNo);
    }

    @Override
    public void unlock(int recNo, String lockkey) throws RecordNotFoundException, SecurityException {
        db.unlock(recNo, lockkey);
    }

    @Override
    public void lockWithoutCookie(int recNo) throws RecordNotFoundException {
        db.lockWithoutCookie(recNo);
        recordsLockedByThisClient.add(recNo);
    }

    @Override
    public void unlockWithoutCookie(int recNo) throws RecordNotFoundException, SecurityException {
        if (recordsLockedByThisClient.contains(new Integer(recNo))) {
            db.unlockWithoutCookie(recNo);
            recordsLockedByThisClient.remove(new Integer(recNo)); //Explicitly using Integer object because ArrayList has a remove(int) method as well
        } else {
            throw new SecurityException("You do not own the lock on this records.");
        }
    }

    @Override
    public void updateWithoutCookie(int recNo, String[] data) throws RecordNotFoundException, SecurityException {
        if (recordsLockedByThisClient.contains(new Integer(recNo))) {
            db.updateWithoutCookie(recNo, data);
        } else {
            throw new SecurityException("You do not own the lock on this records.");
        }
    }

    @Override
    public void deleteWithoutCookie(int recNo) throws RecordNotFoundException, SecurityException {
        if (recordsLockedByThisClient.contains(new Integer(recNo))) {
            db.deleteWithoutCookie(recNo);
        } else {
            throw new SecurityException("You do not own the lock on this records.");
        }
    }
}
