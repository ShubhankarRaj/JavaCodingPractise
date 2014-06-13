package com.unitedcaterers;

import java.lang.reflect.Method;






/**
 * This class is the entry point to the application. It determines what - the client
 * or the server, needs to be run and then starts up the required component.
 * @author Enthuware
 * @version 1.0
 */
public class ApplicationMain
{
  public static void main(String[] args) throws Exception
  {
      //For testing purpose
      //args = new String[] { "rmiclient" };
      //args = new String[] { "rmiserver" };
      
      //Use this for rmiserver if you are running the client and the server on the same box 
      //and if you are using localhost as the hostname.
      //System.setProperty("java.rmi.server.hostname","localhost");
      
    if(args.length == 0)
    {
      System.out.println("Invalid argument to main. Supported option are: rmiclient, socketclient, alone, rmiserver, and socketserver. Exiting.");
    }
    else if("rmiclient".equalsIgnoreCase(args[0]))
    {
      ClientMain.startup("rmi");
    }
    else if("socketclient".equalsIgnoreCase(args[0]))
    {
      ClientMain.startup("socket");
    }
    else if("alone".equalsIgnoreCase(args[0]))
    {
      ClientMain.startup("none");
    }
    else if("rmiserver".equalsIgnoreCase(args[0]))
    {
      //Here you can either use com.unitedcaterers.server.RMIServerMain (the one with lock cookie)
      //or com.unitedcaterers.serverwithoutcookie.RMIServerMain (the one with lock cookie)
      //If you use "serverwithoutcookie" version, please make sure to change code in 
      //com.unitedcaterers.client.ClientController's connectToServer method. 


      //Class server = Class.forName("com.unitedcaterers.server.RMIServerMain");
      Class server = Class.forName("com.unitedcaterers.serverwithoutlockcookie.RMIServerMain");
      Method startupmethod = server.getMethod("startup");
      startupmethod.invoke(null);

      //Note: Reflection is used above only because we have two types of Server implmentations: RMI and Socket.
      //Using reflection we are avoiding import statements for importing both the socket and rmi clsses.
      //In your actual project you can just do something like:
      //
      // com.unitedcaterers.server.RMIServerMain.startup();
      //
      // OR
      //
      // RMIServerMain server = new RMIServerMain();
      // server.startup();
      //
      //This is also the reason the command line parameter is rmiserver or socketserver.
      //In your actual project, the command line argument would be just "server".

      
    }
    else if("socketserver".equalsIgnoreCase(args[0]))
    {

      Class server = Class.forName("com.unitedcaterers.server.SocketServerMain");
      Method startupmethod = server.getMethod("startup");
      startupmethod.invoke(null);

    }
    else
    {
      System.out.println("Invalid argument to main. Supported options are: rmiclient, socketclient, alone, rmiserver, or socketserver. Exiting.");
    }
  }
}