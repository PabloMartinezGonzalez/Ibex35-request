

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the object server for a distributed
 * object of class Callback, which implements the remote 
 * interface CallbackInterface.
 * @author M. L. Liu
 */

public class CallbackServer  {
  public static void main(String args[]) {
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);
    String portNum, registryURL;
    try{     
      System.out.println( "Enter the RMIregistry port number:");
      portNum = (br.readLine()).trim();
      int RMIPortNum = Integer.parseInt(portNum);
      startRegistry(RMIPortNum);
      CallbackServerImpl exportedObj =   new CallbackServerImpl();
      registryURL = "rmi://localhost:" + portNum + "/callback";
      Naming.rebind(registryURL, exportedObj);
      System.out.println("Callback Server ready.");
      temporizador(exportedObj);
    }// end try
    catch (IOException | NumberFormatException re) {
      System.out.println("Exception in HelloServer.main: " + re);
    } // end catch
    
      
  } // end main


  private static void startRegistry(int RMIPortNum)
    throws RemoteException{
    try {
      Registry registry = 
        LocateRegistry.getRegistry(RMIPortNum);
      registry.list( );  
    }
    catch (RemoteException e) { 
      Registry registry =  LocateRegistry.createRegistry(RMIPortNum);
    }
  } // end startRegistry

  private static void temporizador(CallbackServerImpl exportedObj){
      Timer temporizador = new Timer();
      temporizador.schedule(new TimerTask() {
          @Override
          public void run(){
              try {
                  exportedObj.ActualizarTabla();
              } catch (IOException ex) {
                  Logger.getLogger(CallbackServer.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      }, 0, 1000*60);
  }
} // end class
