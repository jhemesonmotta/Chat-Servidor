/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {     
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry pronto.");
        } catch (Exception e) {
                    System.out.println("Exception starting RMI registry:" + e);
        }
                
        try{			
                IServidorImpl s = new IServidorImpl();
                Naming.rebind("ChatSrv", s); 
        }catch (RemoteException | MalformedURLException e){
                System.err.println("Erro: "+ e.getMessage() );	
                System.exit(0);
        }       
    }
    
}
