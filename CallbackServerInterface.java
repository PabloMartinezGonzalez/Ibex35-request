

import java.rmi.*;
import java.util.ArrayList;

/**
 * This is a remote interface for illustrating RMI 
 * client callback.
 * @author M. L. Liu
 */

public interface CallbackServerInterface extends Remote {

  public String sayHello( )   
    throws java.rmi.RemoteException;

// This remote method allows an object client to 
// register for callback
// @param callbackClientObject is a reference to the
//        object of the client; to be used by the server
//        to make its callbacks.

public void registerForCallback(Aviso nuevoAviso) throws java.rmi.RemoteException;
// This remote method allows an object Aviso to 
// cancel its registration for callback
public void unregisterForCallback(Aviso avisoDel) throws java.rmi.RemoteException;
// This remote method allows an object cliente to 
// cancel all its registration for callback
public void unregisterUsrForCallback(CallbackClientInterface callbackClientObject) throws java.rmi.RemoteException;
  
  
}
