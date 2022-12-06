

import java.rmi.*;
import java.rmi.server.*;
/**
 * This class implements the remote interface 
 * CallbackClientInterface.
 * @author M. L. Liu
 */

public class CallbackClientImpl extends UnicastRemoteObject
     implements CallbackClientInterface {
    
    private VMenu vm;
    public CallbackClientImpl(VMenu vm) throws RemoteException {
        super();
        this.vm = vm;
    }

    public CallbackClientImpl() throws RemoteException{
    }



    public synchronized void notifyMe(Aviso aviso){
        vm.recibirNotificacion(aviso);
   }      
    

}// end CallbackClientImpl class   
