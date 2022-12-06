

import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This class implements the remote interface 
 * CallbackServerInterface.
 * @author M. L. Liu
 */

public class CallbackServerImpl extends UnicastRemoteObject
     implements CallbackServerInterface {
    private HashMap<String, Double> datos = new HashMap<>();
    private ArrayList<Aviso> avisos;
   

   public CallbackServerImpl() throws RemoteException {
     super( );
     avisos = new ArrayList<>();
   }

  public String sayHello( )   
    throws java.rmi.RemoteException {
      return("hello");
  }


  public void ActualizarTabla() throws IOException{
        Document web = null; 

        web = (Document) Jsoup.connect("https://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice").get();
   
        Elements tabla = web.select("#ctl00_Contenido_tblAcciones");
   
        for (int i = 1; i <= 35; i++) {
            String[] nombreAux = tabla.first().childNodes().get(1).childNodes().get(i).childNodes().get(1).toString().split(">");
            String[] nombre = nombreAux[2].split("<");
            
            String[] valorAux = tabla.first().childNodes().get(1).childNodes().get(i).childNodes().get(2).toString().replace(",",".").split(">");
            String[] valor = valorAux[1].split("<");
 
            datos.put(nombre[0], Double.parseDouble(valor[0]));
        }
        System.out.println("\n************");
        System.out.println(datos);
        System.out.println("************\n");
        doCallbacks();
        
        
  }
  public synchronized void registerForCallback(Aviso nuevoAviso) throws java.rmi.RemoteException{
      // store the callback object into the vector
      if (!(avisos.contains(nuevoAviso))) {
        avisos.add(nuevoAviso);
        avisos.stream().distinct();
        System.out.println("Registered new notification ");
        
    } // end ifbject client to 
  }  

// This remote method allows an o
  public synchronized void unregisterForCallback( Aviso avisoDel) 
    throws java.rmi.RemoteException{
    if (avisos.remove(avisoDel)) {
      System.out.println("Unregistered notification ");
    } else {
       System.out.println("Unregister: that notification wasn't registered.");
    }
  } 

  
  public synchronized void unregisterUsrForCallback(
    CallbackClientInterface callbackClientObject) 
    throws java.rmi.RemoteException{
      
      ArrayList<Aviso> avisosElim = new ArrayList<>();
      for(Aviso aviso : avisos)
        if(callbackClientObject.equals(aviso.getCliente()))
            avisosElim.add(aviso);
      for(Aviso aviso : avisosElim){
          unregisterForCallback(aviso);
      }     
  } 
  
  private synchronized void doCallbacks() throws java.rmi.RemoteException{
      ArrayList<Aviso> avisosElim = new ArrayList<>();
      for(Aviso aviso : avisos){
          if(aviso.getTipo().equals("Venta")){
              if(datos.get(aviso.getEmpresa()) >=  aviso.getValor()){
              aviso.getCliente().notifyMe(aviso);
              avisosElim.add(aviso);
            }
          }
          else if(aviso.getTipo().equals("Compra")){
              if(datos.get(aviso.getEmpresa()) <=  aviso.getValor()){
                aviso.getCliente().notifyMe(aviso);
                avisosElim.add(aviso);
            }
          }
      }
      for(Aviso aviso : avisosElim){
          unregisterForCallback(aviso);
      }
  } // doCallbacks


   
  
}// end CallbackServerImpl class   
