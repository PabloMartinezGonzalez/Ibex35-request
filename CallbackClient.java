
import java.io.Serializable;
import java.rmi.RemoteException;
/**
 * This class represents the object client for a
 * distributed object of class CallbackServerImpl, 
 * which implements the remote interface 
 * CallbackServerInterface.  It also accepts callback
 * from the server.
 * 
 * 
 * 
 * @author M. L. Liu
 */

public class CallbackClient implements Serializable{
    
    public static void main(String args[]) throws RemoteException{
        VMenu menu = new VMenu();
        menu.setVisible(true);
    }
}//end class
